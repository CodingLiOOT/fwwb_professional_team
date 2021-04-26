from sklearn.preprocessing import MinMaxScaler
import numpy as np
import pandas as pd
from pandas.tseries.offsets import Day
from LSTM import prepare_data
from keras.models import load_model
from LSTM import format_data


if __name__ == '__main__':
    filepath = "resource/12_7_en_weather.csv"
    n_in = 119
    n_out = 30
    load_model = load_model('model/lstm_model')
    data_prepare = prepare_data(filepath, n_in, n_out)
    scaler, data, train_X, train_y, test_X, test_y, dataset = data_prepare

    print('train_X')
    print(train_X)
    print(dataset)

    res = np.zeros(7)
    for i in range (0, 7):
        data_prepare = format_data(dataset, n_in, n_out)
        train_X, dataset = data_prepare
        pre = load_model.predict(train_X, verbose=1)
        scale_new = MinMaxScaler()
        scale_new.min_, scale_new.scale_ = scaler.min_[0], scaler.scale_[0]
        inv_yhat = scale_new.inverse_transform(pre)
        res[i] = inv_yhat[0][0]
        dataset.drop(dataset.index[0])

        tom_time = pd.to_datetime((pd.to_datetime(dataset.index[len(dataset)-1]) + 1 * Day()).strftime('%Y-%m-%d'))
        new = pd.DataFrame({
                            'y': res[i],
                           'temp_high': 32+i,
                            'temp_low': 25-i,
                            'weather_0': 1,
                            'weather_1': 0,
                            }, index=[tom_time]
        )
        dataset = dataset.append(new)  # ignore_index=True,表示不按原来的索引，从0开始自动递增
        print(dataset)

    print('pre')
    print(inv_yhat[0])
    print(res)