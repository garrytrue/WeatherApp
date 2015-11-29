package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IMapFragmentView;

public interface IMapFragmentPresenter extends IBaseFragmentPresenter<IMapFragmentView> {
    void onActivityCreated();

    void onResume();

    void showWeatherFragment();


}
