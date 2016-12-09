package com.tiv.ocpapp.app;

import com.tiv.ocpapp.RepositoryTest;
import com.tiv.ocpapp.di.AppInstrumentalTestsModule;
import com.tiv.ocpapp.di.DaggerOcpAppInstTestComponent;
import com.tiv.ocpapp.di.components.ApplicationComponent;

import javax.inject.Inject;

/**
 * Created by garrytrue on 10.07.16.
 */
public class OcpAndroidTestsApp extends OcpApplication {
    @Inject
    RepositoryTest mRepositoryTest;
    @Override
    public ApplicationComponent buildApplicationComponent() {
        return DaggerOcpAppInstTestComponent.builder().appInstrumentalTestsModule(new AppInstrumentalTestsModule()).build();
    }
}
