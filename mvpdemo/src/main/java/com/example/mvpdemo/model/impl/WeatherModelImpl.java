package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.api.DemoApi;
import com.example.mvpdemo.api.RetrofitWrapper;
import com.example.mvpdemo.model.WeatherModel;
import com.example.mvpdemo.model.entity.WeatherInfo;
import com.example.mvpdemo.presenter.OnWeatherListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 模型的实现类，具体对数据进行操作
 */
public class WeatherModelImpl implements WeatherModel {
    @Override
    public void loadWeather(String cityId, final OnWeatherListener weatherListener) {
        DemoApi demoApi = RetrofitWrapper.getInstance().create(DemoApi.class);
        Call<WeatherInfo> weatherInfo = demoApi.getWeatherInfo(cityId);
        weatherInfo.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
            //通过weatherListener回调将Modle层处理好的数据交给Presenter层
                weatherListener.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                weatherListener.onFailure(call,t);
            }
        });
    }
}
