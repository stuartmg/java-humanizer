package com.radialaspect.humanizer

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNull
import static org.junit.Assert.assertTrue
import spock.lang.Specification

class HumanizerSpec extends Specification {

    private Humanizer humanizer
    private Question question1
    private Question question2

    def setup() {
        question1 = new Question(1L, "2 + 2 is?", ["4", "four"])
        question2 = new Question(2L, "Jack and Jill went up a?", ["hill"])

        Map<Long, Question> questions = [ (question1.id): question1, (question2.id): question2 ]

        humanizer = new Humanizer(questions)
    }

    def "default constructor"() {
        when:
        humanizer = new Humanizer()

        then:
        // there are 20 questions in the default properties file
        20 == humanizer.questions.size()
    }

    def "properties constructor"() {
        Properties props = new Properties()
        props.put("question.1", "2 + 2 is?")
        props.put("answer.1", "4; four")
        props.put("question.2", "Jack and Jill went up a?")
        props.put("answer.2", "hill")
        props.put("question.3", "What are the next two numbers in this sequence? one two three...")
        props.put("answer.3", "four five; 4 5")

        when:
        humanizer = new Humanizer(props)

        then:
        3 == humanizer.questions.size()
    }

    def "get question"() {
        expect: humanizer.question != null
    }

    def "get question with id"() {
        when:
        Question q = humanizer.getQuestion(1)

        then:
        question1.question == q.question
    }

    def "get question with unknown id"() {
        expect: humanizer.getQuestion(999) == null
    }

    def "get question with null id"() {
        expect: humanizer.getQuestion(null) == null
    }

    def "check answer"() {
        expect:
        humanizer.checkAnswer(1, "4") == true
        humanizer.checkAnswer(1, "four") == true
    }
}
