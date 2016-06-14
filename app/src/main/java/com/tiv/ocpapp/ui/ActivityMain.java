package com.tiv.ocpapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiv.ocpapp.R;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = ActivityMain.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
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
