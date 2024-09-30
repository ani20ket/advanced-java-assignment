package ac.ncl.advancedjava.model;

import java.time.LocalDate;

public class Statistics extends Student {
    private FinalVerdict finalVerdict;
    private int noOfAttempts;
    private int noOfRevisions;


    public Statistics(String name, int age, LocalDate birthDate) {
        super(name, age, birthDate);
    }



    public enum FinalVerdict{
        PASS, FAIL, TBD;
    }
}
