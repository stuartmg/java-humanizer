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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {

    private String questionText;
    private List<String> answers;

    private Question question;

    @Before
    public void setUp() throws Exception {
        questionText = "2 + 2 is?";

        answers = new ArrayList<String>();
        answers.add("4");
        answers.add("four");

        question = new Question(1L, questionText, answers);
    }

    @Test
    public void id() throws Exception {
        assertEquals(1L, question.getId().longValue());

        question.setId(2L);
        assertEquals(2L, question.getId().longValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullId() throws Exception {
        question.setId(null);
    }

    @Test
    public void question() throws Exception {
        assertEquals(questionText, question.getQuestion());

        question.setQuestion("Jack and Jill went up a?");
        assertEquals("Jack and Jill went up a?", question.getQuestion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullQuestion() throws Exception {
        question.setQuestion(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyQuestion() throws Exception {
        question.setQuestion("   ");
    }

    @Test
    public void answers() throws Exception {
        assertEquals(answers.size(), question.getAnswers().size());
        assertTrue(question.getAnswers().contains(answers.get(0)));
        assertTrue(question.getAnswers().contains(answers.get(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAnswers() throws Exception {
        question.setAnswers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyAnswers() throws Exception {
        question.setAnswers(new ArrayList<String>());
    }

    @Test
    public void correctAnswer() throws Exception {
        assertTrue(question.isAnswerCorrect("4"));
        assertTrue(question.isAnswerCorrect("four"));
    }

    @Test
    public void incorrectAnswer() throws Exception {
        assertFalse(question.isAnswerCorrect("3"));
        assertFalse(question.isAnswerCorrect("three"));
    }

    @Test
    public void nullAnswer() throws Exception {
        assertFalse(question.isAnswerCorrect(null));
    }

    @Test
    public void emptyAnswer() throws Exception {
        assertFalse(question.isAnswerCorrect("   "));
    }
}
