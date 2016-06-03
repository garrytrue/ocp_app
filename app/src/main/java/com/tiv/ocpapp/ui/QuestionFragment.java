package com.tiv.ocpapp.ui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tiv.ocpapp.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    public static final String TAG = QuestionFragment.class.getSimpleName();
    private static final String QUESTION_NUMBER = "question_number";
    private static final int ANIMATION_Y_DISTANCE = 1000;
    private static final int CORRECTNESS_TAG = 103;
    private TextView question, description, questionNumber;
    private View answerBtn, descBtn;
    private CheckBox answer1, answer2, answer3, answer4;
    private View descBlind;
    private List<CheckBox> checkBoxes;
    private String currentQuestionNumber;

    private OnFragmentInteractionListener mListener;
    private List<CompoundButton> selectedItems = new ArrayList<>();
    private final CheckBox.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> {
        switch (buttonView.getId()) {
            case R.id.cb_id_1:
                handleCheckBoxAction(isChecked, buttonView);
                break;
            case R.id.cb_id_2:
                handleCheckBoxAction(isChecked, buttonView);
                break;
            case R.id.cb_id_3:
                handleCheckBoxAction(isChecked, buttonView);
                break;
            case R.id.cb_id_4:
                handleCheckBoxAction(isChecked, buttonView);
                break;
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Question ID in DB.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(String param1) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_NUMBER, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentQuestionNumber = getArguments().getString(QUESTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
    }

    private void initUI(View view) {
        question = (TextView) view.findViewById(R.id.question_text);
        description = (TextView) view.findViewById(R.id.description);
        questionNumber = (TextView) view.findViewById(R.id.number);
        descBtn = view.findViewById(R.id.desc_btn);
        descBtn.setOnClickListener(v -> {
            descBlind.animate().translationYBy(ANIMATION_Y_DISTANCE).start();
        });
        descBlind = view.findViewById(R.id.desc_blind);
        answerBtn = view.findViewById(R.id.answer_btn);
        answerBtn.setOnClickListener(v -> {
            handleMakeAnswerAction();
        });
        answer1 = (CheckBox) view.findViewById(R.id.cb_id_1);
        answer2 = (CheckBox) view.findViewById(R.id.cb_id_2);
        answer3 = (CheckBox) view.findViewById(R.id.cb_id_3);
        answer4 = (CheckBox) view.findViewById(R.id.cb_id_4);
        checkBoxes = new ArrayList<>(4);
        checkBoxes.add(answer1);
        checkBoxes.add(answer2);
        checkBoxes.add(answer3);
        checkBoxes.add(answer4);
        for (CheckBox cb : checkBoxes) {
            cb.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    private void handleCheckBoxAction(boolean isChecked, CompoundButton button) {
        if (isChecked) {
            selectedItems.add(button);
        } else {
            selectedItems.remove(button);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void handleMakeAnswerAction() {
        if (!selectedItems.isEmpty()) {
            highlightAnswers();
            // TODO: 02.06.2016 Need show description
        } else {
            // TODO: 02.06.2016 Need show warning
        }

    }

    @SuppressWarnings("all")
    private void highlightAnswers() {
        for (CheckBox cb : checkBoxes) {
            cb.setTextColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(getContext(), android.R.color.holo_green_dark)
                    : ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
            cb.setBackgroundColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(getContext(), android.R.color.darker_gray)
                    : ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright));

        }
    }
}
