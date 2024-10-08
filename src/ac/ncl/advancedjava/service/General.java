package ac.ncl.advancedjava.service;

import ac.ncl.advancedjava.model.QuestionModel;
import ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * inherits AbstractQuizGenerator for takeQuiz method which is common between general and revision quiz
 */
public class General extends AbstractQuizGenerator{

    /**
     * generates a general quiz through the question bank, shuffles the database response and returns only number of questions
     * for testing purposes, only a temporary utility is created. to be replaced by DB call
     * @param noOfQuestions
     * @return shuffled sublist of question bank
     */
    public List<QuestionModel> generateQuiz(int noOfQuestions) {
        List<QuestionModel> totalQuestions = UtilitiesForTesting.getGeneralQuestions();
        Collections.shuffle(totalQuestions, new Random());
        return totalQuestions.subList(0, noOfQuestions);
    }
}
