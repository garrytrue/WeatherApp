package com.garrytrue.weatherapp.di.component;

import com.garrytrue.weatherapp.di.module.MainActivityModule;
import com.garrytrue.weatherapp.di.scope.IMainActivityScope;
import com.garrytrue.weatherapp.mvp.views.MainActivity;
import com.garrytrue.weatherapp.mvp.views.MapFragment;
import com.garrytrue.weatherapp.mvp.views.WeatherFragment;


import dagger.Component;

@IMainActivityScope
@Component(dependencies = ApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);

    void inject(MapFragment mapFragment);

    void inject(WeatherFragment weatherFragment);
}
