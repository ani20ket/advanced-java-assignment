package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.model.Statistics;
import uk.ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * inherits AbstractQuizGenerator for takeQuiz method which is common between general and revision quiz
 */
final class General extends AbstractQuizGenerator {

    private boolean isStudentEligible(Statistics statistics) {
        return (Statistics.FinalVerdict.TBD == statistics.getFinalVerdict())
                || (Statistics.FinalVerdict.ATTEMPT_1_FAILED == statistics.getFinalVerdict());
    }

    /**
     * generates a general quiz through the question bank, shuffles the database response and returns only number of questions
     * for testing purposes, only a temporary utility is created. to be replaced by DB call
     *
     * @param noOfQuestions must be between 1 and 10
     * @return shuffled sublist of question bank
     */
    public List<QuestionModel> generateQuiz(int noOfQuestions, Statistics statistics) {
        boolean isEligible = isStudentEligible(statistics);
        if (isEligible) {
            if (noOfQuestions < 1 || noOfQuestions > 10) {
                throw new IllegalArgumentException("Number of questions must be between 1 and 10");
            }
            List<QuestionModel> totalQuestions = UtilitiesForTesting.getGeneralQuestions();
            Collections.shuffle(totalQuestions, new Random());
            List<QuestionModel> generalQuestions = new ArrayList<>();
            List<QuestionModel> attemptedQuestions = statistics.getAttemptedQuestions();
            if(attemptedQuestions == null || attemptedQuestions.isEmpty()){
                return totalQuestions.subList(0, noOfQuestions);
            }
            while(generalQuestions.size() < noOfQuestions) {
                if(totalQuestions.isEmpty()){
                    throw new IllegalStateException("Maximum attempts reached");
                }
                if(attemptedQuestions.contains(totalQuestions.getFirst())){
                    totalQuestions.removeFirst();
                }else{
                    generalQuestions.add(totalQuestions.getFirst());
                    totalQuestions.removeFirst();
                }
            }
            return generalQuestions;
        } else {
            throw new IllegalArgumentException("Student ineligible for general");
        }
    }
}
