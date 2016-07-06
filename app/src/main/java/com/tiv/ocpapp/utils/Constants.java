package com.tiv.ocpapp.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tiv on 06.07.2016.
 */
public final class Constants {
    public static final String ERROR_LAST_QUESTION = "ERROR_LAST_QUESTION";
    public static final String ERROR_ANSWER_NOT_SELECTED = "ERROR_ANSWER_NOT_SELECTED";
    public static final String ERROR_NOT_VALID_INPUT = "ERROR_NOT_VALID_INPUT";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ERROR_ANSWER_NOT_SELECTED, ERROR_LAST_QUESTION, ERROR_NOT_VALID_INPUT})
    public @interface Error {
    }

    public static final int ANSWER_SELECTED = 101;
    public static final int ANSWER_NOT_SELECTED = 102;
    public static final int ANSWER_STATE_NOT_DEFINED = 100;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ANSWER_NOT_SELECTED, ANSWER_SELECTED, ANSWER_STATE_NOT_DEFINED})
    public @interface AnswerState{}

    private Constants() {
        throw new AssertionError("Don't make instance");
    }
}
