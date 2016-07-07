package com.tiv.ocpapp.utils;

import com.tiv.ocpapp.fake_data.FakeDataGenerator;
import com.tiv.ocpapp.model_dao.Question;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tiv on 07.07.2016.
 */
public class MappersTest {
    private Question currentQuestion;

    @Before
    public void setUp() throws Exception {
        currentQuestion = FakeDataGenerator.getInstance().generateFakeQuestion();
    }

    @Test
    public void testGetCorrectAnswersIds() throws Exception {
        List<Long> correctAnswersIds = Mappers.getCorrectAnswersIds(currentQuestion);
        assertNotNull(correctAnswersIds);
        assertEquals(4, correctAnswersIds.size());
        assertEquals((long) 0, (long)correctAnswersIds.get(0));
        assertEquals((long) 6, (long)correctAnswersIds.get(3));

    }
}