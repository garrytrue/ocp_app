package com.tiv.ocpapp.ui.mvp.views;

import com.tiv.ocpapp.model_dao.Question;

/**
 * Created by tiv on 06.07.2016.
 */
public interface IQuestionView extends IView {
    void loadQuestionById(long id);
    void updateData(Question data);
}
