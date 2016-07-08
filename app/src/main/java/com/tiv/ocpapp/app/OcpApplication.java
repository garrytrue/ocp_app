package com.tiv.ocpapp.app;

import android.app.Application;

import com.tiv.ocpapp.di.components.ApplicationComponent;
import com.tiv.ocpapp.di.components.DaggerApplicationComponent;
import com.tiv.ocpapp.di.modules.ApplicationModule;
import com.tiv.ocpapp.di.modules.RepositoryModule;

import javax.inject.Inject;


public class OcpApplication extends Application {

    private static ApplicationComponent mApplicationComponent;
    @Inject
    RepositoryModule mRepositoryModule;

    @Override
    public void onCreate() {
        super.onCreate();
        configApplicationComponent();
        configRepositoryModule();
    }


    public static ApplicationComponent provideApplicationComponent() {
        return mApplicationComponent;
    }

    public ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    }

    public void configApplicationComponent() {
        mApplicationComponent =
                buildApplicationComponent();
        mApplicationComponent.inject(this);
    }

    public void configRepositoryModule() {
        mRepositoryModule.initRepositoryModule(getApplicationContext());
        mRepositoryModule.putDataToDb();
    }
}
