package com.tiv.ocpapp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tiv.ocpapp.R;


public class StartFragment extends Fragment {
    public static final String TAG = StartFragment.class.getSimpleName();
    private EditText qNumber;
    private View arrowBtn;
    private OnArrowClickListener mListener;

    public interface OnArrowClickListener {
        void onArrowClicked(String value);
    }

    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        arrowBtn = view.findViewById(R.id.arrow_btn);
        arrowBtn.setOnClickListener(v -> {
            onArrowClicked();
        });
    }

    private void onArrowClicked() {
        if (mListener != null) {
            mListener.onArrowClicked(qNumber.getText().toString());
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
