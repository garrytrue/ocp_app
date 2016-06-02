package com.tiv.ocpapp.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class QuestionsTable {
    private static final String TAG = QuestionsTable.class.getSimpleName();
    public static final String TABLE_NAME = "questions_table";
    //    Fields
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_QUESTION_TEXT + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpdate() called with: " + "database = [" + database + "], oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }
}
