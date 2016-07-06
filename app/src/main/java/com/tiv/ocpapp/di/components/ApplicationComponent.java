package com.tiv.ocpapp.di.components;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.di.modules.ApplicationModule;
import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.ui.mvp.presenters.QuestionFragmentPresenter;
import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 05.07.2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(OcpApplication application);

    void inject(FakeDataGenerator fakeDataGenerator);

    void inject(StartFragmentPresenter presenter);

    void inject(QuestionFragmentPresenter presenter);

}
