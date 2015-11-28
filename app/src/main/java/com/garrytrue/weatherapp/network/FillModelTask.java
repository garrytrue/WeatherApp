package com.garrytrue.weatherapp.network;

import com.garrytrue.weatherapp.mvp.model.WeatherModel;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface FillModelTask {
    @GET("/data/2.5/weather")
    Call<WeatherModel> getModel(@QueryMap Map<String, String> currentLocation);
}
