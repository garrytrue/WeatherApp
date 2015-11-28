package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IWeatherFragmentView;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 25.11.15.
 */
public interface IWeatherFragmentPresenter extends IBaseFragmentPresenter<IWeatherFragmentView> {
    void startLoadData(double latitude, double longitude, String appId);
}
