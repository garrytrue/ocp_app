package com.tiv.ocpapp;

import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    StartFragmentPresenter startFragmentPresenter;
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Before
    public void setUp(){
        startFragmentPresenter = new StartFragmentPresenter();
    }
    @Test
    public void testCorrectDBId(){
        String s = "101";
        assertEquals(101, startFragmentPresenter.getDbId(s));
    }
    @Test
    public void testDbIdWithInputNullValue(){
        assertEquals(1, startFragmentPresenter.getDbId(null));
    }
}