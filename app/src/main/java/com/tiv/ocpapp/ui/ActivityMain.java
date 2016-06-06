package com.tiv.ocpapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tiv.ocpapp.R;

/**
 * Created by tiv on 03.06.2016.
 */
public class ActivityMain extends AppCompatActivity {
    private static final String TAG = ActivityMain.class.getSimpleName();
    private View container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        container = findViewById(R.id.fragment_container);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment_container, StartFragment.newInstance(), StartFragment.TAG).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getFragmentManager().findFragmentByTag(QuestionFragment.TAG);
        if (getFragmentManager().findFragmentByTag(QuestionFragment.TAG) != null) {
            outState.putString(TAG, QuestionFragment.TAG);
        }
        super.onSaveInstanceState(outState);
    }
}
