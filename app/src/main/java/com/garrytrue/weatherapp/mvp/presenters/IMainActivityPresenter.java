package com.garrytrue.weatherapp.mvp.presenters;

public interface IMainActivityPresenter {
    void onPresenterBackPressed();
    void haveGoogleServiceError();
    void needFinishApp();
}
