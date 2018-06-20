package com.example.mvpdemo.api;

import com.example.mvpdemo.model.entity.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/4/24.
 */
public interface DemoApi {
    @GET("{cityId}.html")
    Call<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);
}
