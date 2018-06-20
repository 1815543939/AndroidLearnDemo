package com.example.mvpdemo.presenter.impl;

import com.example.mvpdemo.model.entity.WeatherInfo;
import com.example.mvpdemo.model.impl.WeatherModelImpl;
import com.example.mvpdemo.presenter.OnWeatherListener;
import com.example.mvpdemo.presenter.WeatherPresenter;
import com.example.mvpdemo.ui.view.WeatherView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/4/24.
 */
public class WeatherPresenterImpl implements WeatherPresenter,OnWeatherListener{
    /**
     * presenter层室友model层和view层的引用
     */
    private WeatherModelImpl mWeatherModelImpl;
    private WeatherView mWeatherView;

    public WeatherPresenterImpl(WeatherView weatherView){
        this.mWeatherView = weatherView;
        mWeatherModelImpl = new WeatherModelImpl();
    }

    @Override
    public void getWeatherInfo(String cityId) {
        mWeatherView.showLoading();
        mWeatherModelImpl.loadWeather(cityId,this);
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        mWeatherView.hideLoading();
        mWeatherView.setWeatherInfo(call,response);
    }

    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        mWeatherView.hideLoading();
        mWeatherView.showError();
    }
}
