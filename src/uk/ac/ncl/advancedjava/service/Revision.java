package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.model.Statistics;
import uk.ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * inherits AbstractQuizGenerator for takeQuiz method which is common between general and revision quiz
 */
final class Revision extends AbstractQuizGenerator {

    private boolean isStudentEligible(Statistics statistics) {
        return Statistics.FinalVerdict.ATTEMPT_1_FAILED == statistics.getFinalVerdict();
    }

    /**
     * generates a revision quiz through the question bank, shuffles the database response and returns only number of questions
     * for testing purposes, only a temporary utility is created. to be replaced by DB call
     *
     * @param noOfQuestions must be between 1 and 10
     * @return shuffled sublist of Question Bank
     */
    public List<QuestionModel> generateQuiz(int noOfQuestions, Statistics statistics) {
        boolean isEligible = isStudentEligible(statistics);
        if(isEligible) {
            if (noOfQuestions < 1 || noOfQuestions > 10) {
                throw new IllegalArgumentException("Number of questions must be between 1 and 10");
            }
            List<QuestionModel> totalQuestions = UtilitiesForTesting.getRevisionQuestions();
            Collections.shuffle(totalQuestions, new Random());
            return totalQuestions.subList(0, noOfQuestions);
        }else{
            throw new IllegalArgumentException("No revision eligible");
        }
    }
}
