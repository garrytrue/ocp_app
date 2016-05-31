package com.tiv.ocpapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiv on 31.05.2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ocp_app.db";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        QuestionsTable.onCreate(db);
        AnswersTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        QuestionsTable.onUpdate(db, oldVersion, newVersion);
        AnswersTable.onUpdate(db, oldVersion, newVersion);
    }
    public static DbHelper createDB(Context context){
        return new DbHelper(context);
    }
}
