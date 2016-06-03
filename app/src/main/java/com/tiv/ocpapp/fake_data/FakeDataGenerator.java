package com.tiv.ocpapp.fake_data;

import android.util.Log;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.model_dao.Answer;
import com.tiv.ocpapp.model_dao.AnswerDao;
import com.tiv.ocpapp.model_dao.DaoSession;
import com.tiv.ocpapp.model_dao.Question;
import com.tiv.ocpapp.model_dao.QuestionDao;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataGenerator {
    private static final String TAG = FakeDataGenerator.class.getSimpleName();
    private FakeDataGenerator() {
    }

    ;
    private static final String FAKE_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id ex bibendum, dignissim velit et, ullamcorper nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In vitae velit hendrerit, euismod purus ac, sodales odio. Cras nunc arcu, blandit vitae mi non, iaculis imperdiet risus. Sed sit amet ex semper, interdum eros eget, posuere diam. Suspendisse finibus ullamcorper venenatis. Duis vel nibh in velit scelerisque cursus. Nam ipsum nibh, dignissim non molestie id, cursus at libero. Fusce lacus arcu, aliquam et sodales eget, lacinia eu dui. Praesent pretium rhoncus magna, at porttitor augue imperdiet eget. In maximus est at libero varius, vitae bibendum purus ornare. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent lacinia condimentum suscipit. Phasellus dolor ligula, consectetur vel lacinia vel, ornare eget nisi.";

    private static class Holder {
        private static final FakeDataGenerator HOLDER = new FakeDataGenerator();
    }

    public static FakeDataGenerator getInstance() {
        return Holder.HOLDER;
    }

    public void insertOneFakeQuestion(DaoSession session) {
        Question question = new Question();
        question.setDescription(FAKE_STRING.substring(0, 800));
        question.setText(FAKE_STRING.substring(0, 500));
        Random random = new Random();
        List<Answer> answers = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer();
            answer.setBody(FAKE_STRING.substring(0, random.nextInt(200)));
            if(i%2 == 0){
                answer.setIsCorrect(true);
            }
            answer.setQuestionId(question.getId());
            answers.add(answer);
        }
        AnswerDao answerDao = session.getAnswerDao();
        answerDao.insertInTx(answers);
        QuestionDao questionDao = session.getQuestionDao();
        long id = questionDao.insert(question);
        Log.d(TAG, "insertOneFakeQuestion: Inserted ID " + id);
        
    }

    public void generate5QuestionsToDb(OcpApplication application){
        for (int i = 0; i < 5; i++) {
            insertOneFakeQuestion(application.getSession());
        }
    }
}
