package com.fengjw.weatherdemo.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fengjw on 2018/6/20.
 */

public interface DemoApi {
    @GET()
    //Call<ResponseBody> getWeatherInfo(@Path("model") String cityId);
    Call<ResponseBody> getWeatherInfo(@Query("model") String model,
                                      @Query("product") String product,
                                      @Query("sdanum") String sdanum);
}
