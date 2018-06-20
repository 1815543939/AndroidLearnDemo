package com.fengjw.weatherdemo.api;

import com.fengjw.weatherdemo.common.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fengjw on 2018/6/20.
 */

public class RetrofitWrapper {

    private static Retrofit sRetrofit;

    private RetrofitWrapper(){
        sRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RetrofitWrapper getInstance(){
        return RetrofitHolder.instance;
    }

    public static class RetrofitHolder{
        private static RetrofitWrapper instance = new RetrofitWrapper();
    }

    public <T> T create(Class<T> tClass){
        return sRetrofit.create(tClass);
    }



}
