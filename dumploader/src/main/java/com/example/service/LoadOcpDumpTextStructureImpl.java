package com.example.service;


import com.example.model.Answer;
import com.example.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

enum CurrentElement {
    DESCRIPTION,
    QUESTION,
    ANSWER
}

public class LoadOcpDumpTextStructureImpl implements LoadOcpDumper {
    private static final String TAG = LoadOcpDumpTextStructureImpl.class.getSimpleName();

    List<Question> questionDump = new ArrayList<>();


    @Override
    public Observable<List<Question>> loadQuestions(final InputStream inputStream) {
        return Observable.fromCallable(new Callable<List<Question>>() {
            @Override
            public List<Question> call() throws Exception {
                loadDump(inputStream);
                return questionDump;
            }
        });
    }

    @Override
    public Question loadQuestion(long questionId) {
        return questionDump.get((int) questionId);
    }

    private void loadDump(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        Question currentQuestion = new Question();
        List<Answer> answers = new ArrayList<>();
        long questionId = 0;
        long answerId = 0;
        StringBuilder questionBody = new StringBuilder();
        StringBuilder descriptionBody = new StringBuilder();
        CurrentElement currentElement = CurrentElement.QUESTION;
        while ((line = br.readLine()) != null) {
            switch (line.substring(0, 2)) {
                case "--":
                    currentQuestion = new Question();
                    // This is a comment line start new Question
                    resetData(questionBody);
                    resetData(descriptionBody);
                    break;
                case "Q:":
//                        currentQuestion.setDescription(descriptionBody.toString());
                    questionBody = new StringBuilder();
                    answers = new ArrayList<>();
                    currentElement = CurrentElement.QUESTION;
                    currentQuestion = new Question();
                    currentQuestion.setId(++questionId);
                    questionBody.append(line.substring(2) + " \n ");
                    break;
                case "A.":
                    answers = new ArrayList<>();
                case "B.":
                case "C.":
                case "D.":
                case "E.":
                case "F.":
                case "G.":
                case "H.":
                    currentElement = CurrentElement.ANSWER;
                    answers.add(new Answer(++answerId, line.charAt(0) + ". " + line.substring(3), !line.substring(2, 3).contains("-"), questionId));
                    break;
                case "Y:":
                    currentElement = CurrentElement.DESCRIPTION;
                    descriptionBody.append(line.substring(2));
//                        currentQuestion.setDescription(line.substring(2));
                    if (!answers.isEmpty()) {
                        currentQuestion.setAnswers(answers);
                        currentQuestion.setText(questionBody.toString());
                        questionDump.add(currentQuestion);

                    }
                    break;
                default:
                    if (currentElement == CurrentElement.QUESTION) {
                        questionBody.append(line + " \n ");
                    } else if (currentElement == CurrentElement.DESCRIPTION) {
                        descriptionBody.append(line + " \n ");
                        currentQuestion.setDescription(descriptionBody.toString());
                    }
                    break;

            }
        }
    }


    private void resetData(StringBuilder data) {
        data.delete(0, data.length());
    }
}