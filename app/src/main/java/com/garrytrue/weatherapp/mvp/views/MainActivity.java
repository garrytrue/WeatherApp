package com.garrytrue.weatherapp.mvp.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.garrytrue.weatherapp.R;
import com.garrytrue.weatherapp.di.component.DaggerMainActivityComponent;
import com.garrytrue.weatherapp.di.component.MainActivityComponent;
import com.garrytrue.weatherapp.di.module.MainActivityModule;
import com.garrytrue.weatherapp.mvp.presenters.MainActivityPresenter;
import com.garrytrue.weatherapp.weather_app.WeatherApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainView {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    @Inject
    MainActivityPresenter presenter;
    FragmentManager fragmentManager;
    MainActivityComponent mainActivityComponent;

    private FrameLayout frameLayoutRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        frameLayoutRoot = (FrameLayout) findViewById(R.id.fl_root);
        setupComponent();
        fragmentManager = getSupportFragmentManager();
        setupFragments(savedInstanceState);
    }

    private void setupComponent() {
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(WeatherApplication.getApplication(this).getApplicationComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);

    }

    private void setupFragments(Bundle savedState) {
        if (isPlayServiceAvailable()) {
            Fragment mapFragment = fragmentManager.findFragmentByTag(getString(R.string
                    .tag_map_fragment));
            if (mapFragment == null) {
                mapFragment = MapFragment.newInstance();
            }
            if (savedState == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_root, mapFragment, getString(R.string.tag_map_fragment))
                        .commit();
            }
        }
    }

    private boolean isPlayServiceAvailable() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            return true;
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isPlayServiceAvailable()) {
            presenter.haveGoogleServiceError();
        }
    }

    @Override
    public void popFragmentFromBackStack() {
        fragmentManager.popBackStack();
    }

    @Override
    public void showGoogleServiceError() {
        Snackbar.make(frameLayoutRoot, R.string.msg_no_google_services, Snackbar.LENGTH_LONG).setAction(R
                .string.action_ok, snackBarActionOkListener).show();
    }

    @Override
    public void finishApp() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onPresenterBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private View.OnClickListener snackBarActionOkListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            presenter.needFinishApp();
        }
    };

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }
}
