package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.di.modules.RepositoryModule;
import com.tiv.ocpapp.model_dao.Question;
import com.tiv.ocpapp.ui.mvp.views.IQuestionView;
import com.tiv.ocpapp.ui.mvp.views.IView;
import com.tiv.ocpapp.utils.Constants;

import javax.inject.Inject;

/**
 * Created by tiv on 06.07.2016.
 */
public class QuestionFragmentPresenter implements BasePresenter {
    private IQuestionView view;
    private
    @Constants.AnswerState
    int answerState = Constants.ANSWER_STATE_NOT_DEFINED;
    @Inject
    RepositoryModule repositoryModule;

    public QuestionFragmentPresenter() {
        OcpApplication.provideApplicationComponent().inject(this);
    }

    @Override
    public void onErrorAction(String error) {

    }

    @Override
    public void onNextAction(long nextId) {

    }

    @Override
    public IView getView() {
        return view;
    }

    @Override
    public void onCreate(IView view) {
        this.view = (IQuestionView) view;
    }

    public void loadQuestionById(long id) {
        if (repositoryModule.isLastQuestion(id)) {
            getView().showError(Constants.ERROR_LAST_QUESTION);
            return;
        }
        Question question = repositoryModule.getQuestionById(id);
        view.updateData(question);
    }

    public void onAnswerAction() {
        if (answerState == Constants.ANSWER_SELECTED) {

        }
    }
}
