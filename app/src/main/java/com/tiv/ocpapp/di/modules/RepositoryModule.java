package com.tiv.ocpapp.di.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.Question;
import com.example.service.LoadOcpDumpTextStructureImpl;
import com.example.service.LoadOcpDumper;
import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.model_dao.DaoMaster;
import com.tiv.ocpapp.model_dao.DaoSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by tiv on 05.07.2016.
 */
@Module
public class RepositoryModule {
    private static final String DB_NAME = "ocp_app.db";
    private Context context;
    private DaoSession session;

    @Inject
    public RepositoryModule(Context context) {
        this.context = context;
        provideDaoSession();
    }


    public DaoSession provideDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        return session = daoMaster.newSession();
    }

    public void putDataToDb() {
        if (isDbEmpty()) {
//            Generate and insert data only if question count in DB is 0
            LoadOcpDumper loadOcpDumpTextStructure = new LoadOcpDumpTextStructureImpl();
            try {
                loadOcpDumpTextStructure.init(context.getAssets().open("ocp8.dump"));
                List<Question> questions = loadOcpDumpTextStructure.loadQuestions();
                for (Question q : questions) {
                    FakeDataGenerator.getInstance().insertQuestion(session, q);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long getQuestionsCount() {
        return session.getQuestionDao().count();
    }

    public boolean isDbEmpty() {
        return (int) getQuestionsCount() == 0;
    }

    public com.tiv.ocpapp.model_dao.Question getQuestionById(long id) {
        return session.getQuestionDao().loadByRowId(id);
    }

    public boolean isLastQuestion(long currentId) {
        return currentId >= getQuestionsCount();
    }
}
