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
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class ParserTest {

    private Parser parser;

    @Test
    public void parse() throws Exception {
        parser = new Parser();

        final Map<Long, Question> questions = parser.parse();

        assertEquals(19, questions.size());

        // question.12=What is the opposite of north?
        // answer.12=south
        assertEquals("What is the opposite of north?", questions.get(12L).getQuestion());
        assertTrue(questions.get(12L).isAnswerCorrect("south"));
    }

    @Test
    public void parseProperties() throws Exception {
        final Properties props = new Properties();
        final String question1 = "2 + 2 is?";
        final String answer1 = "4; four";

        final String question2 = "Jack and Jill went up a?";
        final String answer2 = "hill";

        final String question3 = "What are the next two numbers in this sequence? one two three...";
        final String answer3 = "four five; 4 5";

        props.put("question.1", question1);
        props.put("answer.1", answer1);
        props.put("question.2", question2);
        props.put("answer.2", answer2);
        props.put("question.3", question3);
        props.put("answer.3", answer3);

        parser = new Parser(props);

        final Map<Long, Question> questions = parser.parse();

        assertEquals(3, questions.size());

        assertEquals(1L, questions.get(1L).getId().longValue());
        assertEquals(question1, questions.get(1L).getQuestion());
        assertEquals(2, questions.get(1L).getAnswers().size());
        assertTrue(questions.get(1L).getAnswers().contains("4"));
        assertTrue(questions.get(1L).getAnswers().contains("four"));

        assertEquals(2L, questions.get(2L).getId().longValue());
        assertEquals(1, questions.get(2L).getAnswers().size());
        assertTrue(questions.get(2L).getAnswers().contains("hill"));

        assertEquals(3L, questions.get(3L).getId().longValue());
        assertEquals(2, questions.get(3L).getAnswers().size());
        assertTrue(questions.get(3L).getAnswers().contains("four five"));
        assertTrue(questions.get(3L).getAnswers().contains("4 5"));
    }
}
