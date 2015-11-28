package com.garrytrue.weatherapp.mvp.views;

import android.location.Location;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 24.11.15.
 */
public interface IMapFragmentView {
    void gotoWeatherFragment();

    void showProgress();

    void hideProgress();

    void showConnectionSuspendedError();

    void showConnectionFailedError(ConnectionResult connectionResult);

    void showLocation(LatLng location);

    void showNotLocationMsg();
}
