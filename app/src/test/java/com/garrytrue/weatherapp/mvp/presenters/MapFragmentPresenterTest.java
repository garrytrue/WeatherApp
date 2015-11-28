package com.garrytrue.weatherapp.mvp.presenters;

import com.garrytrue.weatherapp.mvp.views.IMapFragmentView;
import com.google.android.gms.common.api.GoogleApiClient;

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
public class MapFragmentPresenterTest extends TestCase {
    @Mock
    IMapFragmentView view;
    @Mock
    GoogleApiClient locationClient;

    @Test
    public void testInit() throws Exception {

    }

    @Test
    public void testOnActivityCreated_LocationClientMustBeNotNull() throws Exception {
        assertNotNull(locationClient);
    }

    @Test
    public void testOnResume_LocationClientMustCallConnect() throws Exception {
        Mockito.verify(locationClient).connect();
        assertNotNull(locationClient);
    }

    @Test
    public void testShowWeatherFragment() throws Exception {
//        Mockito.verify(view).gotoWeatherFragment();
    }

    @Test
    public void testOnConnected() throws Exception {
//        Mockito.verify(locationClient).isConnected();
    }

    @Test
    public void testOnConnectionSuspended() throws Exception {

    }

    @Test
    public void testOnConnectionFailed() throws Exception {

    }

    @Test
    public void testCreateLocationRequest() throws Exception {

    }

    @Test
    public void testOnLocationChanged() throws Exception {

    }
}
