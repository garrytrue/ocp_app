package com.tiv.ocpapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by tiv on 08.07.2016.
 */
@Module
public class ApplicationTestModule {

    @Singleton
    @Provides
    RepositoryModule provideRepositoryMockModule(){
        return mock(RepositoryModule.class);
    }
}
