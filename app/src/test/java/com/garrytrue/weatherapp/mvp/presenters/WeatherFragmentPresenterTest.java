package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IWeatherFragmentView;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by TorbaIgor (garrytrue@yandex.ru) on 28.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherFragmentPresenterTest extends TestCase {
    @Mock
    IWeatherFragmentView view;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testInit() throws Exception {

    }

    @Test
    public void testStartLoadData() throws Exception {
        Mockito.verify(view).showProgress();
    }

    @Test
    public void testOnResponse() throws Exception {
        Mockito.verify(view).showProgress();
    }

    @Test
    public void testOnFailure() throws Exception {
//        Mockito.verify(view).showError();
    }
}
