package com.garrytrue.weatherapp.di.module;

import com.garrytrue.weatherapp.mvp.presenters.MainActivityPresenter;
import com.garrytrue.weatherapp.mvp.presenters.MapFragmentPresenter;
import com.garrytrue.weatherapp.mvp.presenters.WeatherFragmentPresenter;
import com.garrytrue.weatherapp.mvp.views.IMainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private IMainView view;

    public MainActivityModule(IMainView view) {
        this.view = view;
    }

    @Provides
    IMainView provideMainView() {
        return view;
    }

    @Provides
    MainActivityPresenter provideMainPresenter() {
        return new MainActivityPresenter(view);
    }

    @Provides
    MapFragmentPresenter provideMapFragmentPresenter() {
        return new MapFragmentPresenter();
    }

    @Provides
    WeatherFragmentPresenter provideWeatherFragmentPresenter() {
        return new
                WeatherFragmentPresenter();
    }
}
