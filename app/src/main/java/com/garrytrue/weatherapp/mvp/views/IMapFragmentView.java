package com.garrytrue.weatherapp.mvp.views;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.model.LatLng;

public interface IMapFragmentView {
    void gotoWeatherFragment();

    void showConnectionSuspendedError();

    void showConnectionFailedError(ConnectionResult connectionResult);

    void showLocation(LatLng location);

    void showNotLocationMsg();
}
