package com.tiv.ocpapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tiv.ocpapp.R;
import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.model_dao.Answer;
import com.tiv.ocpapp.model_dao.DaoSession;
import com.tiv.ocpapp.model_dao.Question;
import com.tiv.ocpapp.model_dao.QuestionDao;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    public static final String TAG = QuestionFragment.class.getSimpleName();
    private static final String QUESTION_NUMBER = "question_number";
    private static final int DEFAULT_CHECK_BOX_COUNT = 8;
    private TextView question, description, questionNumber;
    private View descBtn, rootLayout;
    private List<CheckBox> checkBoxes;
    private String currentQuestionNumber;
    private Question currentQuestion;
    private List<CompoundButton> selectedItems = new ArrayList<>();
    private final CheckBox.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> handleCheckBoxAction(isChecked, buttonView);

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
        bindData(loadData(currentQuestionNumber));
    }


    private void initUI(View view) {
        question = (TextView) view.findViewById(R.id.question_text);
        description = (TextView) view.findViewById(R.id.description);
        questionNumber = (TextView) view.findViewById(R.id.number);
        descBtn = view.findViewById(R.id.desc_btn);
        checkBoxes = new ArrayList<>(DEFAULT_CHECK_BOX_COUNT);
        rootLayout = view.findViewById(R.id.root_coordinator_layout);
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_1));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_2));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_3));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_4));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_5));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_6));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_7));
        checkBoxes.add((CheckBox) view.findViewById(R.id.cb_id_8));
        for (CheckBox cb : checkBoxes) {
            cb.setOnCheckedChangeListener(checkedChangeListener);
        }
        View bottomSheet = view.findViewById(R.id.bottom_sheet);
        view.findViewById(R.id.answer_btn).setOnClickListener(v -> onAnswerBtnClicked());
        view.findViewById(R.id.next_btn).setOnClickListener(v -> onNextBtnClicked());
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        descBtn.setOnClickListener(v -> mBottomSheetBehavior.setState(
                (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                        ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED));
    }

    private void onNextBtnClicked() {
        long id = currentQuestion.getId();
        DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
        QuestionDao questionDao = session.getQuestionDao();
        currentQuestion = questionDao.load(++id);
        bindData(currentQuestion);

    }

    private void bindData(Question data) {
        resetViewsStates();
        question.setText(data.getText());
        description.setText(data.getDescription());
        questionNumber.setText(String.valueOf(data.getId()));
        List<Answer> answers = data.getAnswers();
        for (int i = 0; i < checkBoxes.size(); i++) {
            if(i > answers.size()-1){
                checkBoxes.get(i).setVisibility(View.GONE);
                continue;
            }
            checkBoxes.get(i).setText(answers.get(i).getBody());
            checkBoxes.get(i).setTag(R.id.CORRECTNESS_TAG, answers.get(i).getIsCorrect());
        }
    }
    private void resetViewsStates(){
        resetCheckBoxesState(checkBoxes);
        selectedItems.clear();
        descBtn.setAlpha(0);
        descBtn.setClickable(false);
    }

    private void resetCheckBoxesState(List<CheckBox> checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            cb.setChecked(false);
            cb.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.black));
            cb.setVisibility(View.VISIBLE);
        }
    }

    private Question loadData(String id) {
        DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
        QuestionDao questionDao = session.getQuestionDao();
        int qId = getQuestionDbId(id);
        currentQuestion = questionDao.load((long) qId);
        return currentQuestion;
    }

    private int getQuestionDbId(String id) {
        int dbId = -1;
        try {
            dbId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            Log.e(TAG, "getQuestionDbId: ", ex);
            dbId = 1;
        }
        return dbId;
    }

    private void handleCheckBoxAction(boolean isChecked, CompoundButton button) {
        if (isChecked) {
            selectedItems.add(button);
        } else {
            selectedItems.remove(button);
        }
    }

    private void onAnswerBtnClicked() {
        if (!selectedItems.isEmpty()) {
            highlightAnswers();
            descBtn.setClickable(true);
            descBtn.animate().alpha(1).setDuration(1000).start();
        } else {
            // TODO: 02.06.2016 Need show warning
            Snackbar.make(rootLayout, R.string.msg_answers_not_selected, Snackbar.LENGTH_SHORT).show();
        }
    }


    @SuppressWarnings("all")
    private void highlightAnswers() {
        for (CheckBox cb : checkBoxes) {
            cb.setTextColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(getActivity(), android.R.color.holo_green_dark)
                    : ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark));
        }
    }
}
