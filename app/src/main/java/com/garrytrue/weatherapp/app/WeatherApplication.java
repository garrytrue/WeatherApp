package com.garrytrue.weatherapp.app;

import android.app.Application;
import android.content.Context;

import com.garrytrue.weatherapp.di.component.ApplicationComponent;
import com.garrytrue.weatherapp.di.component.DaggerApplicationComponent;
import com.garrytrue.weatherapp.di.component.DaggerNetworkComponent;
import com.garrytrue.weatherapp.di.component.NetworkComponent;
import com.garrytrue.weatherapp.di.module.ApplicationModule;
import com.garrytrue.weatherapp.di.module.NetworkModule;

public class WeatherApplication extends Application {
    private ApplicationComponent applicationComponent;
    private static Context mAppContext;
    private NetworkComponent networkComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public static WeatherApplication getApplication(Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }


    public static Context getContextApplication() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new
                ApplicationModule(this)).build();
        applicationComponent.inject(this);
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(getApplication(this))).build();
    }
}
