package com.tiv.ocpapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    private long currentQuestionNumber;
    private Question currentQuestion;
    private boolean isAnswerClicked = false;
    private final List<CompoundButton> selectedItems = new ArrayList<>();
    private final CheckBox.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> handleCheckBoxAction(isChecked, buttonView);
    private BottomSheetBehavior mBottomSheetBehavior;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Question ID in DB.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(long param1) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putLong(QUESTION_NUMBER, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            currentQuestionNumber = getArguments().getLong(QUESTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        (getActivity()).setTitle(getString(R.string.app_name));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUI(view);
        if (currentQuestion == null) {
            Log.d(TAG, "onViewCreated: Current Question is Null");
            bindData(loadData(currentQuestionNumber));
        } else {
            Log.d(TAG, "onViewCreated: Current Question is not Null");
            bindData(currentQuestion);
        }
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
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        descBtn.setOnClickListener(v -> mBottomSheetBehavior.setState(
                (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                        ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED));
        if (isAnswerClicked) {
            onAnswerBtnClicked();
        }
    }

    private void onNextBtnClicked() {
        long id = currentQuestion.getId();
        DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
        QuestionDao questionDao = session.getQuestionDao();
        if (isLastQuestion(questionDao)) {
            // TODO: 06.06.2016 Need show last question msg
            Snackbar.make(rootLayout, R.string.msg_last_question, Snackbar.LENGTH_SHORT).show();

            return;
        }
        currentQuestion = questionDao.load(++id);
        isAnswerClicked = false;
        bindData(currentQuestion);

    }

    private boolean isLastQuestion(QuestionDao questionDao) {
        long id = currentQuestion.getId();
        long questionCount = questionDao.count();
        return id >= questionCount;
    }

    private void bindData(Question data) {
        Log.d(TAG, "bindData() called with: " + "data = [" + data + "]");
        resetViewsStates();
        question.setText(data.getText());
        description.setText(data.getDescription());
        questionNumber.setText(String.valueOf(data.getId()));
        List<Answer> answers = data.getAnswers();
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (i > answers.size() - 1) {
                checkBoxes.get(i).setVisibility(View.GONE);
                continue;
            }
            checkBoxes.get(i).setText(answers.get(i).getBody());
            checkBoxes.get(i).setTag(R.id.CORRECTNESS_TAG, answers.get(i).getIsCorrect());
        }
        if (isAnswerClicked) {
            highlightAnswers();
        }
    }

    private void resetViewsStates() {
        Log.d(TAG, "resetViewsStates: IsAnsweredClicked " + isAnswerClicked);
        if (isAnswerClicked) {
            return;
        }
        resetCheckBoxesState(checkBoxes);
        selectedItems.clear();
        descBtn.setAlpha(0);
        descBtn.setClickable(false);
        isAnswerClicked = false;
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void resetCheckBoxesState(List<CheckBox> checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            cb.setChecked(false);
            cb.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.black));
            cb.setVisibility(View.VISIBLE);
        }
    }

    private Question loadData(long id) {
        DaoSession session = ((OcpApplication) getActivity().getApplication()).getSession();
        QuestionDao questionDao = session.getQuestionDao();
        currentQuestion = questionDao.load(id);
        return currentQuestion;
    }

//    private int getQuestionDbId(String id) {
//        int dbId;
//        try {
//            dbId = Integer.parseInt(id);
//        } catch (NumberFormatException ex) {
//            Log.e(TAG, "getQuestionDbId: ", ex);
//            dbId = 1;
//        }
//        return dbId;
//    }

    private void handleCheckBoxAction(boolean isChecked, CompoundButton button) {
        if (isChecked) {
            selectedItems.add(button);
        } else {
            selectedItems.remove(button);
        }
    }

    private void onAnswerBtnClicked() {
        isAnswerClicked = true;
        if (!selectedItems.isEmpty()) {
            highlightAnswers();
            descBtn.setClickable(true);
            descBtn.animate().alpha(1).setDuration(1000).start();
        } else {
            // TODO: 02.06.2016 Need show warning
            Snackbar.make(rootLayout, R.string.msg_answers_not_selected, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("all")
    private void highlightAnswers() {
        for (CheckBox cb : checkBoxes) {
            if (cb.getTag(R.id.CORRECTNESS_TAG) == null) {
                continue;
            }
            cb.setTextColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(getActivity(), android.R.color.holo_green_dark)
                    : ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark));
        }
    }
}
