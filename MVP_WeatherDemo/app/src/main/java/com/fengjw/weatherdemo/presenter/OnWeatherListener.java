package com.fengjw.weatherdemo.presenter;

import com.fengjw.weatherdemo.model.entity.WeatherInfo;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fengjw on 2018/6/20.
 */

public interface OnWeatherListener {

    /**
     * at presenter level, callback for Model level, change view status,
     * make sure model level not directly operate view level
     * @param call
     * @param response
     */
    void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response);
    void onFailure(Call<WeatherInfo> call, Throwable t);

}
