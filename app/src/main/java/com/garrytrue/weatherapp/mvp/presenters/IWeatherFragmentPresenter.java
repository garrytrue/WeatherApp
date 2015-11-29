package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IWeatherFragmentView;


public interface IWeatherFragmentPresenter extends IBaseFragmentPresenter<IWeatherFragmentView> {
    void startLoadData(double latitude, double longitude, String appId);
}
