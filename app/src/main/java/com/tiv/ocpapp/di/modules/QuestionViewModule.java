package com.tiv.ocpapp.di.modules;

import com.tiv.ocpapp.ui.mvp.presenters.QuestionFragmentPresenter;
import com.tiv.ocpapp.ui.mvp.views.IView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tiv on 06.07.2016.
 */
@Module
public class QuestionViewModule {

    @Singleton
    @Provides
    public QuestionFragmentPresenter provideQuestionPresenter(){
        return new QuestionFragmentPresenter();
    }
}
