package com.fengjw.weatherdemo.model;

import com.fengjw.weatherdemo.presenter.OnWeatherListener;

/**
 * Created by fengjw on 2018/6/20.
 *
 * abstract model, defines a way to access data, holds a reference to an interface of presenter
 * the purpose is to facilitate callbacks
 */

public interface WeatherModel {

    void loadWeather(String model, String product, String sdanum, OnWeatherListener weatherListener);

}
