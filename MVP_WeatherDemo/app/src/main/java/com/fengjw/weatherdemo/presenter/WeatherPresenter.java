package com.fengjw.weatherdemo.presenter;

/**
 * Created by fengjw on 2018/6/20.
 */

public interface WeatherPresenter {

    /**
     * get weather logic
     * @param model
     * @param product
     * @param sdanum
     */
    void getWeatherInfo(String model, String product, String sdanum);

}
