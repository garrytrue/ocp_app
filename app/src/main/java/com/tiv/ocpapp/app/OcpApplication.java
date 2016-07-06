package com.tiv.ocpapp.app;

import android.app.Application;
import android.util.Log;

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
        mApplicationComponent =
                buildApplicationComponent();
        mApplicationComponent.inject(this);
        mRepositoryModule.putDataToDb();
    }


    public static ApplicationComponent provideApplicationComponent() {
        return mApplicationComponent;
    }

    public ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
}
