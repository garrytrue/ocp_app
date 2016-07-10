package com.tiv.ocpapp.app;

import com.tiv.ocpapp.di.components.ApplicationComponent;
import com.tiv.ocpapp.di.components.DaggerApplicationTestComponent;

/**
 * Created by garrytrue on 10.07.16.
 */
public class OcpAndroidTestsApp extends OcpApplication {
    @Override
    public ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationTestComponent.builder().applicationTestModule(new ApplicationTestModule()).build();
    }
}
