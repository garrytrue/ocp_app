package com.tiv.ocpapp.mvp;

import android.os.SystemClock;

import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.model.Question;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tiv on 01.06.2016.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public Observable<Question> getData() {
        return null;
                /*Observable.fromCallable(() -> {
            SystemClock.sleep(2000);
            return FakeDataGenerator.getInstance().getOneFakeQuestion();
        });*/
    }

    @Override
    public void loadData(int id) {
        getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(question -> view.onDataLoaded(question),
                error -> {
                    // TODO: 01.06.2016 Need handle error
                },
                () -> {
                });
    }
}
