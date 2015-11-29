package com.garrytrue.weatherapp.di.component;

import com.garrytrue.weatherapp.di.module.LocationRequestModule;
import com.garrytrue.weatherapp.mvp.presenters.MapFragmentPresenter;
import com.google.android.gms.location.LocationRequest;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = LocationRequestModule.class)
public interface LocationRequestComponent {
    LocationRequest provideLocationRequest();

    void inject(MapFragmentPresenter presenter);

}
