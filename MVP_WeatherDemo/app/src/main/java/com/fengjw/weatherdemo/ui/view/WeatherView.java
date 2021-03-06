package com.fengjw.weatherdemo.ui.view;


import com.fengjw.weatherdemo.model.entity.WeatherInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fengjw on 2018/6/20.
 */

public interface WeatherView {
    void showLoading();
    void hideLoading();
    void showError();
    void setWeatherInfo(Call<ResponseBody> call, Response<ResponseBody> response);

}
