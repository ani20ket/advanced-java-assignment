package uk.ac.ncl.advancedjava.unittests;

import uk.ac.ncl.advancedjava.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {

    private Student firstStudent;
    private Student secondStudent;

    @BeforeEach
    public void setup() {
        firstStudent = new Student("First Student", 22, LocalDate.of(2002, Month.NOVEMBER, 20));
        secondStudent = new Student("Second Student", 23, LocalDate.of(2001, Month.OCTOBER, 26));
    }

    @Test
    public void testQuiz() {
        assertEquals(0, 0);
    }

}
