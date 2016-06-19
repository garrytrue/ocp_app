package com.tiv.ocpapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tiv.ocpapp.R;
import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.model_dao.DaoSession;
import com.tiv.ocpapp.model_dao.QuestionDao;


public class StartFragment extends Fragment {
    public static final String TAG = StartFragment.class.getSimpleName();
    private EditText qNumber;


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
    }

    private void initUI(View view) {
        qNumber = (EditText) view.findViewById(R.id.question_number);
        qNumber.requestFocus();
        View arrowBtn = view.findViewById(R.id.arrow_btn);
        arrowBtn.setOnClickListener(v -> onArrowClicked());
    }

    private void onArrowClicked() {
        DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
        QuestionDao questionDao = session.getQuestionDao();
        long lastQuestionId = questionDao.count();
        long inputQuestionId = getInputQuestionId();
        Log.d(TAG, "onArrowClicked: LastId " + lastQuestionId + " InputID " + inputQuestionId);
        if (inputQuestionId > lastQuestionId) {
            errorAction(lastQuestionId);
            return;
        }
        startQuestionFragment(inputQuestionId);
    }

    private long getInputQuestionId() {
        long inputQuestionId;
        if (TextUtils.isEmpty(qNumber.getText().toString())) {
            inputQuestionId = 1;
        } else {
            inputQuestionId = Long.parseLong(qNumber.getText().toString());
            if(inputQuestionId == 0){
                inputQuestionId = 1;
            }
        }
        return inputQuestionId;
    }

    private void errorAction(long lastQuestionId) {
        qNumber.setError(String.format(getString(R.string.msg_not_valid_input), lastQuestionId));
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

}
