package com.tiv.ocpapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tiv.ocpapp.R;

/**
 * Created by tiv on 03.06.2016.
 */
public class ActivityMain extends AppCompatActivity implements StartFragment.OnArrowClickListener {
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
    public void onArrowClicked(String value) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.from_right_to_left, R.animator.from_left_to_right)
                .replace(R.id.fragment_container, QuestionFragment.newInstance(value), QuestionFragment.TAG)
                .addToBackStack(QuestionFragment.TAG)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getFragmentManager().findFragmentByTag(QuestionFragment.TAG);
        if (getFragmentManager().findFragmentByTag(QuestionFragment.TAG) != null) {
            outState.putString(TAG, QuestionFragment.TAG);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        super.onBackPressed();
    }
}
