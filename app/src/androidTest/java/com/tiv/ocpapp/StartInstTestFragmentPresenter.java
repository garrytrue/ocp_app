package com.tiv.ocpapp;

import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import javax.inject.Inject;

/**
 * Created by tiv on 11.07.2016.
 */
public class StartInstTestFragmentPresenter extends StartFragmentPresenter {
    @Inject
    RepositoryTest repositoryTest;

    @Override
    public void loadQuestionById(String id) {
        if(id.equals("5")){
            onErrorAction("4");
        }else{
            onNextAction(5);
        }
    }
}
