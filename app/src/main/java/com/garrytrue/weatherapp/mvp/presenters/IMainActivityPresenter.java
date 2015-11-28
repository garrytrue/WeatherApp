package com.garrytrue.weatherapp.mvp.presenters;

public interface IMainActivityPresenter {
    void onPresenterBackPressed();
    void hadConnectionError();
    void hadGoogleServiceError();
    void needFinishApp();
}
