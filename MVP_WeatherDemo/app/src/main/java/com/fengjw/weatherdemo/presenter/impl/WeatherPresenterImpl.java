package com.fengjw.weatherdemo.presenter.impl;

import com.fengjw.weatherdemo.model.entity.Weather;
import com.fengjw.weatherdemo.model.entity.WeatherInfo;
import com.fengjw.weatherdemo.model.impl.WeatherModelImpl;
import com.fengjw.weatherdemo.presenter.OnWeatherListener;
import com.fengjw.weatherdemo.presenter.WeatherPresenter;
import com.fengjw.weatherdemo.ui.view.WeatherView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fengjw on 2018/6/20.
 */

public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener{

    /**
     * presenter level holds the reference of model level and view level.
     */

    private WeatherModelImpl mWeatherModelImpl;
    private WeatherView mWeatherView;

    public WeatherPresenterImpl(WeatherView weatherView){
        this.mWeatherView = weatherView;
        mWeatherModelImpl = new WeatherModelImpl();
    }

    @Override
    public void getWeatherInfo(String model, String product, String sdanum) {
        mWeatherView.showLoading();
        mWeatherModelImpl.loadWeather(model, product, sdanum, this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        mWeatherView.hideLoading();
        mWeatherView.setWeatherInfo(call, response);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        mWeatherView.hideLoading();
        mWeatherView.showError();
    }
}
