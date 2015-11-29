package com.garrytrue.weatherapp.di.module;

import android.app.Application;

import com.garrytrue.weatherapp.R;
import com.garrytrue.weatherapp.weather_app.WeatherApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class NetworkModule {
    public static final int HTTP_CACHE = 10*1024*1024;
    Application application;


    @Inject
    public NetworkModule(WeatherApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()))
                .baseUrl(application.getString(R.string.base_url))
                .client(new OkHttpClient().setCache(new Cache(application.getCacheDir(), HTTP_CACHE)))
                .build();
        return retrofit;
    }
}
