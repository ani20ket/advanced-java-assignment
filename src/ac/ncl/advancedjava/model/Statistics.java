package ac.ncl.advancedjava.model;

import java.time.LocalDate;

public class Statistics extends Student {
    private FinalVerdict finalVerdict;
    private int noOfAttempts;
    private int noOfRevisions;
    private double finalScoreOutOfTen;


    public Statistics(Student student) {
        super(student.getName(), student.getAge(), student.getBirthDate());
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
        PASS, FAIL, TBD;
    }
}
