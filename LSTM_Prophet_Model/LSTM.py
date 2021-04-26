import pandas as pd
import numpy as np

from math import sqrt
import matplotlib.pyplot as plt
from keras.wrappers.scikit_learn import KerasClassifier
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import r2_score
from sklearn.metrics import mean_squared_error
from keras.models import Sequential
from keras.layers import Dense
from keras.callbacks import EarlyStopping
from keras.optimizers import Adam
from keras.layers import LSTM

from sklearn.model_selection import StratifiedKFold
from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import train_test_split


def series_to_supervised(data, n_in=1, n_out=1, dropnan=True):
    n_vars = 1 if type(data) is list else data.shape[1]
    df = pd.DataFrame(data)
    cols, names = [], []
    # i: n_in, n_in-1, ..., 1，为滞后期数
    # 分别代表t-n_in, ... ,t-1期
    for i in range(n_in, 0, -1):
        cols.append(df.shift(i))
        names += [('var%d(t-%d)' % (j + 1, i)) for j in range(n_vars)]
    # i: 0, 1, ..., n_out-1，为超前预测的期数
    # 分别代表t，t+1， ... ,t+n_out-1期
    for i in range(0, n_out):
        cols.append(df.shift(-i))
        if i == 0:
            names += [('var%d(t)' % (j + 1)) for j in range(n_vars)]
        else:
            names += [('var%d(t+%d)' % (j + 1, i)) for j in range(n_vars)]
    agg = pd.concat(cols, axis=1)
    agg.columns = names
    if dropnan:
        agg.dropna(inplace=True)
    return agg


def prepare_data(filepath, n_in, n_out=30, n_vars=4, train_proportion=0.8):
    # 读取数据集
    dataset = pd.read_csv(filepath, encoding='utf-8')

    # 设置时间戳索引
    dataset['Date'] = pd.to_datetime(dataset['ds'])
    dataset.drop(['ds'], axis=1, inplace=True)
    dataset = dataset.set_index(['Date'], drop=True)
    print("Set timestamp:")
    print(dataset.head(10))

    # onehot编码天气
    dataset = pd.get_dummies(dataset, columns=['weather'])
    print("One hot encoding:")
    print(dataset.head(10))

    # 保证所有数据都是float32类型
    values = dataset.values.astype('float32')

    # 变量归一化
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaled = scaler.fit_transform(values)

    # 将时间序列问题转化为监督学习问题
    reframed = series_to_supervised(scaled, n_in, n_out)

    # 取出保留的变量
    contain_vars = []
    for i in range(1, n_in + 1):
        contain_vars += [('var%d(t-%d)' % (j, i)) for j in range(1, n_vars + 1)]
    data = reframed[contain_vars + ['var1(t)'] + [('var1(t+%d)' % (j)) for j in range(1, n_out)]]
    print(data)

    # 修改列名
    col_names = ['Y', 'X1', 'X2', 'X3', 'X4']
    contain_vars = []
    for i in range(n_vars):
        contain_vars += [('%s(t-%d)' % (col_names[i], j)) for j in range(1, n_in + 1)]
    data.columns = contain_vars + ['Y(t)'] + [('Y(t+%d)' % (j)) for j in range(1, n_out)]
    print(data)

    # 分隔数据集，分为训练集和测试集
    values = data.values
    n_train = round(data.shape[0] * train_proportion)
    train = values[:n_train, :]
    test = values[n_train:, :]

    # 分隔输入X和输出y
    train_X, train_y = train[:, :n_in * n_vars], train[:, n_in * n_vars:]
    test_X, test_y = test[:, :n_in * n_vars], test[:, n_in * n_vars:]

    # 将输入X改造为LSTM的输入格式，即[samples,timesteps,features]
    train_X = train_X.reshape((train_X.shape[0], n_in, n_vars))
    test_X = test_X.reshape((test_X.shape[0], n_in, n_vars))
    return scaler, data, train_X, train_y, test_X, test_y, dataset


def format_data(dataset, n_in, n_out, n_vars=4):
    values = dataset.values.astype('float32')

    # 变量归一化
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaled = scaler.fit_transform(values)

    # 将时间序列问题转化为监督学习问题
    reframed = series_to_supervised(scaled, n_in, n_out)

    # 取出保留的变量
    contain_vars = []
    for i in range(1, n_in + 1):
        contain_vars += [('var%d(t-%d)' % (j, i)) for j in range(1, n_vars + 1)]
    data = reframed[contain_vars + ['var1(t)'] + [('var1(t+%d)' % (j)) for j in range(1, n_out)]]
    print(data)

    # 修改列名
    col_names = ['Y', 'X1', 'X2', 'X3', 'X4']
    contain_vars = []
    for i in range(n_vars):
        contain_vars += [('%s(t-%d)' % (col_names[i], j)) for j in range(1, n_in + 1)]
    data.columns = contain_vars + ['Y(t)'] + [('Y(t+%d)' % (j)) for j in range(1, n_out)]
    print(data)

    # 分隔数据集，分为训练集和测试集
    values = data.values
    # n_train = round(data.shape[0] * train_proportion)
    # train = values[:n_train, :]
    # test = values[n_train:, :]

    # 分隔输入X和输出y
    train_X, train_y = values[:, :n_in * n_vars], values[:, n_in * n_vars:]
    #test_X, test_y = test[:, :n_in * n_vars], test[:, n_in * n_vars:]

    # 将输入X改造为LSTM的输入格式，即[samples,timesteps,features]
    train_X = train_X.reshape((train_X.shape[0], n_in, n_vars))
    #test_X = test_X.reshape((test_X.shape[0], n_in, n_vars))
    return train_X, dataset


def fit_lstm(data_prepare, n_neurons_1=50, n_neurons_2=100, n_batch=72, n_epoch=100, loss='mae', optimizer='adam',
             repeats=1):
    train_X = data_prepare[2]
    train_y = data_prepare[3]
    test_X = data_prepare[4]
    test_y = data_prepare[5]
    model_list = []
    for i in range(repeats):
        # 设计神经网络
        model = Sequential()
        model.add(LSTM(n_neurons_1, input_shape=(train_X.shape[1], train_X.shape[2]), return_sequences=True,
                       dropout=0.01))
        model.add(LSTM(n_neurons_2))
        model.add(Dense(train_y.shape[1]))
        model.compile(loss=loss, optimizer=optimizer)
        early_stop = EarlyStopping(monitor='val_loss', patience=1, verbose=1, mode='auto')
        # 拟合神经网络
        history = model.fit(train_X, train_y, epochs=n_epoch, batch_size=n_batch, validation_data=(test_X, test_y),
                            verbose=1, shuffle=False, callbacks=[early_stop])

        # 画出学习过程
        p1 = plt.plot(history.history['loss'], color='blue', label='train')
        p2 = plt.plot(history.history['val_loss'], color='yellow', label='test')

        # 保存model
        model_list.append(model)

    plt.legend(["train", "test"])
    plt.show()
    return model_list


def lstm_predict(model, data_prepare):
    scaler = data_prepare[0]
    test_X = data_prepare[4]
    test_y = data_prepare[5]
    # 做出预测
    yhat = model.predict(test_X)

    # 将测试集上的预测值还原为原来的数据维度
    scale_new = MinMaxScaler()
    scale_new.min_, scale_new.scale_ = scaler.min_[0], scaler.scale_[0]
    inv_yhat = scale_new.inverse_transform(yhat)

    # 将测试集上的实际值还原为原来的数据维度
    inv_y = scale_new.inverse_transform(test_y)
    return inv_yhat, inv_y


# 计算每一步预测的RMSE
def evaluate_forecasts(test, forecasts, n_out):
    rmse_dic = {}
    for i in range(n_out):
        actual = [float(row[i]) for row in test]
        predicted = [float(forecast[i]) for forecast in forecasts]
        rmse = sqrt(mean_squared_error(actual, predicted))
        rmse_dic['t+' + str(i + 1) + ' RMSE'] = rmse
    return rmse_dic


# 以原始数据为背景画出预测数据
def plot_forecasts(series, forecasts):
    # 用蓝色画出原始数据集
    plt.plot(series.values)
    n_seq = len(forecasts[0])
    # 用红色画出预测值
    for i in range(1, len(forecasts) + 1):
        xaxis = [x for x in range(i, i + n_seq + 1)]
        yaxis = [float(series.iloc[i - 1, 0])] + list(forecasts[i - 1])
        plt.plot(xaxis, yaxis, color='red')
    # 展示图像
    plt.show()


if __name__ == '__main__':
    filepath = "resource/12_7_en_weather.csv"
    n_in = 119
    n_out = 30
    n_vars = 4
    n_neuron_1 = 100
    n_neuron_2 = 50
    n_batch = 8
    n_epoch = 400
    repeats = 1
    inv_yhat_list = []
    inv_y_list = []

    data_prepare = prepare_data(filepath, n_in, n_out)
    scaler, data, train_X, train_y, test_X, test_y, dataset = data_prepare
    model_list = fit_lstm(data_prepare, n_neuron_1, n_neuron_2,  n_batch=n_batch, n_epoch=n_epoch, repeats=repeats)

    model_list[0].save('model/lstm_model')

    for i in range(len(model_list)):
        model = model_list[i]
        inv_yhat = lstm_predict(model, data_prepare)[0]
        inv_y = lstm_predict(model, data_prepare)[1]
        inv_yhat_list.append(inv_yhat)
        inv_y_list.append(inv_y)

    print('inv_y')
    print(inv_y_list)
    inv_yhat_ave = np.zeros(inv_y.shape)
    for i in range(repeats):
        inv_yhat_ave += inv_yhat_list[i]

    inv_yhat_ave = inv_yhat_ave / repeats

    rmse_dic_list = []
    for i in range(len(model_list)):
        inv_yhat = inv_yhat_list[i]
        inv_y = inv_y_list[i]
        rmse_dic = evaluate_forecasts(inv_y, inv_yhat, n_out)
        rmse_dic_list.append(rmse_dic)

    rmse_dic_list.append(evaluate_forecasts(inv_y, inv_yhat_ave, n_out))

    df_dic = {}
    for i in range(len(rmse_dic_list) - 1):
        df_dic['第' + str(i + 1) + '次'] = pd.Series(rmse_dic_list[i])

    df_dic['平均'] = pd.Series(rmse_dic_list[i + 1])
    rmse_df = pd.DataFrame(df_dic)
    rmse_df

    s = inv_yhat_ave[0].shape
    erro_rate = np.zeros(s)
    for i in range(len(inv_y)):
        erro_rate += inv_yhat_ave[i] / inv_y[i] - 1

    erro_rate_ave = erro_rate / len(inv_y)
    err_df = pd.DataFrame(pd.Series(erro_rate_ave))
    err_df.columns = ['平均预测错误率']
    err_df.index = ['超前%d步预测' % (i + 1) for i in range(n_out)]
    print(err_df)

    dataset = data_prepare[6]
    test_X = data_prepare[4]
    n_real = len(dataset) - len(test_X) - len(inv_yhat[0])
    print(len(dataset))
    print(len(test_X))
    print(len(inv_yhat[0]))
    print(dataset)
    print(inv_yhat_ave)
    # 多画一个
    y_real = pd.DataFrame(dataset['y'][n_real:])
    plot_forecasts(y_real, inv_yhat_ave)

    pre_df = pd.DataFrame(inv_yhat_ave)
    # 时间戳处理，让它只显示到日
    date_index = dataset.index[n_in - 1 + len(train_X) - 1:n_in - 1 + len(train_X) + len(test_X) - 1]
    pydate_array = date_index.to_pydatetime()
    date_only_array = np.vectorize(lambda s: s.strftime('%Y-%m-%d'))(pydate_array)
    date_only_series = pd.Series(date_only_array)
    pre_df = pre_df.set_index(date_only_series)
    names_columns = ['未来%d期' % (i + 1) for i in range(n_out)]
    pre_df.columns = names_columns
    pre_df = pre_df.round(decimals=2)  # 小数点

    #pre_df_avg = np.zeros(len(pre_df[0]))


    actual_df = pd.DataFrame(inv_y)
    names_columns = ['未来%d期' % (i + 1) for i in range(n_out)]
    actual_df.columns = names_columns
    actual_df = actual_df.set_index(date_only_series)
    actual_df = actual_df.round(decimals=2)

    writer = pd.ExcelWriter('resource/Y-结果导出.xlsx')
    pre_df.to_excel(writer, "Yhat")
    actual_df.to_excel(writer, "Y")
    writer.save()