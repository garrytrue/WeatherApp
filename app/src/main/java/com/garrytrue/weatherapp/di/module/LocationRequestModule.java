package com.garrytrue.weatherapp.di.module;

import com.google.android.gms.location.LocationRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class LocationRequestModule {
    private static final int REQUEST_LOCATION_INTERVAL = 10_000; //10 sec
    private static final int REQUEST_LOCATION_FASTEST_INTERVAL = 5_000; // 5sec
    @Singleton
    @Provides
    LocationRequest provideLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(REQUEST_LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(REQUEST_LOCATION_FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        return mLocationRequest;
    }


}
