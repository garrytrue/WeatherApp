package com.garrytrue.weatherapp.di.module;

import com.garrytrue.weatherapp.weather_app.WeatherApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final WeatherApplication application;

    public ApplicationModule(WeatherApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public WeatherApplication provideWeatherApplication() {
        return application;
    }

    @Singleton
    @Provides
    public NetworkModule provideNetworkModule() {
        return new NetworkModule(application);
    }
}
