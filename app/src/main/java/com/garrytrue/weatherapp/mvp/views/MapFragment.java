package com.garrytrue.weatherapp.mvp.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.garrytrue.weatherapp.R;
import com.garrytrue.weatherapp.mvp.presenters.MapFragmentPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 23.11.15.
 */
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, IMapFragmentView {
    private static final String TAG = "MapFragment";
    private GoogleMap mMap;
    private LatLng mLocation;
    private View rootView;

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    MapFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rootView = view;
        super.onViewCreated(view, savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getMainActivityComponent().inject(this);
        presenter.onActivityCreated();
        presenter.init(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                presenter.showWeatherFragment();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();

    }

    @Override
    public void gotoWeatherFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_root,
                WeatherFragment.newInstance(mLocation.latitude, mLocation.longitude)).addToBackStack("")
                .commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void showConnectionSuspendedError() {

    }

    @Override
    public void showConnectionFailedError(ConnectionResult connectionResult) {

    }

    @Override
    public void showLocation(LatLng location) {
        mLocation = location;
        mMap.addMarker(new MarkerOptions().position(location).title(getString(R.string.msg_click_on_me)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

    @Override
    public void showNotLocationMsg() {
        Snackbar.make(rootView, R.string.msg_turn_on_geolocation, Snackbar.LENGTH_LONG).show();
    }

}
