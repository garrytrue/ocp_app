package com.tiv.ocpapp.di;

import com.tiv.ocpapp.StartFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 11.07.2016.
 */
@Singleton
@Component(modules = StartViewInstTestModule.class)
public interface StartViewInstTestComponent {
    void inject(StartFragmentTest startFragmentTest);
}
