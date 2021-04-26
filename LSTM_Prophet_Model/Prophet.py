import pandas as pd
from fbprophet import Prophet
from fbprophet.plot import add_changepoints_to_plot
from pandas.plotting import register_matplotlib_converters
import matplotlib.pyplot as plt
import numpy as np
from fbprophet import hdays
import json


def format_data():
    # data_frame = pd.read_csv('resource/1.csv')
    #data_frame = pd.read_csv('resource/12_7_en.csv')
    # data_frame = pd.read_csv('resource/5-d-o.csv')
    #data_frame = pd.read_csv('resource/hour.csv')

    # df4 = pd.DataFrame([['2020-12-12', '23'],
    #                     ['2020-12-13', '34'],
    #                     ['2020-12-14', '48']
    #                     ],
    #                    columns=['ds', 'y'])
    # print(df4)
    # dataFrame['cap'] = 120
    # print(dataFrame)
    # print(dataFrame.head())

    path = 'resource/sta1.txt'
    file = open(path, 'r')
    js = file.read()

    data_list = json.loads(js)
    data_df = pd.DataFrame(data_list)
    data_df['ds'] = pd.to_datetime(data_df['time'])
    data_df['y'] = data_df['num']
    data_df.drop(['num'], axis=1, inplace=True)
    data_df.drop(['time'], axis=1, inplace=True)
    #data_df = data_df.set_index(['ds'], drop=True)
    print(data_df)
    return data_df
    return data_frame



def prophet_model(data_frame):

    #model = Prophet()
    model = Prophet(changepoint_prior_scale=0.2,
                    seasonality_mode='multiplicative',
                    n_changepoints=30,
                    weekly_seasonality=True,
                    daily_seasonality='auto',
                    yearly_seasonality='auto',
                    )
    # model.fit(df4)
    model.add_country_holidays(country_name='CN')
    model.fit(data_frame)

    future = model.make_future_dataframe(periods=30)
    # future['cap'] = 120
    # print(future.tail())

    forecast = model.predict(future)
    # print(forcast[['ds', 'yhat', 'yhat_lower', 'yhat_upper']])

    print(forecast['yhat'][-16:])



    register_matplotlib_converters()

    real_y = pd.read_csv('resource/12_7.csv')
    print(real_y)

    se = np.square(forecast.loc[:, 'yhat'] - real_y['entranceNum'])
    mse = np.mean(se)
    rmse = np.sqrt(mse)
    # err = 0
    #
    # for index in range (len(real_y['entranceNum'])):
    #     if real_y['entranceNum'][index] != 0 and index > 151:
    #         err += abs((forecast['yhat'][index] - real_y['entranceNum'][index])) / real_y['entranceNum'][index]
    #
    # err = err / 16
    #err = err / len(real_y['entranceNum']) * 1.0
    #err = ((forecast.loc[:, 'yhat'] - real_y['entranceNum'])/real_y['entranceNum'])/len(real_y['entranceNum'])

    print("The MSE is: " + str(mse))
    print("The RMSE is: " + str(rmse))
    #print("The err is: " + str(err))

    # plt.plot(forecast['yhat'], 'r', label='predict', linewidth=2)
    # plt.plot(real_y['entranceNum'], 'b', label='real')
    # plt.legend()
    # plt.show()

    fig1 = model.plot(forecast)
    add_changepoints_to_plot(fig1.gca(), model, forecast)
    fig1.show()
    fig2 = model.plot_components(forecast)
    fig2.show()
    plt.show()


if __name__ == '__main__':

    data_frame = format_data()
    prophet_model(data_frame)


