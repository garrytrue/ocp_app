package com.tiv.ocpapp.di.modules;

import com.tiv.ocpapp.ui.mvp.views.IView;
import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 06.07.2016.
 */
@Module
public class StartViewModule {

    @Provides
    @Singleton
    public StartFragmentPresenter provideStartPresenter() {
        return new StartFragmentPresenter();
    }
}
