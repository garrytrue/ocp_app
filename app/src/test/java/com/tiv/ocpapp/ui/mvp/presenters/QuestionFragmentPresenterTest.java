package com.tiv.ocpapp.ui.mvp.presenters;

import com.tiv.ocpapp.di.modules.RepositoryModule;
import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.helpers.BaseTest;
import com.tiv.ocpapp.model_dao.Question;
import com.tiv.ocpapp.ui.mvp.views.IQuestionView;
import com.tiv.ocpapp.utils.Constants;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tiv on 08.07.2016.
 */
public class QuestionFragmentPresenterTest extends BaseTest {
    QuestionFragmentPresenter questionFragmentPresenter;
    IQuestionView mockQuestionView;
    List<Long> selectedAnswers = new ArrayList<>(4);

    @Inject
    RepositoryModule mockRepositoryModule;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        testComponent.inject(this);
        questionFragmentPresenter = new QuestionFragmentPresenter();
        mockQuestionView = mock(IQuestionView.class);
        questionFragmentPresenter.onCreate(mockQuestionView);
    }

    private void initSelectedAnswers() {
        selectedAnswers.add((long) 0);
        selectedAnswers.add((long) 2);
        selectedAnswers.add((long) 4);
        selectedAnswers.add((long) 6);
    }

    @Test
    public void testOnAnswerActionWithNoSelectedAnswers() {
        assertNotNull(selectedAnswers);
        questionFragmentPresenter.onAnswerAction(selectedAnswers);
        verify(mockQuestionView).showError(Constants.ERROR_ANSWER_NOT_SELECTED);
    }
    @Test
    public void testOnAnswerActionWithSelectedAnswers(){
        initSelectedAnswers();
        when(mockRepositoryModule.getCurrentQuestion()).thenReturn(FakeDataGenerator.getInstance().generateFakeQuestion());
        questionFragmentPresenter.onAnswerAction(selectedAnswers);
        verify(mockQuestionView).answerResponse(selectedAnswers);
    }
    @Test
    public void testInitQuestionById(){
        questionFragmentPresenter.initQuestionById(5);
        verify(mockRepositoryModule).getQuestionById(5);
    }
    @Test
    public void testOnNextActionWithLastQuestion(){
        Question question = FakeDataGenerator.getInstance().generateFakeQuestion();
        question.setId((long)5);
        when(mockRepositoryModule.getCurrentQuestion()).thenReturn(question);
        when(mockRepositoryModule.isLastQuestion((long)5)).thenReturn(true);
        questionFragmentPresenter.onNextAction(5);
        verify(mockQuestionView).showError(Constants.ERROR_LAST_QUESTION);
    }
    @Test
    public void testOnNextActionWithNoLastQuestion(){
        Question question = FakeDataGenerator.getInstance().generateFakeQuestion();
        question.setId((long)10);
        when(mockRepositoryModule.getCurrentQuestion()).thenReturn(question);
        when(mockRepositoryModule.isLastQuestion((long)5)).thenReturn(false);
        questionFragmentPresenter.onNextAction(5);
        verify(mockQuestionView).resetViews();
        question.setId((long)6);
        when(mockRepositoryModule.getQuestionById(anyLong())).thenReturn(question);
//        Real Question object not loaded from mock RepositoryModule
        verify(mockQuestionView).updateData(null);
    }
    @Test
    public void testLoadCurrentQuestionWithError(){
        Question question = FakeDataGenerator.getInstance().generateFakeQuestion();
        question.setId((long)5);
        when(mockRepositoryModule.getCurrentQuestion()).thenReturn(question);
        when(mockRepositoryModule.isLastQuestion((long)5)).thenReturn(true);
        questionFragmentPresenter.loadCurrentQuestion();
        verify(mockQuestionView).showError(Constants.ERROR_LAST_QUESTION);
    }
    @Test
    public void testLoadCurrentQuestionWithoutError(){
        Question question = FakeDataGenerator.getInstance().generateFakeQuestion();
        question.setId((long)10);
        when(mockRepositoryModule.getCurrentQuestion()).thenReturn(question);
        when(mockRepositoryModule.isLastQuestion((long)5)).thenReturn(false);
        questionFragmentPresenter.loadCurrentQuestion();
        verify(mockQuestionView).updateData(question);
        initSelectedAnswers();
        questionFragmentPresenter.setAnswerState(Constants.ANSWER_SELECTED);
        when(mock(QuestionFragmentPresenter.class).isAnswerSelected()).thenReturn(true);
        verify(mockQuestionView).answerResponse(selectedAnswers);
    }


}