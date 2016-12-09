package com.tiv.ocpapp.di;

import com.tiv.ocpapp.RepositoryTest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 11.07.2016.
 */
@Module
public class AppInstrumentalTestsModule {
    @Singleton
    @Provides
    RepositoryTest provideRepositoryMockModule(){
        return new RepositoryTest();
    }
}
