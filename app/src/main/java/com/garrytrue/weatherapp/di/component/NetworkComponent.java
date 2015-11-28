package com.garrytrue.weatherapp.di.component;

import com.garrytrue.weatherapp.di.module.ApplicationModule;
import com.garrytrue.weatherapp.di.module.MainActivityModule;
import com.garrytrue.weatherapp.di.module.NetworkModule;
import com.garrytrue.weatherapp.mvp.presenters.WeatherFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

@Singleton
@Component( modules = NetworkModule.class)

public interface NetworkComponent {

    Retrofit provideRetrofit();

    void inject(WeatherFragmentPresenter presenter);
}
