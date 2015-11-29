package com.garrytrue.weatherapp.mvp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garrytrue.weatherapp.R;
import com.garrytrue.weatherapp.weather_app.WeatherApplication;
import com.garrytrue.weatherapp.mvp.model.WeatherModel;
import com.garrytrue.weatherapp.mvp.presenters.WeatherFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class WeatherFragment extends Fragment implements IWeatherFragmentView {
    private static final String BUNDLE_KEY_LOCATION = "BUNDLE_KEY_LOCATION";
    private static final String TAG = "WeatherFragment";
    private double longitude = -1;
    private double latitude = -1;
    private ImageView weatherImage;
    private View commonInfo, fullInfo, progressBar;
    private TextView cityName, temperature, desc, windSpeed, windDeg, tempMax, tempMin, seaLvl,
            grndLvl, clouds, rain, show, cityId, pressure, humidity, calcTime, sunsetTime,
            sunriseTime, country, tvLat, tvLon;

    @Inject
    WeatherFragmentPresenter presenter;

    public static WeatherFragment newInstance(double latitude, double longitude) {
        Bundle args = new Bundle();
        args.putDoubleArray(BUNDLE_KEY_LOCATION, new double[]{latitude, longitude});
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArguments();
        initUI(view);
    }

    private void initArguments() {
        if (getArguments() != null) {
            double[] array = getArguments().getDoubleArray(BUNDLE_KEY_LOCATION);
            latitude = array[0];
            longitude = array[1];
        }
    }

    private void initUI(View v) {
        weatherImage = (ImageView) v.findViewById(R.id.im_weather_icon);
        commonInfo = v.findViewById(R.id.ll_common_info);
        fullInfo = v.findViewById(R.id.ll_full_info);
        progressBar = v.findViewById(R.id.progressBar);
        cityName = (TextView) v.findViewById(R.id.tv_city_name);
        temperature = (TextView) v.findViewById(R.id.tv_temp);
        desc = (TextView) v.findViewById(R.id.tv_desc);
        windSpeed = (TextView) v.findViewById(R.id.tv_wind_speed);
        windDeg = (TextView) v.findViewById(R.id.tv_wind_deg);
        tempMax = (TextView) v.findViewById(R.id.tv_max_temp);
        tempMin = (TextView) v.findViewById(R.id.tv_min_temp);
        seaLvl = (TextView) v.findViewById(R.id.tv_sea_lvl);
        grndLvl = (TextView) v.findViewById(R.id.tv_grnd_lvl);
        clouds = (TextView) v.findViewById(R.id.tv_clouds);
        show = (TextView) v.findViewById(R.id.tv_snow);
        rain = (TextView) v.findViewById(R.id.tv_rain);
        cityId = (TextView) v.findViewById(R.id.tv_city_id);
        pressure = (TextView) v.findViewById(R.id.tv_pressure);
        humidity = (TextView) v.findViewById(R.id.tv_humidity);
        calcTime = (TextView) v.findViewById(R.id.tv_calc_time);
        sunsetTime = (TextView) v.findViewById(R.id.tv_sunset);
        sunriseTime = (TextView) v.findViewById(R.id.tv_sunrise);
        country = (TextView) v.findViewById(R.id.tv_country);
        tvLon = (TextView) v.findViewById(R.id.tv_longitude);
        tvLat = (TextView) v.findViewById(R.id.tv_latitude);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getMainActivityComponent().inject(this);
        WeatherApplication.getApplication(getActivity()).getNetworkComponent().inject(presenter);
        presenter.init(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.startLoadData(latitude, longitude, getString(R.string.open_weather_api_key));
    }

    // IWeatherFragmentView method implementation
    @Override
    public void updateView(WeatherModel data) {
        Log.d(TAG, "updateView: IconEndPoint " + String.format(getString(R.string.image_endpoint), data.getWeather().get(0).getIcon()));
        Picasso.with(getActivity())
                .load(String.format(getString(R.string.image_endpoint), data.getWeather().get(0).getIcon()))
                .placeholder(R.drawable.loading_animation)
                .into(weatherImage);
        cityName.setText(String.format(getString(R.string.lbl_city_name), data.getName()));
        temperature.setText(String.format(getString(R.string.lbl_temp), data.getMain().getTemp()));
        desc.setText(String.format(getString(R.string.lbl_desc), data.getWeather().get(0).getDescription()));
        windSpeed.setText(String.format(getString(R.string.lbl_wind_speed), data.getWind().getSpeed()));
        windDeg.setText(String.format(getString(R.string.lbl_wind_deg), data.getWind().getDeg()));
        tempMax.setText(String.format(getString(R.string.lbl_max_temp), data.getMain().getTempMax()));
        tempMin.setText(String.format(getString(R.string.lbl_min_temp), data.getMain().getTempMin()));
        seaLvl.setText(String.format(getString(R.string.lbl_sea_level), data.getMain().getSeaLevel()));
        grndLvl.setText(String.format(getString(R.string.lbl_grnd_level), data.getMain().getGrndLevel()));
        clouds.setText(String.format(getString(R.string.lbl_clods), data.getClouds().getAll()));
        if (data.getSnow() == null) {
            show.setVisibility(View.GONE);
        } else {
            show.setText(String.format(getString(R.string.lbl_snow), data.getSnow().get3h()));
        }
        if (data.getRain() == null) {
            rain.setVisibility(View.GONE);
        } else {
            rain.setText(String.format(getString(R.string.lbl_rain), data.getRain().get3h()));
        }
        cityId.setText(String.format(getString(R.string.lbl_city_id), data.getId()));
        pressure.setText(String.format(getString(R.string.lbl_pressure), data.getMain().getPressure()));
        humidity.setText(String.format(getString(R.string.lbl_humidity), data.getMain().getHumidity()));
        country.setText(String.format(getString(R.string.lbl_country_code), data.getSys().getCountry()));
        tvLat.setText(String.format(getString(R.string.lbl_latitude), data.getCoord().getLat()));
        tvLon.setText(String.format(getString(R.string.lbl_longitude), data.getCoord().getLon()));
        calcTime.setText(String.format(getString(R.string.lbl_time), dateTimeConverter(data.getDt())));
        sunsetTime.setText(String.format(getString(R.string.lbl_sunset_time), dateTimeConverter
                (data.getSys().getSunset())));
        sunriseTime.setText(String.format(getString(R.string.lbl_sunrise_time), dateTimeConverter
                (data.getSys().getSunrise())));
    }

    @Override
    public void showProgress() {
        Log.d(TAG, "showProgress: ");
        showInfoViews(false);

    }

    @Override
    public void hideProgress() {
        Log.d(TAG, "hideProgress: ");
        showInfoViews(true);
    }

    @Override
    public void showError() {
        Log.d(TAG, "showError() ");
        progressBar.setVisibility(View.INVISIBLE);
        Snackbar.make(weatherImage, R.string.msg_could_not_load_data, Snackbar.LENGTH_LONG).show();

    }

    private void showInfoViews(boolean needShow) {
        commonInfo.setVisibility(needShow ? View.VISIBLE : View.GONE);
        fullInfo.setVisibility(needShow ? View.VISIBLE : View.GONE);
        weatherImage.setVisibility(needShow ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(!needShow ? View.VISIBLE : View.GONE);
    }

    private String dateTimeConverter(long timestamp) {
        long dv = Long.valueOf(timestamp) * 1000;// its need to be in milisecond
        Date df = new java.util.Date(dv);
        return new SimpleDateFormat("MMM dd, yy HH:mm", Locale.getDefault()).format(df);
    }
}