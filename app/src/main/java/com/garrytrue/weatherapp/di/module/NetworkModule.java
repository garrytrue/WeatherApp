package com.garrytrue.weatherapp.di.module;

import android.app.Application;

import com.garrytrue.weatherapp.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class NetworkModule {
    Application application;


    @Inject
    public NetworkModule(Application application) {
        this.application = application;
    }

//    @Provides
//    @Singleton
//    Cache provideOkHttpCache() {
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
//    }
//
//
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        return gsonBuilder.create();
//    }
//
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient(Cache cache) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setCache(cache);
//        return okHttpClient;
//    }
//

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()))
                .baseUrl(application.getString(R.string.base_url))
                .client(new OkHttpClient().setCache(new Cache(application.getCacheDir(),
                        10 * 1024 * 1024)))
                .build();
        return retrofit;
    }
}
