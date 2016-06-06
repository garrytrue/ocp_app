package com.tiv.ocpapp.mvp;

import android.content.Context;

/**
 * Created by tiv on 06.06.2016.
 */
public interface StartScreenContract {
    interface StartView extends BaseView{
        void showDbIdViolation(long maxCount);
        void showNextScreen(long questionId);
        Context provideContext();
    }
    interface Presenter extends BasePresenter<StartView>{
       void onLoadById(String id);
    }
}
