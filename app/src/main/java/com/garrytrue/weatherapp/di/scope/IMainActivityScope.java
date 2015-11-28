package com.garrytrue.weatherapp.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 24.11.15.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface IMainActivityScope {
}
