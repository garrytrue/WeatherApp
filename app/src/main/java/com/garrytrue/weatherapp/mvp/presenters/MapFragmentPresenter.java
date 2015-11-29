package com.garrytrue.weatherapp.mvp.presenters;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.garrytrue.weatherapp.weather_app.WeatherApplication;
import com.garrytrue.weatherapp.mvp.views.IMapFragmentView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

public class MapFragmentPresenter implements IMapFragmentPresenter, GoogleApiClient
        .ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String TAG = MapFragmentPresenter.class.getSimpleName();
    private IMapFragmentView view;
    private GoogleApiClient mLocationClient;
    @Inject
    LocationRequest mLocationRequest;

    @Inject
    public MapFragmentPresenter() {
    }


    @Override
    public void init(IMapFragmentView view) {
        this.view = view;
    }

    @Override
    public void onActivityCreated() {
        if (mLocationClient == null)
            buildLocationService();
    }


    @Override
    public void onResume() {
        if (mLocationClient != null && !mLocationClient.isConnected())
            mLocationClient.connect();
    }

     @Override
    public void showWeatherFragment() {
        view.gotoWeatherFragment();
    }


    // GoogleApiClient.ConnectionCallbacks
    @Override
    public void onConnected(Bundle bundle) {
        Location mLocation = LocationServices.FusedLocationApi.getLastLocation(mLocationClient);

        if (mLocation != null) {
            Log.d(TAG, "onConnected: Location " + mLocation);
            LatLng location = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            view.showLocation(location);
        } else {
            Log.d(TAG, "onConnected: Location is NULL");
            view.showNotLocationMsg();
            LocationServices.FusedLocationApi.requestLocationUpdates(mLocationClient,
                    mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        view.showConnectionSuspendedError();
    }

    // GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        view.showConnectionFailedError(connectionResult);
    }

    private synchronized void buildLocationService() {
        mLocationClient = new GoogleApiClient.Builder(WeatherApplication.getContextApplication())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    //LocationCallback
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        view.showLocation(latLng);
        LocationServices.FusedLocationApi.removeLocationUpdates(mLocationClient, this);
        mLocationRequest = null;
    }
}
