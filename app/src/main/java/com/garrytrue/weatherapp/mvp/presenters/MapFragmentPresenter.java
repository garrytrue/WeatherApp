package com.garrytrue.weatherapp.mvp.presenters;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.garrytrue.weatherapp.app.WeatherApplication;
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
            startLocationService();
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
            double latitude = mLocation.getLatitude();
            double longitude = mLocation.getLongitude();
            Log.d(TAG, "onConnected: LAT" + latitude + " Lon " + longitude);
            LatLng location = new LatLng(latitude, longitude);
            view.showLocation(location);
        } else {
            Log.d(TAG, "onConnected: Location is NULL");
            view.showNotLocationMsg();
            createLocationRequest();
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

    private synchronized void startLocationService() {
        mLocationClient = new GoogleApiClient.Builder(WeatherApplication.getContextApplication())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    //LocationCallback
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        view.showLocation(latLng);
        LocationServices.FusedLocationApi.removeLocationUpdates(mLocationClient, this);
        mLocationRequest = null;
    }
}
