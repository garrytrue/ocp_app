package com.tiv.ocpapp.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.model_dao.DaoMaster;
import com.tiv.ocpapp.model_dao.DaoSession;
import com.tiv.ocpapp.model_dao.Question;

import java.util.List;

/**
 * Created by tiv on 03.06.2016.
 */
public class OcpApplication extends Application {
    private static final String DB_NAME = "ocp_app.db";
    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        session = daoMaster.newSession();
        long count = session.getQuestionDao().queryBuilder().count();
        if(count == 0) {
//            Generate and insert data only if question count in DB is 0
            FakeDataGenerator.getInstance().generate5QuestionsToDb(this);
        }
    }

    public DaoSession getSession() {
        return session;
    }
}
