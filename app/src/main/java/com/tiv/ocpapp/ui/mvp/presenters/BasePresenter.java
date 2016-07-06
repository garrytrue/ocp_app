package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.ui.mvp.views.IView;

/**
 * Created by tiv on 06.07.2016.
 */
public interface BasePresenter {
    void onErrorAction(String error);
    void onNextAction(long nextId);
    IView getView();
    void onCreate(IView view);
}
