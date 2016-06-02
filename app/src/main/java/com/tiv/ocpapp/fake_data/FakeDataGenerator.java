package com.tiv.ocpapp.fake_data;

import com.tiv.ocpapp.model.Answer;
import com.tiv.ocpapp.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataGenerator {
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

    public Question getOneFakeQuestion() {
        Question question = new Question();
        question.setId(11);
        question.setDescription(FAKE_STRING.substring(0, 800));
        question.setText(FAKE_STRING.substring(0, 500));
        Random random = new Random();
        List<Answer> answers = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer();
            answer.setId(i);
            answer.setText(FAKE_STRING.substring(0, random.nextInt(200)));
            answers.add(answer);
            if(i%2 == 0){
                answer.setCorrect(true);
            }
        }
        question.setAnswers(answers);
        return question;
    }
}
