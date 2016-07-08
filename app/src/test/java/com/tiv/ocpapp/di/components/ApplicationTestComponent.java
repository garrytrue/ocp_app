package com.tiv.ocpapp.di.components;

import com.tiv.ocpapp.app.OcpTestApp;
import com.tiv.ocpapp.di.modules.ApplicationTestModule;
import com.tiv.ocpapp.ui.mvp.presenters.QuestionFragmentPresenterTest;
import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 08.07.2016.
 */
@Singleton
@Component(modules = ApplicationTestModule.class)
public interface ApplicationTestComponent extends ApplicationComponent {
    void inject(OcpTestApp application);
    void  inject(StartFragmentPresenterTest startFragmentPresenterTest);
    void inject(QuestionFragmentPresenterTest questionFragmentPresenterTest);
}
