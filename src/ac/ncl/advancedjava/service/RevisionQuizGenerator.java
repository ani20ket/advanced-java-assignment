package ac.ncl.advancedjava.service;

import ac.ncl.advancedjava.model.AnswerModel;
import ac.ncl.advancedjava.model.QuestionModel;
import ac.ncl.advancedjava.model.Quiz;
import ac.ncl.advancedjava.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RevisionQuizGenerator implements Quiz {

    @Override
    public List<QuestionModel> generateQuiz(int numberOfQuestions) {
        return new ArrayList<>();
    }

    @Override
    public double takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers) {
        return 0;
    }
}
