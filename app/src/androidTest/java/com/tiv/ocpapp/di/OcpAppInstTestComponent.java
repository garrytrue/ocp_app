package com.tiv.ocpapp.di;

import com.tiv.ocpapp.StartInstTestFragmentPresenter;
import com.tiv.ocpapp.app.OcpAndroidTestsApp;
import com.tiv.ocpapp.di.components.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 11.07.2016.
 */
@Component(modules = AppInstrumentalTestsModule.class)
@Singleton
public interface OcpAppInstTestComponent extends ApplicationComponent{
    void inject(OcpAndroidTestsApp app);
    void inject(StartInstTestFragmentPresenter presenter);

}
