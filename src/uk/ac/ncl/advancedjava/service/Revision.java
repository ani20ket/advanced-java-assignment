package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * inherits AbstractQuizGenerator for takeQuiz method which is common between general and revision quiz
 */
public class Revision extends AbstractQuizGenerator {

    /**
     * generates a revision quiz through the question bank, shuffles the database response and returns only number of questions
     * for testing purposes, only a temporary utility is created. to be replaced by DB call
     *
     * @param noOfQuestions
     * @return shuffled sublist of Question Bank
     */
    public List<QuestionModel> generateQuiz(int noOfQuestions) {
        List<QuestionModel> totalQuestions = UtilitiesForTesting.getRevisionQuestions();
        Collections.shuffle(totalQuestions, new Random());
        return totalQuestions.subList(0, noOfQuestions);
    }
}
