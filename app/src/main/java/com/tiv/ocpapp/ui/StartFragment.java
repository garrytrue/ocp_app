package com.tiv.ocpapp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
    private OnArrowClickListener mListener;

    public interface OnArrowClickListener {
        void onArrowClicked(long questionId);

        void notValidInput(long questionCount);
    }

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
        View arrowBtn = view.findViewById(R.id.arrow_btn);
        arrowBtn.setOnClickListener(v -> onArrowClicked());
    }

    private void onArrowClicked() {
        if (mListener != null) {
            DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
            QuestionDao questionDao = session.getQuestionDao();
            long lastQuestionId = questionDao.count();
            long inputQuestionId;
            if (TextUtils.isEmpty(qNumber.getText().toString())) {
                inputQuestionId = 1;
            } else {
                inputQuestionId = Long.parseLong(qNumber.getText().toString());
            }
            Log.d(TAG, "onArrowClicked: LoastId " + lastQuestionId + " InputID " + inputQuestionId);
            if (inputQuestionId > lastQuestionId) {
                mListener.notValidInput(lastQuestionId);
                return;
            }
            mListener.onArrowClicked(inputQuestionId);
        }
    }


    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnArrowClickListener) {
            mListener = (OnArrowClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnArrowClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
