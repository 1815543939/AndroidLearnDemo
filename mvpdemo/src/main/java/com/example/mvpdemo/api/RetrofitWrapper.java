package com.example.mvpdemo.api;

import com.example.mvpdemo.common.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/24.
 */
public class RetrofitWrapper {
    private static Retrofit sRetrofit;

    public RetrofitWrapper(){
        sRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static RetrofitWrapper getInstance(){
        return RetrofitHolder.instance;
    }
    public static class RetrofitHolder{
        private static RetrofitWrapper instance = new RetrofitWrapper();
    }
    public <T> T create(Class<T> clazz){
        return sRetrofit.create(clazz);
    }
}
