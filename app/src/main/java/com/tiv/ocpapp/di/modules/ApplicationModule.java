package com.tiv.ocpapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 05.07.2016.
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public RepositoryModule provideRepositoryModule(){
        return new RepositoryModule();
    }
}
