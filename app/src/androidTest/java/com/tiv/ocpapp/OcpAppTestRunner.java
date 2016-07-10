package com.tiv.ocpapp;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.tiv.ocpapp.app.OcpAndroidTestsApp;

/**
 * Created by garrytrue on 10.07.16.
 */
public class OcpAppTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, OcpAndroidTestsApp.class.getName(), context);
    }
}
