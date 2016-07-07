package com.tiv.ocpapp.ui.mvp.presenters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tiv on 07.07.2016.
 */
public class StartFragmentPresenterTest {
    StartFragmentPresenter startFragmentPresenter;

    @Before
    public void setUp() {
        startFragmentPresenter = new StartFragmentPresenter();
    }

    @Test
    public void testCorrectDBId() {
        String s = "101";
        assertEquals(101, startFragmentPresenter.getDbId(s));
    }

    @Test
    public void testDbIdWithInputNullValue() {
        assertEquals(1, startFragmentPresenter.getDbId(null));
    }
}