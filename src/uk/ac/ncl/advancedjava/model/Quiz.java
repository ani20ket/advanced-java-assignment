package uk.ac.ncl.advancedjava.model;

import java.util.List;

public interface Quiz {
    double takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers);
}
