package com.garrytrue.weatherapp.di.component;

import com.garrytrue.weatherapp.app.WeatherApplication;
import com.garrytrue.weatherapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 24.11.15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(WeatherApplication application);
}
