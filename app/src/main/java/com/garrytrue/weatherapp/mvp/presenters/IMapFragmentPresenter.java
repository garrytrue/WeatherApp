package com.garrytrue.weatherapp.mvp.presenters;

import android.location.Location;

import com.garrytrue.weatherapp.mvp.views.IMapFragmentView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 24.11.15.
 */
public interface IMapFragmentPresenter extends IBaseFragmentPresenter<IMapFragmentView> {
    void onActivityCreated();

    void onResume();

    void showWeatherFragment();


}
