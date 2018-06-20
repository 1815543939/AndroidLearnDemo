package com.fengjw.weatherdemo.model.impl;


import android.util.Log;

import com.fengjw.weatherdemo.api.DemoApi;
import com.fengjw.weatherdemo.api.RetrofitWrapper;
import com.fengjw.weatherdemo.model.WeatherModel;
import com.fengjw.weatherdemo.presenter.OnWeatherListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fengjw on 2018/6/20.
 */

public class WeatherModelImpl implements WeatherModel{

    @Override
    public void loadWeather(String model, String product, String sdanum, final OnWeatherListener weatherListener) {
        DemoApi demoApi = RetrofitWrapper.getInstance().create(DemoApi.class);
        Call<ResponseBody> weatherInfo = demoApi.getWeatherInfo(model, product, sdanum);
        weatherInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                weatherListener.onResponse(call, response);
                Log.d("fengjw", "onResponse");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                weatherListener.onFailure(call, t);
                Log.d("fengjw", "onFailure");
                Log.d("fengjw", t.getMessage());
                Log.d("fengjw", call.request().toString());
            }
        });
    }
}
