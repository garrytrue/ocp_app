package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.di.modules.RepositoryModule;
import com.tiv.ocpapp.helpers.BaseTest;
import com.tiv.ocpapp.ui.mvp.views.IView;
import com.tiv.ocpapp.ui.mvp.views.StartFragmentView;
import com.tiv.ocpapp.utils.Constants;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tiv on 07.07.2016.
 */
public class StartFragmentPresenterTest extends BaseTest {
    StartFragmentPresenter startFragmentPresenter;
    StartFragmentView mockStartFragmentView;
    @Inject
    RepositoryModule mockRepositoryModule;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mockStartFragmentView = mock(StartFragmentView.class);
        startFragmentPresenter = new StartFragmentPresenter();
        startFragmentPresenter.onCreate(mockStartFragmentView);
        testComponent.inject(this);
    }


    @Test
    public void correctParseInputDbId() {
        String s = "101";
        assertEquals(101, startFragmentPresenter.getDbId(s));
    }

    @Test
    public void testDbIdWithInputNullValue() {
        assertEquals(1, startFragmentPresenter.getDbId(null));
    }

    @Test
    public void viewShouldShowError() {
        when(mockRepositoryModule.getQuestionsCount()).thenReturn((long)5);
        startFragmentPresenter.loadQuestionById(String.valueOf(6));
        verify(mockStartFragmentView).showError("5");
    }

    @Test
    public void viewShouldInvokeNextActionWithCorrectValue() {
        startFragmentPresenter.onNextAction(15);
        verify(mockStartFragmentView).onNextAction(15);
    }

    @Test
    public void testLoadQuestionById() throws Exception {
        when(mockRepositoryModule.getQuestionsCount()).thenReturn((long)5);
        startFragmentPresenter.loadQuestionById(String.valueOf(6));
        verify(mockStartFragmentView).showError("5");
        when(mockRepositoryModule.getQuestionsCount()).thenReturn((long)10);
        startFragmentPresenter.loadQuestionById(String.valueOf(6));
        verify(mockStartFragmentView).onNextAction(6);
    }
}