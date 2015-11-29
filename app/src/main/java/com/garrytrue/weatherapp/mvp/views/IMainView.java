package com.garrytrue.weatherapp.mvp.views;


public interface IMainView {
    void popFragmentFromBackStack();

    void showGoogleServiceError();

    void finishApp();
}
