package com.tiv.ocpapp.db;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AnswersTable {
    private static final String TAG = AnswersTable.class.getSimpleName();
    public static final String TABLE_NAME = "answers_table";
    //    Fields
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANSWER_TEXT = "answer_text";
    public static final String COLUMN_QUESTION_ID = "question_id";
    public static final String COLUMN_IS_CORRECT = "is_correct";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ANSWER_TEXT + " TEXT NOT NULL, "
            + COLUMN_QUESTION_ID + " INTEGER NOT NULL"
            + COLUMN_IS_CORRECT + " INTEGER NOT NULL"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpdate() called with: " + "database = [" + database + "], oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }
}
