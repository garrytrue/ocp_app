package com.tiv.ocpapp.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.tiv.ocpapp.R;
import com.tiv.ocpapp.model.Question;
import com.tiv.ocpapp.mvp.MainPresenter;
import com.tiv.ocpapp.mvp.MainPresenterImpl;
import com.tiv.ocpapp.mvp.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int ANIMATION_Y_DISTANCE = 1000;
    private static final int CORRECTNESS_TAG = 103;
    private TextView question, description;
    private EditText questionNumber;
    private View answerBtn, descBtn;
    private CheckBox answer1, answer2, answer3, answer4;
    private View descBlind;
    private MainPresenter presenter;
    private List<CheckBox> checkBoxes;
    private List<CompoundButton> selectedItems = new ArrayList<>();
    private CheckBox.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        presenter = new MainPresenterImpl(this);
        loadData(11);
    }

    private void initUI() {
        question = (TextView) findViewById(R.id.question_text);
        description = (TextView) findViewById(R.id.description);
        descBtn = findViewById(R.id.desc_btn);
        descBtn.setOnClickListener(v -> {
            descBlind.animate().translationYBy(ANIMATION_Y_DISTANCE).start();
        });
        descBlind = findViewById(R.id.desc_blind);
        answerBtn = findViewById(R.id.answer_btn);
        answerBtn.setOnClickListener(v -> {
            handleMakeAnswerAction();
        });
        questionNumber = (EditText) findViewById(R.id.number);
        answer1 = (CheckBox) findViewById(R.id.cb_id_1);
        answer2 = (CheckBox) findViewById(R.id.cb_id_2);
        answer3 = (CheckBox) findViewById(R.id.cb_id_3);
        answer4 = (CheckBox) findViewById(R.id.cb_id_4);
        checkBoxes = new ArrayList<>(4);
        checkBoxes.add(answer1);
        checkBoxes.add(answer2);
        checkBoxes.add(answer3);
        checkBoxes.add(answer4);
        for (CheckBox cb : checkBoxes) {
            cb.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    private void handleMakeAnswerAction() {
        if (!selectedItems.isEmpty()) {
            highlightAnswers();
            // TODO: 02.06.2016 Need show description
        } else {
            // TODO: 02.06.2016 Need show warning 
        }

    }
    }

    private void highlightAnswers() {
        for (CheckBox cb : checkBoxes) {
            cb.setTextColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(this, android.R.color.holo_green_dark)
                    : ContextCompat.getColor(this, android.R.color.holo_red_dark));
            cb.setBackgroundColor(((boolean) cb.getTag(R.id.CORRECTNESS_TAG))
                    ? ContextCompat.getColor(this, android.R.color.darker_gray)
                    : ContextCompat.getColor(this, android.R.color.holo_blue_bright));

        }
    }

    private boolean isAnswersCorrect() {
        boolean result = true;
        for (CompoundButton cb : selectedItems) {
            boolean bl = (boolean) cb.getTag(R.id.CORRECTNESS_TAG);
            Log.d(TAG, "handleMakeAnswerAction: isCorrect " + bl);
            result &= bl;
        }
        Log.d(TAG, "handleMakeAnswerAction: Result " + result);
        return result;
    }

    @Override
    public void loadData(int id) {
        presenter.loadData(id);
    }

    @Override
    public void onDataLoaded(Question question) {
        updateUI(question);
    }

    private void updateUI(Question data) {
        question.setText(data.getText());
        description.setText(data.getDescription());
        questionNumber.setText(String.valueOf(data.getId()));
        Log.d(TAG, "updateUI: " + checkBoxes.size());
        for (int i = 0; i < checkBoxes.size(); i++) {
            Log.d(TAG, "updateUI: " + data.getAnswers().get(i).getText());
            checkBoxes.get(i).setText(data.getAnswers().get(i).getText());
            Log.d(TAG, "updateUI: I= " + i + " IsCorrect " + data.getAnswers().get(i).isCorrect());
            checkBoxes.get(i).setTag(R.id.CORRECTNESS_TAG, data.getAnswers().get(i).isCorrect());
        }
    }

    private void handleCheckBoxAction(boolean isChecked, CompoundButton button) {
        if (isChecked) {
            selectedItems.add(button);
        } else {
            selectedItems.remove(button);
        }
    }
}
