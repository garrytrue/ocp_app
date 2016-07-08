package com.tiv.ocpapp.app;


import com.tiv.ocpapp.di.components.ApplicationComponent;
import com.tiv.ocpapp.di.components.DaggerApplicationTestComponent;
import com.tiv.ocpapp.di.modules.ApplicationTestModule;
import com.tiv.ocpapp.di.modules.RepositoryModule;

import javax.inject.Inject;

/**
 * Created by tiv on 08.07.2016.
 */
public class OcpTestApp extends OcpApplication {
    @Inject
    RepositoryModule repositoryTestModule;

    @Override
    public ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationTestComponent.builder().applicationTestModule(new ApplicationTestModule()).build();
    }

    @Override
    public void configApplicationComponent() {
        super.configApplicationComponent();
    }
}
