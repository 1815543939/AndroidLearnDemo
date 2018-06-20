package com.fengjw.weatherdemo.model.impl;


import android.util.Log;

import com.fengjw.weatherdemo.api.DemoApi;
import com.fengjw.weatherdemo.api.RetrofitWrapper;
import com.fengjw.weatherdemo.model.WeatherModel;
import com.fengjw.weatherdemo.model.entity.WeatherInfo;
import com.fengjw.weatherdemo.presenter.OnWeatherListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fengjw on 2018/6/20.
 */

public class WeatherModelImpl implements WeatherModel{

    @Override
    public void loadWeather(String cityId, final OnWeatherListener weatherListener) {
        DemoApi demoApi = RetrofitWrapper.getInstance().create(DemoApi.class);
        Call<WeatherInfo> weatherInfo = demoApi.getWeatherInfo(cityId);
        weatherInfo.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                weatherListener.onResponse(call, response);
                Log.d("fengjw", "onResponse");
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                weatherListener.onFailure(call, t);
                Log.d("fengjw", "onFailure");
            }
        });
    }
}
