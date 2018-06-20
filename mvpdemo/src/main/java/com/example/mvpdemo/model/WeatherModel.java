package com.example.mvpdemo.model;

import com.example.mvpdemo.presenter.OnWeatherListener;

/**
 * 抽象模型，定义了访问数据的方法，持有presenter一个接口的引用，目的就是为了方便回调
 */
public interface WeatherModel {
    void loadWeather(String cityId, OnWeatherListener weatherListener);
}
