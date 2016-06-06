package com.tiv.ocpapp.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tiv.ocpapp.R;
import com.tiv.ocpapp.mvp.StartScreenContract;
import com.tiv.ocpapp.mvp.StartViewPresenterImpl;


public class StartFragment extends Fragment implements StartScreenContract.StartView {
    public static final String TAG = StartFragment.class.getSimpleName();
    private EditText qNumber;
    private StartScreenContract.Presenter mPresenter;


    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = new StartViewPresenterImpl();
        mPresenter.attachToView(this);
        initUI(view);
    }

    private void initUI(View view) {
        qNumber = (EditText) view.findViewById(R.id.question_number);
        qNumber.requestFocus();
        View arrowBtn = view.findViewById(R.id.arrow_btn);
        arrowBtn.setOnClickListener(v -> onArrowClicked(qNumber.getText().toString()));
    }

    private void onArrowClicked(String str) {
        mPresenter.onLoadById(str);
    }

    private void errorAction(long lastQuestionId) {
        qNumber.setError(String.format(getString(R.string.msg_max_count_violation), lastQuestionId));
        new Handler(Looper.getMainLooper()).postDelayed(() ->
                qNumber.setError(null), 1000);
    }

    private void startQuestionFragment(long questionId) {
        getActivity().getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.from_right_to_left, R.animator.from_left_to_right)
                .replace(R.id.fragment_container, QuestionFragment.newInstance(questionId), QuestionFragment.TAG)
                .addToBackStack(QuestionFragment.TAG)
                .commit();
    }

    @Override
    public void showDbIdViolation(long maxCount) {
        errorAction(maxCount);
    }

    @Override
    public void showNextScreen(long questionId) {
        startQuestionFragment(questionId);
    }

    @Override
    public Context provideContext() {
        return getActivity();
    }
}
