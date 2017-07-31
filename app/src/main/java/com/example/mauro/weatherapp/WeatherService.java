package com.example.mauro.weatherapp;

import com.example.mauro.weatherapp.data.ResultApi;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mauro on 7/28/17.
 */

public interface WeatherService {

    String BASE_URL = "http://api.openweathermap.org/";

    @GET("/data/2.5/forecast/daily")
    Observable<ResultApi> getWeatherDaily(@Query("id") int id_city, @Query("APPID") String api_key, @Query("cnt") int num_days);

    final class Factory {

        Retrofit createRetrofit() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        WeatherService createUserService() {
            return createRetrofit().create(WeatherService.class);
        }
    }
}
