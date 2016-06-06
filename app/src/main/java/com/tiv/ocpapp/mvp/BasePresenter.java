package com.tiv.ocpapp.mvp;

/**
 * Created by tiv on 06.06.2016.
 */
public interface BasePresenter<T extends BaseView> {
    void attachToView(T view);
}
