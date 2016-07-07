package com.tiv.ocpapp.ui.mvp.views;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tiv.ocpapp.R;
import com.tiv.ocpapp.di.components.DaggerStartViewComponent;
import com.tiv.ocpapp.di.modules.StartViewModule;
import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import javax.inject.Inject;


public class StartFragment extends Fragment implements StartFragmentView {
    public static final String TAG = StartFragment.class.getSimpleName();
    private EditText qNumber;
    @Inject
    StartFragmentPresenter startFragmentPresenter;


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
        initUI(view);
        DaggerStartViewComponent.builder().startViewModule(new StartViewModule()).build().inject(this);
        startFragmentPresenter.onCreate(this);
    }

    private void initUI(View view) {
        qNumber = (EditText) view.findViewById(R.id.question_number);
        qNumber.requestFocus();
        View arrowBtn = view.findViewById(R.id.arrow_btn);
        arrowBtn.setOnClickListener(v -> onArrowClicked());
    }

    private void onArrowClicked() {
        startFragmentPresenter.loadQuestionById(qNumber.getText().toString());
    }

    @Override
    public void onNextAction(long nextId) {
        getActivity().getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.from_right_to_left, R.animator.from_left_to_right)
                .replace(R.id.fragment_container, QuestionFragment.newInstance(nextId), QuestionFragment.TAG)
                .addToBackStack(QuestionFragment.TAG)
                .commit();
    }

    @Override
    public void showError(String error) {
        qNumber.setError(String.format(getString(R.string.msg_not_valid_input), error));
        new Handler(Looper.getMainLooper()).postDelayed(() ->
                qNumber.setError(null), 1000);
    }
}
