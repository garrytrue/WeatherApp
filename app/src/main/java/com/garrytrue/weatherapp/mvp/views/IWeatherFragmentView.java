package com.garrytrue.weatherapp.mvp.views;

import com.garrytrue.weatherapp.mvp.model.WeatherModel;

public interface IWeatherFragmentView {
    void updateView(WeatherModel data); // In params need add my model
    void showProgress();
    void hideProgress();
    void showError();
}
