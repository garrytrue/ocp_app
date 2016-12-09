package com.tiv.ocpapp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tiv.ocpapp.di.DaggerStartViewInstTestComponent;
import com.tiv.ocpapp.di.StartViewInstTestModule;
import com.tiv.ocpapp.ui.ActivityMain;
import com.tiv.ocpapp.ui.mvp.presenters.StartFragmentPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by tiv on 11.07.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartFragmentTest {
    @Inject
    StartInstTestFragmentPresenter presenter;
    @Rule
    public ActivityTestRule<ActivityMain> mActivityRule = new ActivityTestRule(ActivityMain.class);

    @Before
    public void setUp() {
        DaggerStartViewInstTestComponent.builder().startViewInstTestModule(new StartViewInstTestModule()).build().inject(this);
        presenter.onCreate(null);
    }

    @Test
    public void checkScreenVisibility() {
        onView(withId(R.id.question_number)).check(matches(isDisplayed()));
        onView(withId(R.id.arrow_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void checkShowErrorIfInputNotValid() {
        onView(withId(R.id.question_number)).perform(replaceText("5"));
        onView(withId(R.id.question_number)).perform(closeSoftKeyboard());
        System.out.println("Presenter " + presenter);
        onView(withId(R.id.arrow_btn)).perform(click());
        onView(withText("You have only")).check(matches(isDisplayed()));
    }


}
