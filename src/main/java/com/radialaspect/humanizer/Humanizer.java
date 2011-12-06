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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Humanizer {

    private final Map<Long, Question> questions;

    public Humanizer() {
        this(new Parser());
    }

    public Humanizer(final Properties properties) {
        this(new Parser(properties));
    }

    public Humanizer(final Parser parser) {
        this(parser.parse());
    }

    public Humanizer(final Map<Long, Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return new ArrayList<Question>(questions.values());
    }

    public Question getQuestion() {
        return new ArrayList<Question>(questions.values()).get(getRandomIndex());
    }

    public Question getQuestion(final Long id) {
        return questions.get(id);
    }

    public boolean checkAnswer(final Long id, final String answer) {
        final Question question = getQuestion(id);

        return (question == null) ? false : question.isAnswerCorrect(answer);
    }

    private int getRandomIndex() {
        return (int) (questions.size() * Math.random());
    }
}
