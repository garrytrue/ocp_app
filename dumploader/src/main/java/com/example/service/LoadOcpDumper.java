package com.example.service;

import com.example.model.Question;

import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by roman.tsypuk on 6/4/16.
 */
public interface LoadOcpDumper {
    Observable<List<Question>> loadQuestions(InputStream stream);

    Question loadQuestion(long questionId);
}
