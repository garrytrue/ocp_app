package com.tiv.ocpapp.helpers;

import com.tiv.ocpapp.BuildConfig;
import com.tiv.ocpapp.app.OcpTestApp;
import com.tiv.ocpapp.di.components.ApplicationTestComponent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by tiv on 08.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        application = OcpTestApp.class,
        sdk = 21)
@Ignore
public class BaseTest {
    public  ApplicationTestComponent testComponent;

    @Before
    public void setUp() throws Exception {
        System.out.println("ApplicationComponent " + OcpTestApp.provideApplicationComponent());
        testComponent = (ApplicationTestComponent) OcpTestApp.provideApplicationComponent();
    }


}
