/*
 * Copyright (C) 2011 by Stuart Garner
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.radialaspect.humanizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class HumanizerTest {

    private Humanizer humanizer;

    private Question question1;
    private Question question2;

    @Before
    public void setUp() throws Exception {
        question1 = new Question(1L, "2 + 2 is?", new String[] { "4", "four" });
        question2 = new Question(2L, "Jack and Jill went up a?", new String[] { "hill" });

        final Map<Long, Question> questions = new HashMap<Long, Question>();
        questions.put(question1.getId(), question1);
        questions.put(question2.getId(), question2);

        humanizer = new Humanizer(questions);
    }

    @Test
    public void defaultConstructor() throws Exception {
        humanizer = new Humanizer();

        assertEquals(19, humanizer.getQuestions().size());
    }

    @Test
    public void PropertiesConstructor() throws Exception {
        final Properties props = new Properties();
        props.put("question.1", "2 + 2 is?");
        props.put("answer.1", "4; four");
        props.put("question.2", "Jack and Jill went up a?");
        props.put("answer.2", "hill");
        props.put("question.3", "What are the next two numbers in this sequence? one two three...");
        props.put("answer.3", "four five; 4 5");

        humanizer = new Humanizer(props);

        assertEquals(3, humanizer.getQuestions().size());
    }

    @Test
    public void getQuestion() throws Exception {
        assertNotNull(humanizer.getQuestion());
    }

    @Test
    public void getQuestionWithId() throws Exception {
        assertEquals(question1.getQuestion(), humanizer.getQuestion(1L).getQuestion());
    }

    @Test
    public void getQuestionWithUnknownId() throws Exception {
        assertNull(humanizer.getQuestion(999L));
    }

    @Test
    public void getQuestionWithNullId() throws Exception {
        assertNull(humanizer.getQuestion(null));
    }

    @Test
    public void checkAnswer() throws Exception {
        assertTrue(humanizer.checkAnswer(1L, "4"));
        assertTrue(humanizer.checkAnswer(1L, "four"));
    }
}
