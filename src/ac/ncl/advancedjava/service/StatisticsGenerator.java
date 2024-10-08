package ac.ncl.advancedjava.service;

import ac.ncl.advancedjava.model.Statistics;
import ac.ncl.advancedjava.model.Student;

public class StatisticsGenerator {

    /**
     * generates statistics for a student after they take general/revision quiz
     *
     * @param student
     * @return statistics
     */
    public Statistics generateStatistics(Student student, double score, boolean isRevision) {
        Statistics statistics = new Statistics(student);
        int noOfAttempts = statistics.getNoOfAttempts();
        int noOfRevisions = statistics.getNoOfRevisions();
        noOfAttempts++;
        if (isRevision) {
            noOfRevisions++;
        }
        if (score < 0.4) {
            statistics.setFinalVerdict(Statistics.FinalVerdict.FAIL);
        } else {
            statistics.setFinalVerdict(Statistics.FinalVerdict.PASS);
        }
        statistics.setNoOfAttempts(noOfAttempts);
        statistics.setNoOfRevisions(noOfRevisions);
        statistics.setFinalScoreOutOfTen(score * 10);
        // Save to DB
        return statistics;
    }
}
