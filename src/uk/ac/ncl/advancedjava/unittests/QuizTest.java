package uk.ac.ncl.advancedjava.unittests;

import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.model.Statistics;
import uk.ac.ncl.advancedjava.service.Quiz;
import uk.ac.ncl.advancedjava.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.advancedjava.service.AbstractQuizGenerator;
import uk.ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {

    private Student firstStudent;
    private Student secondStudent;

    List<QuestionModel> generalQuestions;
    List<QuestionModel> revisionQuestions;

    Quiz generalQuiz;
    Quiz revisionQuiz;

    Statistics firstStudentStatistics;
    Statistics secondStudentStatistics;

    @BeforeEach
    public void setup() {
        firstStudent = new Student("First Student", 22, LocalDate.of(2002, Month.NOVEMBER, 20), 1);
        secondStudent = new Student("Second Student", 23, LocalDate.of(2001, Month.OCTOBER, 26), 2);
        firstStudentStatistics = new Statistics(firstStudent);
        secondStudentStatistics = new Statistics(secondStudent);
        generalQuestions = UtilitiesForTesting.getGeneralQuestions();
        revisionQuestions = UtilitiesForTesting.getRevisionQuestions();
        generalQuiz = AbstractQuizGenerator.getInstance("GENERAL");
        revisionQuiz = AbstractQuizGenerator.getInstance("REVISION");
    }

    @Test
    public void generalQuizHappyPath() {
        List<QuestionModel> generalQuestionPaper = generalQuiz.generateQuiz(10, firstStudentStatistics);

    }

}
