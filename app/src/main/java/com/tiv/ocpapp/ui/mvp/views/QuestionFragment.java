package com.tiv.ocpapp.ui.mvp.views;

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
import com.tiv.ocpapp.di.components.DaggerQuestionViewComponent;
import com.tiv.ocpapp.di.modules.QuestionViewModule;
import com.tiv.ocpapp.model_dao.Answer;
import com.tiv.ocpapp.model_dao.Question;
import com.tiv.ocpapp.ui.mvp.presenters.QuestionFragmentPresenter;
import com.tiv.ocpapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QuestionFragment extends Fragment implements IQuestionView {
    public static final String TAG = QuestionFragment.class.getSimpleName();
    private static final String QUESTION_NUMBER = "question_number";
    private static final int DEFAULT_CHECK_BOX_COUNT = 8;
    private TextView question, description, questionNumber;
    private View descBtn, rootLayout;
    private List<CheckBox> checkBoxes;
    private long currentQuestionNumber;
    private final List<Long> selectedAnswersIds = new ArrayList<>();
    private final CheckBox.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> handleCheckBoxAction(isChecked, buttonView);
    private BottomSheetBehavior mBottomSheetBehavior;
    @Inject
    QuestionFragmentPresenter questionFragmentPresenter;


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
        DaggerQuestionViewComponent.builder().questionViewModule(new QuestionViewModule()).build().inject(this);
        questionFragmentPresenter.onCreate(this);
        questionFragmentPresenter.initQuestionById(currentQuestionNumber);
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
        questionFragmentPresenter.loadCurrentQuestion();
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
    }

    private void onNextBtnClicked() {
        questionFragmentPresenter.onNextAction(-1);

    }


    private void bindData(Question data) {
        Log.d(TAG, "bindData() called with: " + "data = [" + data + "]");
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
            checkBoxes.get(i).setTag(R.id.ANSWER_ID_TAG, answers.get(i).getId());
        }
    }

    private void resetCheckBoxesState(List<CheckBox> checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            cb.setChecked(false);
            cb.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.black));
            cb.setVisibility(View.VISIBLE);
        }
    }

    private void handleCheckBoxAction(boolean isChecked, CompoundButton button) {
        if (isChecked) {
            selectedAnswersIds.add((Long) button.getTag(R.id.ANSWER_ID_TAG));
        } else {
            selectedAnswersIds.remove(button.getTag(R.id.ANSWER_ID_TAG));
        }
    }

    private void onAnswerBtnClicked() {
        questionFragmentPresenter.onAnswerAction(selectedAnswersIds);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Respond to the action bar's Up/Home button
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("all")
    private void highlightAnswers(List<Long> correctAnswersIds) {
        for (CheckBox cb : checkBoxes) {
            if (cb.getTag(R.id.ANSWER_ID_TAG) == null) {
                continue;
            }
            cb.setTextColor(correctAnswersIds.contains(cb.getTag(R.id.ANSWER_ID_TAG))
                    ? ContextCompat.getColor(getActivity(), android.R.color.holo_green_dark)
                    : ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark));
        }
    }

    @Override
    public void showError(@Constants.Error String error) {
        switch (error) {
            case Constants.ERROR_ANSWER_NOT_SELECTED:
                Snackbar.make(rootLayout, R.string.msg_answers_not_selected, Snackbar.LENGTH_SHORT).show();
                break;
            case Constants.ERROR_LAST_QUESTION:
                Snackbar.make(rootLayout, R.string.msg_last_question, Snackbar.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalArgumentException("Not supported params");
        }

    }

    @Override
    public void updateData(Question data) {
        bindData(data);
    }

    @Override
    public void answerResponse(List<Long> correctAnswersIds) {
        highlightAnswers(correctAnswersIds);
        switchBottomBtnState(true);
    }

    @Override
    public void resetViews() {
        resetCheckBoxesState(checkBoxes);
        selectedAnswersIds.clear();
        switchBottomBtnState(false);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void switchBottomBtnState(boolean isActive) {
        descBtn.clearAnimation();
        descBtn.setClickable(isActive);
        if (isActive) {
            descBtn.animate().alpha(1).setDuration(500).start();
        } else {
            descBtn.animate().alpha(0).setDuration(500).start();
        }
    }
}
