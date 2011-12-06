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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {
    private static final long serialVersionUID = -5365049431441803692L;

    private Long id;
    private String question;
    private List<String> answers;

    public Question() {
        super();
    }

    public Question(final Long id, final String question, final String[] answers) {
        this(id, question, Arrays.asList(answers));
    }

    public Question(final Long id, final String question, final List<String> answers) {
        setId(id);
        setQuestion(question);
        setAnswers(answers);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        if (getCleanValue(question) == null) {
            throw new IllegalArgumentException("question must not be empty");
        }

        this.question = question.trim();
    }

    public List<String> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void setAnswers(final List<String> answers) {
        if ((answers == null) || answers.isEmpty()) {
            throw new IllegalArgumentException("answers must not be empty");
        }

        this.answers = new ArrayList<String>(answers);
    }

    public boolean isAnswerCorrect(final String value) {
        final String trial = getCleanValue(value);

        if (trial == null) {
            return false;
        }

        for (final String answer : getAnswers()) {
            if (isMatchingAnswer(answer, trial)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(1024);

        sb.append("Question: ").append(getQuestion()).append(" Answers:").append(getAnswers());

        return sb.toString();
    }

    private boolean isMatchingAnswer(final String answer, final String value) {
        return answer.equalsIgnoreCase(value);
    }

    private String getCleanValue(final String value) {
        if ((value == null) || "".equals(value.trim())) {
            return null;
        }

        return value.trim();
    }
}
