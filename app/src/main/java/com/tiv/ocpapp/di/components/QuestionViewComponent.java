package com.tiv.ocpapp.di.components;

import com.tiv.ocpapp.di.modules.QuestionViewModule;
import com.tiv.ocpapp.ui.QuestionFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tiv on 06.07.2016.
 */
@Singleton
@Component(modules = QuestionViewModule.class)
public interface QuestionViewComponent {
    void inject(QuestionFragment fragment);
}
