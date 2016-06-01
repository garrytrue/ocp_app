package com.tiv.ocpapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tiv.ocpapp.R;

public class MainActivity extends AppCompatActivity {
    private TextView question, description;
    private EditText questionNumber;
    private View answerBtn, descBtn;
    private CheckBox answer1, answer2, answer3, answer4;
    private View descBlind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        question = (TextView) findViewById(R.id.question_text);
        description = (TextView) findViewById(R.id.description);
        descBtn = findViewById(R.id.desc_btn);
        descBlind = findViewById(R.id.desc_blind);
        answerBtn = findViewById(R.id.answer_btn);
        questionNumber = (EditText) findViewById(R.id.question_id);
        answer1 = (CheckBox) findViewById(R.id.cb_id_1);
        answer2 = (CheckBox) findViewById(R.id.cb_id_2);
        answer3 = (CheckBox) findViewById(R.id.cb_id_3);
        answer4 = (CheckBox) findViewById(R.id.cb_id_4);
    }
}
