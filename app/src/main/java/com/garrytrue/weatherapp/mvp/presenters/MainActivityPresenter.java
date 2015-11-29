package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IMainView;

import javax.inject.Inject;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainView mainView;

    @Inject
    public MainActivityPresenter(IMainView view) {
        mainView = view;
    }

    @Override
    public void onPresenterBackPressed() {
        mainView.popFragmentFromBackStack();
    }

    @Override
    public void hadGoogleServiceError() {
        mainView.showGoogleServiceError();
    }

    @Override
    public void needFinishApp() {
        mainView.finishApp();
    }
}
