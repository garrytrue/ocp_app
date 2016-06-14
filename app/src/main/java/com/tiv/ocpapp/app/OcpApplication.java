package com.tiv.ocpapp.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.Question;
import com.example.service.LoadOcpDumpTextStructureImpl;
import com.example.service.LoadOcpDumper;
import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.model_dao.DaoMaster;
import com.tiv.ocpapp.model_dao.DaoSession;

import java.io.IOException;
import java.util.List;


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
        if (count == 0) {
//            Generate and insert data only if question count in DB is 0
            LoadOcpDumper loadOcpDumpTextStructure = new LoadOcpDumpTextStructureImpl();
            try {
                loadOcpDumpTextStructure.init(getAssets().open("ocp8.dump"));
                List<Question> questions = loadOcpDumpTextStructure.loadQuestions();
                for (Question q : questions) {
                    FakeDataGenerator.getInstance().insertQuestion(session, q);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public DaoSession getSession() {
        return session;
    }
    /*Make comment for update repo*/
    /*test credential cache*/
}
