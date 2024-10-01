package ac.ncl.advancedjava.service;

import ac.ncl.advancedjava.model.AnswerModel;
import ac.ncl.advancedjava.model.QuestionModel;
import ac.ncl.advancedjava.model.Quiz;
import ac.ncl.advancedjava.model.Student;

import java.util.List;

public class QuizGenerator implements Quiz {

    @Override
    public List<QuestionModel> generateQuiz(int numberOfQuestions) {
        return List.of();
    }

    @Override
    public double takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers) {
        return 0;
    }
}
