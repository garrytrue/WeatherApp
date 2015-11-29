package com.garrytrue.weatherapp.mvp.presenters;

import android.util.Log;

import com.garrytrue.weatherapp.mvp.model.WeatherModel;
import com.garrytrue.weatherapp.mvp.views.IWeatherFragmentView;
import com.garrytrue.weatherapp.network.FillModelTask;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class WeatherFragmentPresenter implements IWeatherFragmentPresenter {
    private static final String TAG = WeatherFragmentPresenter.class.getSimpleName();
    private IWeatherFragmentView view;
    @Inject
    protected Retrofit retrofit;

    @Inject
    public WeatherFragmentPresenter() {
    }

    @Override
    public void init(IWeatherFragmentView view) {
        this.view = view;
    }

    @Override
    public void startLoadData(double latitude, double longitude, String appId) {
        view.showProgress();
        Log.d(TAG, "startLoadData() called with: " + "latitude = [" + latitude + "], longitude = [" + longitude + "], appId = [" + appId + "]");
        Map<String, String> queryMap = new LinkedHashMap<>();
        queryMap.put("lat", String.valueOf(latitude));
        queryMap.put("lon", String.valueOf(longitude));
        queryMap.put("appid", appId);
        queryMap.put("units","metric");
        FillModelTask task = retrofit.create(FillModelTask.class);
        Call<WeatherModel> call = task.getModel(queryMap);
        call.enqueue(new WeatherCallback());
    }


    class WeatherCallback implements Callback<WeatherModel> {

        @Override
        public void onResponse(Response<WeatherModel> response, Retrofit retrofit) {
            Log.d(TAG, "onResponse: " + response.code() + " " + response.errorBody() + " " +
                    "" + response.body());
            view.hideProgress();
            if (response.isSuccess()) {
                WeatherModel mainModel = response.body();
                view.updateView(mainModel);
            } else {
                view.showError();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e(TAG, "onFailure: ", t);
            view.showError();
        }
    }

}