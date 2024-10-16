package uk.ac.ncl.advancedjava.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.advancedjava.model.*;
import uk.ac.ncl.advancedjava.service.AbstractQuizGenerator;
import uk.ac.ncl.advancedjava.service.Quiz;
import uk.ac.ncl.advancedjava.util.UtilitiesForTesting;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class E2EQuizTest {

    private Student firstStudent;
    private Student secondStudent;

    List<QuestionModel> generalQuestions;
    List<QuestionModel> revisionQuestions;

    Quiz generalQuiz;
    Quiz revisionQuiz;

    Statistics firstStudentStatistics;
    Statistics secondStudentStatistics;

    private List<AnswerModel> answerQuestionsCorrectly(List<QuestionModel> questionModels){
        List<AnswerModel> answerModels = new ArrayList<>();
        for(QuestionModel questionModel : questionModels){
            AnswerModel answerModel = new AnswerModel();
            answerModel.setQuestionNo(questionModels.indexOf(questionModel) + 1);
            if(questionModel.getCorrectAnswer() != null){
                answerModel.setAnswer(questionModel.getCorrectAnswer());
            } else{
                StringBuilder stringBuilder = new StringBuilder();
                for(OptionModel optionModel : questionModel.getOptions()){
                    if(optionModel.isCorrect()){
                        stringBuilder.append(optionModel.getOption()).append(",");
                    }
                }
                answerModel.setAnswer(stringBuilder.toString());
            }

            answerModels.add(answerModel);
        }
        return answerModels;
    }

    private List<AnswerModel> answerQuestionsIncorrect(List<QuestionModel> questionModels){
        List<AnswerModel> answerModels = new ArrayList<>();
        for(QuestionModel questionModel : questionModels){
            AnswerModel answerModel = new AnswerModel();
            answerModel.setQuestionNo(questionModels.indexOf(questionModel) + 1);
            answerModel.setAnswer("incorrectAnswer");
            answerModels.add(answerModel);
        }
        return answerModels;
    }

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
    public void generalQuizIneligibleStudent() {
        firstStudentStatistics.setFinalVerdict(Statistics.FinalVerdict.FAIL);
        assertThrows(IllegalArgumentException.class, () -> generalQuiz.generateQuiz(10, firstStudentStatistics));
    }

    @Test
    public void generalQuizNoOfQuestionsExceeded() {
        assertThrows(IllegalArgumentException.class, () -> generalQuiz.generateQuiz(1000, firstStudentStatistics));
    }

    @Test
    public void revisionQuizIneligibleStudent() {
        firstStudentStatistics.setFinalVerdict(Statistics.FinalVerdict.FAIL);
        assertThrows(IllegalArgumentException.class, () -> revisionQuiz.generateQuiz(10, firstStudentStatistics));
    }

    @Test
    public void revisionQuizNoOfQuestionsExceeded() {
        assertThrows(IllegalArgumentException.class, () -> revisionQuiz.generateQuiz(1000, firstStudentStatistics));
    }

    @Test
    public void generalQuizHappyPath() {
        List<QuestionModel> generalQuestionPaper = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels = answerQuestionsCorrectly(generalQuestionPaper);
        generalQuestionPaper.forEach(System.out::println);
        answerModels.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper, answerModels, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(0, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(10, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.PASS, firstStudentStatistics.getFinalVerdict());
    }

    @Test
    public void generalQuizOneFailureRevision1Attempt() {
        List<QuestionModel> generalQuestionPaper = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels = answerQuestionsIncorrect(generalQuestionPaper);
        generalQuestionPaper.forEach(System.out::println);
        answerModels.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper, answerModels, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(0, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> revisionQuestionPaper = revisionQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> revisionAnswerModels = answerQuestionsCorrectly(revisionQuestionPaper);
        revisionQuestionPaper.forEach(System.out::println);
        revisionAnswerModels.forEach(System.out::println);
        firstStudentStatistics = revisionQuiz.takeQuiz(firstStudent, revisionQuestionPaper, revisionAnswerModels, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(1, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());
    }

    @Test
    public void generalQuizBothAttemptsFAIL() {
        List<QuestionModel> revisionQuestionPaper = revisionQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> revisionAnswerModels = answerQuestionsCorrectly(revisionQuestionPaper);
        revisionQuestionPaper.forEach(System.out::println);
        revisionAnswerModels.forEach(System.out::println);
        firstStudentStatistics = revisionQuiz.takeQuiz(firstStudent, revisionQuestionPaper, revisionAnswerModels, firstStudentStatistics);
        assertEquals(0, firstStudentStatistics.getNoOfAttempts());
        assertEquals(1, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.TBD, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> generalQuestionPaper = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels = answerQuestionsIncorrect(generalQuestionPaper);
        generalQuestionPaper.forEach(System.out::println);
        answerModels.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper, answerModels, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(1, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> revisionQuestionPaper2 = revisionQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> revisionAnswerModels2 = answerQuestionsCorrectly(revisionQuestionPaper2);
        revisionQuestionPaper2.forEach(System.out::println);
        revisionAnswerModels2.forEach(System.out::println);
        firstStudentStatistics = revisionQuiz.takeQuiz(firstStudent, revisionQuestionPaper2, revisionAnswerModels2, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(2, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> generalQuestionPaper2 = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels2 = answerQuestionsIncorrect(generalQuestionPaper2);
        generalQuestionPaper2.forEach(System.out::println);
        answerModels2.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper2, answerModels2, firstStudentStatistics);
        assertEquals(2, firstStudentStatistics.getNoOfAttempts());
        assertEquals(2, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.FAIL, firstStudentStatistics.getFinalVerdict());
    }

    @Test
    public void generalQuizFirstFailSecondPass() {
        List<QuestionModel> revisionQuestionPaper = revisionQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> revisionAnswerModels = answerQuestionsCorrectly(revisionQuestionPaper);
        revisionQuestionPaper.forEach(System.out::println);
        revisionAnswerModels.forEach(System.out::println);
        firstStudentStatistics = revisionQuiz.takeQuiz(firstStudent, revisionQuestionPaper, revisionAnswerModels, firstStudentStatistics);
        assertEquals(0, firstStudentStatistics.getNoOfAttempts());
        assertEquals(1, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.TBD, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> generalQuestionPaper = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels = answerQuestionsIncorrect(generalQuestionPaper);
        generalQuestionPaper.forEach(System.out::println);
        answerModels.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper, answerModels, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(1, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> revisionQuestionPaper2 = revisionQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> revisionAnswerModels2 = answerQuestionsCorrectly(revisionQuestionPaper2);
        revisionQuestionPaper2.forEach(System.out::println);
        revisionAnswerModels2.forEach(System.out::println);
        firstStudentStatistics = revisionQuiz.takeQuiz(firstStudent, revisionQuestionPaper2, revisionAnswerModels2, firstStudentStatistics);
        assertEquals(1, firstStudentStatistics.getNoOfAttempts());
        assertEquals(2, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(0, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.ATTEMPT_1_FAILED, firstStudentStatistics.getFinalVerdict());

        List<QuestionModel> generalQuestionPaper2 = generalQuiz.generateQuiz(10, firstStudentStatistics);
        List<AnswerModel> answerModels2 = answerQuestionsCorrectly(generalQuestionPaper2);
        generalQuestionPaper2.forEach(System.out::println);
        answerModels2.forEach(System.out::println);
        firstStudentStatistics = generalQuiz.takeQuiz(firstStudent, generalQuestionPaper2, answerModels2, firstStudentStatistics);
        assertEquals(2, firstStudentStatistics.getNoOfAttempts());
        assertEquals(2, firstStudentStatistics.getNoOfRevisions());
        assertEquals("First Student", firstStudentStatistics.getName());
        assertEquals(22, firstStudentStatistics.getAge());
        assertEquals(LocalDate.of(2002, Month.NOVEMBER, 20), firstStudentStatistics.getBirthDate());
        assertEquals(1, firstStudentStatistics.getId());
        assertEquals(10, firstStudentStatistics.getFinalScoreOutOfTen());
        assertEquals(Statistics.FinalVerdict.PASS, firstStudentStatistics.getFinalVerdict());
    }

}
