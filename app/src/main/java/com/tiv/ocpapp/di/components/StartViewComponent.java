package com.tiv.ocpapp.di.components;

import com.tiv.ocpapp.di.modules.StartViewModule;
import com.tiv.ocpapp.ui.mvp.views.StartFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 06.07.2016.
 */
@Singleton
@Component(modules = StartViewModule.class)
public interface StartViewComponent {
    void inject(StartFragment startFragment);
}
