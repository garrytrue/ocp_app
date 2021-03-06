package com.tiv.ocpapp.model_dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.tiv.ocpapp.model_dao.Answer;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ANSWER".
*/
public class AnswerDao extends AbstractDao<Answer, Long> {

    public static final String TABLENAME = "ANSWER";

    /**
     * Properties of entity Answer.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Body = new Property(1, String.class, "body", false, "BODY");
        public final static Property IsCorrect = new Property(2, Boolean.class, "isCorrect", false, "IS_CORRECT");
        public final static Property QuestionId = new Property(3, Long.class, "questionId", false, "QUESTION_ID");
    };

    private DaoSession daoSession;

    private Query<Answer> question_AnswersQuery;

    public AnswerDao(DaoConfig config) {
        super(config);
    }
    
    public AnswerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ANSWER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"BODY\" TEXT," + // 1: body
                "\"IS_CORRECT\" INTEGER," + // 2: isCorrect
                "\"QUESTION_ID\" INTEGER);"); // 3: questionId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ANSWER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Answer entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(2, body);
        }
 
        Boolean isCorrect = entity.getIsCorrect();
        if (isCorrect != null) {
            stmt.bindLong(3, isCorrect ? 1L: 0L);
        }
 
        Long questionId = entity.getQuestionId();
        if (questionId != null) {
            stmt.bindLong(4, questionId);
        }
    }

    @Override
    protected void attachEntity(Answer entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Answer readEntity(Cursor cursor, int offset) {
        Answer entity = new Answer( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // body
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // isCorrect
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // questionId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Answer entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBody(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsCorrect(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setQuestionId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Answer entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Answer entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "answers" to-many relationship of Question. */
    public List<Answer> _queryQuestion_Answers(Long questionId) {
        synchronized (this) {
            if (question_AnswersQuery == null) {
                QueryBuilder<Answer> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.QuestionId.eq(null));
                question_AnswersQuery = queryBuilder.build();
            }
        }
        Query<Answer> query = question_AnswersQuery.forCurrentThread();
        query.setParameter(0, questionId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getQuestionDao().getAllColumns());
            builder.append(" FROM ANSWER T");
            builder.append(" LEFT JOIN QUESTION T0 ON T.\"QUESTION_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Answer loadCurrentDeep(Cursor cursor, boolean lock) {
        Answer entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Question question = loadCurrentOther(daoSession.getQuestionDao(), cursor, offset);
        entity.setQuestion(question);

        return entity;    
    }

    public Answer loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Answer> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Answer> list = new ArrayList<Answer>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Answer> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Answer> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
