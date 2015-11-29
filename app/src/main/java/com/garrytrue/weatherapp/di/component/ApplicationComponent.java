package com.garrytrue.weatherapp.di.component;

import com.garrytrue.weatherapp.weather_app.WeatherApplication;
import com.garrytrue.weatherapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(WeatherApplication application);
}
