package com.tiv.ocpapp.ui.mvp.views;

import com.tiv.ocpapp.model_dao.Question;

import java.util.List;

/**
 * Created by tiv on 06.07.2016.
 */
public interface IQuestionView extends IView {
    void updateData(Question data);
    void answerResponse(List<Long> correctAnswersIds);
    void resetViews();
}
