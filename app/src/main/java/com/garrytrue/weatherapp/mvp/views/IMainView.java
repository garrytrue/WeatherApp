package com.garrytrue.weatherapp.mvp.views;


public interface IMainView {
    void popFragmentFromBackStack();
    void showConnectionError();
    void showGoogleServiceError();

    void finishApp();
}
