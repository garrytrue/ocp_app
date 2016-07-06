package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.di.modules.RepositoryModule;
import com.tiv.ocpapp.ui.mvp.views.IView;

import javax.inject.Inject;

/**
 * Created by tiv on 06.07.2016.
 */
public class StartFragmentPresenter implements BasePresenter {
    private IView view;
    @Inject
    RepositoryModule mRepositoryModule;

    public StartFragmentPresenter() {
        OcpApplication.provideApplicationComponent().inject(this);
    }

    @Override
    public void onErrorAction(String error) {
        getView().showError(error);
    }

    @Override
    public void onNextAction(long nextId) {
        getView().onNextAction(nextId);
    }

    @Override
    public IView getView() {
        return view;
    }

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    public void loadQuestionById(String id) {
        long inputId = getDbId(id);
        if (inputId > mRepositoryModule.getQuestionsCount()) {
            onErrorAction(String.valueOf(mRepositoryModule.getQuestionsCount()));
        } else {
            onNextAction(inputId);
        }

    }

    public long getDbId(String id) {
        long dbId = 1;
        try {
            dbId = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            return dbId;
        }
        return dbId;
    }


}
