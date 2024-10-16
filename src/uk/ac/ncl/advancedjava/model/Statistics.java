package uk.ac.ncl.advancedjava.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends Student {
    private FinalVerdict finalVerdict;
    private int noOfAttempts;
    private int noOfRevisions;
    private double finalScoreOutOfTen;
    private List<QuestionModel> attemptedQuestions;


    public Statistics(Student student) {
        super(student.getName(), student.getAge(), student.getBirthDate(), student.getId());
        this.attemptedQuestions = new ArrayList<>();
        this.setFinalVerdict(FinalVerdict.TBD);
    }

    public double getFinalScoreOutOfTen() {
        return finalScoreOutOfTen;
    }

    public void setFinalScoreOutOfTen(double finalScoreOutOfTen) {
        this.finalScoreOutOfTen = finalScoreOutOfTen;
    }

    public FinalVerdict getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(FinalVerdict finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public int getNoOfRevisions() {
        return noOfRevisions;
    }

    public void setNoOfRevisions(int noOfRevisions) {
        this.noOfRevisions = noOfRevisions;
    }

    public enum FinalVerdict {
        PASS, FAIL, TBD, ATTEMPT_1_FAILED;
    }

    public List<QuestionModel> getAttemptedQuestions() {
        return attemptedQuestions;
    }

    @Override
    public String toString() {
        String student = super.toString();
        return student.concat("\n")
                .concat("No. of tests appeared: "+ this.noOfAttempts).concat("\n")
                .concat("No. of revision attempts: "+ this.noOfRevisions).concat("\n")
                .concat("Final score out of 10: "+ this.finalScoreOutOfTen).concat("\n")
                .concat("This student is "+ this.finalVerdict);
    }
}
