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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Parser {

    public static final String DEFAULT_PROPERTIES = "humanizer.properties";

    private final Properties properties;

    public Parser() {
        this(getDefaultProperties());
    }

    public Parser(final Properties properties) {
        this.properties = properties;
    }

    public Map<Long, Question> parse() {
        final Map<Long, Question> questions = new HashMap<Long, Question>();

        if (properties == null) {
            return questions;
        }

        Question question = null;

        for (final String name : properties.stringPropertyNames()) {
            question = getQuestion(properties, name);

            if (question != null) {
                questions.put(question.getId(), question);
            }
        }

        return questions;
    }

    private Question getQuestion(final Properties properties, final String name) {
        final String question = properties.getProperty(name);
        final String allAnswers = properties.getProperty(getMatchingAnswerName(name));
        final Long id = getQuestionId(name);

        final List<String> answers = parseAnswers(allAnswers);

        try {
            return new Question(id, question, answers);
        } catch (final Exception e) {
            return null;
        }
    }

    // accept name like question.1 and return answer.1
    private String getMatchingAnswerName(final String name) {
        return name.replace("question.", "answer.");
    }

    // assume name is in the form of question.<id>
    private Long getQuestionId(final String name) {
        try {
            return new Long(name.replace("question.", ""));
        } catch (final Exception e) {
            return null;
        }
    }

    private List<String> parseAnswers(final String allAnswers) {
        final List<String> answers = new ArrayList<String>();

        for (final String answer : allAnswers.split(";")) {
            if ((answer == null) || "".equals(answer.trim())) {
                continue;
            }

            answers.add(answer.trim());
        }

        return answers;
    }

    private static Properties getDefaultProperties() {
        try {
            final Properties props = new Properties();
            props.load(Parser.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES));

            return props;
        } catch (final IOException e) {
            return null;
        }
    }
}
