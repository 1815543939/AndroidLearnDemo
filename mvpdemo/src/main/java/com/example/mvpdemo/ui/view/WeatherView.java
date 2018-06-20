package com.example.mvpdemo.ui.view;

import com.example.mvpdemo.model.entity.WeatherInfo;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/4/24.
 */
public interface WeatherView {
    void showLoading();
    void hideLoading();
    void showError();
    void setWeatherInfo(Call<WeatherInfo> call, Response<WeatherInfo> response);
}
