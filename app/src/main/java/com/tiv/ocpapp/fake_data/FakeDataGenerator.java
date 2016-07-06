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

import javax.inject.Inject;

public class FakeDataGenerator {
//    @Inject
//    DaoSession mSession;
    private static final String TAG = FakeDataGenerator.class.getSimpleName();

    private FakeDataGenerator() {
        OcpApplication.provideApplicationComponent().inject(this);
    }


    private static final String FAKE_STRING = "asfasfLorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id ex bibendum, dignissim velit et, ullamcorper nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In vitae velit hendrerit, euismod purus ac, sodales odio. Cras nunc arcu, blandit vitae mi non, iaculis imperdiet risus. Sed sit amet ex semper, interdum eros eget, posuere diam. Suspendisse finibus ullamcorper venenatis. Duis vel nibh in velit scelerisque cursus. Nam ipsum nibh, dignissim non molestie id, cursus at libero. Fusce lacus arcu, aliquam et sodales eget, lacinia eu dui. Praesent pretium rhoncus magna, at porttitor augue imperdiet eget. In maximus est at libero varius, vitae bibendum purus ornare. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent lacinia condimentum suscipit. Phasellus dolor ligula, consectetur vel lacinia vel, ornare eget nisi.";
    public static final String Q1 = " What is the result of executing the following application? (Choose all that apply.) \n 1: import java.util.concurrent.*; import java.util.stream.*; \n 2: public class BabyPandaBathManager { \n 3:      public static void await(CyclicBarrier cb) { \n 4:          try { \n 5:              cb.await(); \n 6:          } catch (InterruptedException | BrokenBarrierException e) { \n 7:              // Handle exception } \n 8:          } \n 9:      public static void main(String[] args) { \n 10:         final CyclicBarrier cb = new CyclicBarrier(3,()-> System.out. println(\\\"Clean!\\\"));// u1 \n 11:         ExecutorService service = Executors.newScheduledThreadPool(2); \n 12:         IntStream.iterate(1, i-> 1) // u2 \n 11:                  .limit(12) \n 12:                  .forEach(i-> service.submit( // u3 \n 13:                          ()-> await(cb))); // u4 service.shutdown(); \n 14:     } \n 15: } \n \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
public static final String DESC = "H. The code compiles without issue, so C, D, E, and F are incorrect. The key to under- standing this code is to notice that our thread pool size is only 2, but our CyclicBarrier limit is 3. Even though 12 tasks are all successfully submitted to the thread executor service by way of the stream forEach() method, the  rst two tasks will use up both available threads and wait inde nitely. Since a third await() is never executed, the barrier is never broken and the program hangs, making H the only correct answer. Nothing is ever out- putted nor is any exception thrown, so A, B, and G are incorrect. See Chapter 7 for more information.\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
    private static class Holder {
        private static final FakeDataGenerator HOLDER = new FakeDataGenerator();
    }

    public static FakeDataGenerator getInstance() {
        return Holder.HOLDER;
    }

    public void insertOneFakeQuestion(DaoSession session) {
        Question question = new Question();
//        question.setDescription(FAKE_STRING.substring(0, 800));
        question.setDescription(DESC);
//        question.setText(FAKE_STRING.substring(0, 500));
        question.setText(Q1);
        Random random = new Random();
        QuestionDao questionDao = session.getQuestionDao();
        long id = questionDao.insert(question);
        List<Answer> answers = new ArrayList<>(8);
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer();
            answer.setBody(FAKE_STRING.substring(0, random.nextInt(200)));
            answer.setIsCorrect(i % 2 == 0);
            answer.setQuestion(question);
            answers.add(answer);
        }
        AnswerDao answerDao = session.getAnswerDao();
        answerDao.insertInTx(answers);
        Log.d(TAG, "insertOneFakeQuestion: Inserted ID " + id);

    }

//    public void generateQuestionsToDb(OcpApplication application) {
//        for (int i = 0; i < 5; i++) {
//            insertOneFakeQuestion(application.getSession());
//        }
//    }
    public void insertQuestion(DaoSession session, com.example.model.Question q) {
        Question question = new Question();
        question.setDescription(q.getDescription());
        question.setText(q.getText());
        QuestionDao questionDao = session.getQuestionDao();
        long id = questionDao.insert(question);
        List<com.example.model.Answer> answers = q.getAnswers();
        List<Answer> answersDb = new ArrayList<>(answers.size());
        for (com.example.model.Answer a: answers) {

            Answer answer = new Answer();
            answer.setBody(a.getBody());
            answer.setIsCorrect(a.getCorrect());
            answer.setQuestion(question);
            answersDb.add(answer);
        }
        AnswerDao answerDao = session.getAnswerDao();
        answerDao.insertInTx(answersDb);
        Log.d(TAG, "insertOneFakeQuestion: Inserted ID " + id);

    }
}
