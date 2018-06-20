package com.example.mvpdemo.presenter;

/**
 * Created by Administrator on 2016/4/24.
 */
public interface WeatherPresenter {
    /**
     * 获取天气的逻辑
     * @param cityId
     */
    void getWeatherInfo(String cityId);
}
