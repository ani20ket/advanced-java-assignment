package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.model.Statistics;
import uk.ac.ncl.advancedjava.model.Student;

import java.util.List;

public class StatisticsGenerator {

    /**
     * generates statistics for a student after they take general/revision quiz
     *
     * @param score
     * @param isRevision
     * @param statistics
     * @param attemptedQuestions
     * @return
     */
    public Statistics generateStatistics(double score, boolean isRevision, Statistics statistics, List<QuestionModel> attemptedQuestions) {
        int noOfAttempts = statistics.getNoOfAttempts();
        int noOfRevisions = statistics.getNoOfRevisions();
        if (isRevision) {
            noOfRevisions++;
        }else{
            if (score < 0.4 && noOfAttempts == 0) {
                statistics.setFinalVerdict(Statistics.FinalVerdict.ATTEMPT_1_FAILED);
            }else if(score < 0.4 && noOfAttempts > 0){
                statistics.setFinalVerdict(Statistics.FinalVerdict.FAIL);
            }else{
                statistics.setFinalVerdict(Statistics.FinalVerdict.PASS);
            }
            statistics.setFinalScoreOutOfTen(score * 10);
            noOfAttempts++;
        }
        statistics.setNoOfAttempts(noOfAttempts);
        statistics.setNoOfRevisions(noOfRevisions);
        statistics.getAttemptedQuestions().addAll(attemptedQuestions);
        // Save to DB
        System.out.println(statistics);
        return statistics;
    }
}
