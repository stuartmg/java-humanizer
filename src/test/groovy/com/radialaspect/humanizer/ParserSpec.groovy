package com.radialaspect.humanizer

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import spock.lang.Specification

class ParserSpec extends Specification {

    Parser parser

    def "parse"() {
        parser = new Parser()

        when:
        Map<Long, Question> questions = parser.parse()

        // question.12=What is the opposite of north?
        // answer.12=south
        then:
        questions.size() == 20
        questions.get(12L).question == "What is the opposite of north?"
        questions.get(12L).isAnswerCorrect("south") == true
    }

    def "parse a properties file"() {
        String question1 = "2 + 2 is?"
        String answer1 = "4; four"

        String question2 = "Jack and Jill went up a?"
        String answer2 = "hill"

        String question3 = "What are the next two numbers in this sequence? one two three..."
        String answer3 = "four five; 4 5"

        Properties props = new Properties()
        props.put("question.1", question1)
        props.put("answer.1", answer1)
        props.put("question.2", question2)
        props.put("answer.2", answer2)
        props.put("question.3", question3)
        props.put("answer.3", answer3)

        parser = new Parser(props)

        when:
        def questions = parser.parse()

        then:
        questions.size() == 3
        questions.get(1L).id == 1
        questions.get(1L).question == question1
        questions.get(1L).answers.contains("4") == true
        questions.get(1L).answers.contains("four") == true
        questions.get(2L).id == 2
        questions.get(2L).question == question2
        questions.get(2L).answers.contains("hill") == true
        questions.get(3L).id == 3
        questions.get(3L).question == question3
        questions.get(3L).answers.contains("four five") == true
        questions.get(3L).answers.contains("4 5") == true
    }
}
