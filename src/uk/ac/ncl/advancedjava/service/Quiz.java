package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.AnswerModel;
import uk.ac.ncl.advancedjava.model.QuestionModel;
import uk.ac.ncl.advancedjava.model.Statistics;
import uk.ac.ncl.advancedjava.model.Student;

import java.util.List;

public interface Quiz {
    Statistics takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers);
    List<QuestionModel> generateQuiz(int noOfQuestions, Statistics statistics);
}
