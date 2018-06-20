package com.example.mvpdemo.presenter;

import com.example.mvpdemo.model.entity.WeatherInfo;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/4/24.
 */
public interface OnWeatherListener {
    /**
     * 在presenter层实现，给Model层回调，更改view的状态，确保model层不直接操作view层
     * @param call
     * @param response
     */
    void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response);
    void onFailure(Call<WeatherInfo> call, Throwable t);
}
