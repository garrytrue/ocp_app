package com.tiv.ocpapp.utils;

import com.tiv.ocpapp.model_dao.Answer;
import com.tiv.ocpapp.model_dao.Question;

import java.util.List;

import rx.Observable;

/**
 * Created by tiv on 07.07.2016.
 */
public class Mappers {
    public static List<Long> getCorrectAnswersIds(Question current){
        return Observable.from(current.getAnswers()).filter(Answer::getIsCorrect).map(Answer::getId).toList().toBlocking().first();
    }
}
