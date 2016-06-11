package com.example.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.model.Question;

/**
 * Created by roman.tsypuk on 6/4/16.
 */
public interface LoadOcpDumper {
    void init(InputStream stream) throws IOException;
    List<Question> loadQuestions();
    Question loadQuestion(long questionId);
}