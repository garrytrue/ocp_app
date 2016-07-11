package com.tiv.ocpapp.di;

import com.tiv.ocpapp.StartInstTestFragmentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 11.07.2016.
 */
@Module
public class StartViewInstTestModule {
    @Provides
    @Singleton
    StartInstTestFragmentPresenter provideMockStartFragmentPresenter(){
        return new StartInstTestFragmentPresenter();
    }
}
