package ac.ncl.advancedjava.model;

import java.util.List;

public interface Quiz {

    List<QuestionModel> generateQuiz(int numberOfQuestions);
    double takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers);
}
