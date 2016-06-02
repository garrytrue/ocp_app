package com.tiv.ocpapp.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tiv on 31.05.2016.
 */
public class DataContentProvider extends ContentProvider {
    private static final String TAG = DataContentProvider.class.getSimpleName();
    private static final String AUTHORITY = "com.tiv.ocpapp.db.DataContentProvider";
    private static final String FORWARD_SLASH = "/";
    private static final String CONTENT_PREFIX = "content://";
    private static final String DOT = ".";

    private static final int QUESTION_BY_ID = 100;

    private static final Uri CONTENT_QUESTION_BY_ID = Uri.parse(CONTENT_PREFIX + AUTHORITY + FORWARD_SLASH + QuestionsTable.TABLE_NAME + FORWARD_SLASH + "#" + QUESTION_BY_ID);

    private static final UriMatcher uriMather = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMather.addURI(AUTHORITY, QuestionsTable.TABLE_NAME, QUESTION_BY_ID);
    }

    private static final String QUESTION_ID_ALIAS = "question_id_alias";
    public static final String ANSWER_ID_ALIAS = "answer_id_alias";

    private static final String[] QUESTION_PROJECTION = {QuestionsTable.TABLE_NAME + DOT + QuestionsTable.COLUMN_ID + " AS " + QUESTION_ID_ALIAS
            + QuestionsTable.COLUMN_QUESTION_TEXT
            + QuestionsTable.COLUMN_DESCRIPTION
            + AnswersTable.COLUMN_ANSWER_TEXT
            + AnswersTable.COLUMN_IS_CORRECT};
    private static final String JOIN_REQUEST = QuestionsTable.TABLE_NAME + " LEFT JOIN " + AnswersTable.TABLE_NAME
            + " ON "
            + QuestionsTable.TABLE_NAME + DOT + QuestionsTable.COLUMN_ID
            + " = "
            + AnswersTable.TABLE_NAME + DOT + AnswersTable.COLUMN_QUESTION_ID;

    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate() called with: " + "");
        dbHelper = DbHelper.createDB(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        int uriType = uriMather.match(uri);
        Log.d(TAG, "query: UriType: " + uriType);
        Cursor cursor = null;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (uriType) {
            case QUESTION_BY_ID:
                queryBuilder.setTables(JOIN_REQUEST);
                break;
            default:
                throw new IllegalArgumentException("Not supported UriType. Type " + uriType);
        }
        cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
