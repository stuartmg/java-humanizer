package com.radialaspect.humanizer

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue
import spock.lang.Specification

class QuestionSpec extends Specification {

    String questionText
    List<String> answers
    Question question

    def setup() {
        questionText = "2 + 2 is?"

        answers = new ArrayList<String>()
        answers.add("4")
        answers.add("four")

        question = new Question(1L, questionText, answers)
    }

    def "id"() {
        expect: 1 == question.id
    }

    def "null id"() {
        when:
        question.id = null

        then:
        thrown(IllegalArgumentException)
    }

    def "question text"() {
        when:
        question.question = "Jack and Jill went up a?"

        then:
        "Jack and Jill went up a?" == question.question
    }

    def "null question"() {
        when:
        question.question = null

        then:
        thrown(IllegalArgumentException)
    }

    def "empty question"() {
        when:
        question.question = "   "

        then:
        thrown(IllegalArgumentException)
    }

    def "answers"() {
        expect:
        answers.size() == question.answers.size()
        question.answers.contains(answers.get(0)) == true
        question.answers.contains(answers.get(1)) == true
    }

    def "null answers"() {
        when:
        question.answers = null

        then:
        thrown(IllegalArgumentException)
    }

    def "empty answers"() {
        when:
        question.answers = new ArrayList<String>()

        then:
        thrown(IllegalArgumentException)
    }

    def "correct answers"() {
        expect:
        question.isAnswerCorrect("4") == true
        question.isAnswerCorrect("four") == true
    }

    def "incorrect answers"() {
        expect:
        question.isAnswerCorrect("5") == false
        question.isAnswerCorrect("three") == false
    }

    def "empty answer"() {
        expect:
        question.isAnswerCorrect("  ") == false
        question.isAnswerCorrect(null) == false
    }
}
