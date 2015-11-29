package com.garrytrue.weatherapp.mvp.presenters;

public interface IMainActivityPresenter {
    void onPresenterBackPressed();
    void hadGoogleServiceError();
    void needFinishApp();
}
