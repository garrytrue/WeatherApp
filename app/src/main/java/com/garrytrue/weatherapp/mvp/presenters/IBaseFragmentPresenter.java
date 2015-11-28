package com.garrytrue.weatherapp.mvp.presenters;

public interface IBaseFragmentPresenter<T> {
    void init(T view);
}
