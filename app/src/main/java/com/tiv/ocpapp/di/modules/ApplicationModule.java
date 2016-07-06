package com.tiv.ocpapp.di.modules;

import com.tiv.ocpapp.app.OcpApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 05.07.2016.
 */
@Module
public class ApplicationModule {
    private final OcpApplication application;

    public ApplicationModule(OcpApplication application) {
        this.application = application;
    }
    @Singleton
    @Provides
    public RepositoryModule provideRepositoryModule(){
        return new RepositoryModule(application.getApplicationContext());
    }
}
