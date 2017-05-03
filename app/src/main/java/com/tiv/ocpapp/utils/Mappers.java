package com.tiv.ocpapp.utils;

import com.tiv.ocpapp.model_dao.Answer;
import com.tiv.ocpapp.model_dao.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by tiv on 07.07.2016.
 */
public class Mappers {
    public static List<Long> getCorrectAnswersIds(Question current) {
        List<Long> ids = new ArrayList<>();
        Iterator<Long> longIterator =
                Observable.fromIterable(current.getAnswers())
                        .filter(Answer::getIsCorrect)
                        .map(Answer::getId).blockingIterable().iterator();
        while (longIterator.hasNext()) {
            ids.add(longIterator.next());
        }

        return ids;
    }
}
