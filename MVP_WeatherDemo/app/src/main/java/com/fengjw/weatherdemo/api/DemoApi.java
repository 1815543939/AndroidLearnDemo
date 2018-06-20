package com.fengjw.weatherdemo.api;

import com.fengjw.weatherdemo.model.entity.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fengjw on 2018/6/20.
 */

public interface DemoApi {
    @GET("{cityId}.shtml#search")
    Call<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);
}
