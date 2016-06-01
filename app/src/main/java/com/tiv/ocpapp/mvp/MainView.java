package com.tiv.ocpapp.mvp;

import com.tiv.ocpapp.model.Question;

/**
 * Created by tiv on 01.06.2016.
 */
public interface MainView {
    void loadData(int id);
    void onDataLoaded(Question question);
}
