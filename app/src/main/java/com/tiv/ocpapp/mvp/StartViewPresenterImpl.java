package com.tiv.ocpapp.mvp;

import android.content.Context;

import com.tiv.ocpapp.app.OcpApplication;
import com.tiv.ocpapp.model_dao.DaoSession;

import java.lang.ref.WeakReference;

/**
 * Created by tiv on 06.06.2016.
 */
public class StartViewPresenterImpl implements StartScreenContract.Presenter {

    private WeakReference<StartScreenContract.StartView> startViewWeakReference;

    @Override
    public void onLoadById(String id) {
        if (startViewWeakReference != null) {
            final StartScreenContract.StartView startView = startViewWeakReference.get();
            if (startView != null) {
                Context context = startView.provideContext();
                DaoSession session = ((OcpApplication) context.getApplicationContext()).getSession();
                long dbId = getLongId(id, startView);
                long dbCount = session.getQuestionDao().count();
                if (dbId > dbCount) {
                    startView.showDbIdViolation(dbCount);
                } else {
                    startView.showNextScreen(dbId);
                }
            }
        }

    }

    @Override
    public void attachToView(StartScreenContract.StartView view) {
        startViewWeakReference = new WeakReference<>(view);
    }

    private long getLongId(String id, StartScreenContract.StartView view) {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            longId = 1;
        }
        return longId;
    }
}
