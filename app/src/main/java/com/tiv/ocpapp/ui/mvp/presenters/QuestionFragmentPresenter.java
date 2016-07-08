package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.di.modules.RepositoryModule;
import com.tiv.ocpapp.ui.mvp.views.IQuestionView;
import com.tiv.ocpapp.ui.mvp.views.IView;
import com.tiv.ocpapp.utils.Constants;
import com.tiv.ocpapp.utils.Mappers;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by tiv on 06.07.2016.
 */
public class QuestionFragmentPresenter implements BasePresenter {
    private IQuestionView view;
    @Constants.AnswerState
    private int answerState = Constants.ANSWER_STATE_NOT_DEFINED;
    @Inject
    RepositoryModule repositoryModule;


    @Override
    public void onErrorAction(String error) {
        getView().showError(error);
    }

    @Override
    public void onNextAction(long nextId) {
        setAnswerState(Constants.ANSWER_NOT_SELECTED);
        if (repositoryModule.isLastQuestion(repositoryModule.getCurrentQuestion().getId())) {
            onErrorAction(Constants.ERROR_LAST_QUESTION);
        } else {
            view.resetViews();
            view.updateData(repositoryModule.getQuestionById(repositoryModule.getCurrentQuestion().getId() + 1));
        }
    }

    @Override
    public IView getView() {
        return view;
    }

    @Override
    public void onCreate(IView view) {
        this.view = (IQuestionView) view;
        OcpApplication.provideApplicationComponent().inject(this);
    }

    public void initQuestionById(long id) {
        repositoryModule.getQuestionById(id);
    }

    public void onAnswerAction(List<Long> selectedAnswers) {
        if (selectedAnswers.isEmpty()) {
            onErrorAction(Constants.ERROR_ANSWER_NOT_SELECTED);
            return;
        }
        setAnswerState(Constants.ANSWER_SELECTED);
        view.answerResponse( Mappers.getCorrectAnswersIds(repositoryModule.getCurrentQuestion()));
    }

    public void setAnswerState(@Constants.AnswerState int answerState) {
        this.answerState = answerState;
    }

    public void loadCurrentQuestion() {
        if (repositoryModule.isLastQuestion(repositoryModule.getCurrentQuestion().getId())) {
            onErrorAction(Constants.ERROR_LAST_QUESTION);
            return;
        }
        view.updateData(repositoryModule.getCurrentQuestion());
        if (isAnswerSelected()) {
            view.answerResponse( Mappers.getCorrectAnswersIds(repositoryModule.getCurrentQuestion()));
        }
    }
    public boolean isAnswerSelected(){
        return answerState == Constants.ANSWER_SELECTED;
    }
}
