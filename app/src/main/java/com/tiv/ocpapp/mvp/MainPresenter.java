package com.tiv.ocpapp.mvp;

import com.tiv.ocpapp.model.Question;

import rx.Observable;

/**
 * Created by tiv on 01.06.2016.
 */
public interface MainPresenter {
    Observable<Question> getData();
    void loadData(int id);
}
