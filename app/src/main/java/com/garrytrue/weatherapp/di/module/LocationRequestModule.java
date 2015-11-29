package com.garrytrue.weatherapp.di.module;

import com.google.android.gms.location.LocationRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class LocationRequestModule {
    public static final int REQUEST_LOCATION_INTERVAL = 10*1000; //10 sec
    public static final int REQUEST_LOCATION_FASTEST_INTERVAL = 5*1000; // 5sec
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
