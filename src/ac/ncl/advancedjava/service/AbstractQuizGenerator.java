package ac.ncl.advancedjava.service;

import ac.ncl.advancedjava.model.AnswerModel;
import ac.ncl.advancedjava.model.QuestionModel;
import ac.ncl.advancedjava.model.Quiz;
import ac.ncl.advancedjava.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AbstractQuizGenerator implements Quiz {

    public double takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers) {
        double correctAnswers = 0;
        for(QuestionModel question : questions) {
            Optional<AnswerModel> answerModelOptional = answers.stream().filter(answer ->
                    answer.getQuestionNo() == (questions.indexOf(question)+1)).findFirst();
            if(answerModelOptional.isPresent()){
                AnswerModel answerModel = answerModelOptional.get();
                if(question.getCorrectAnswer().contains(",")){
                    boolean isCorrect = evaluateQuestionWithOptions(question, answerModel);
                    if(isCorrect){
                        correctAnswers++;
                    }
                }
            }
        }
        return correctAnswers/questions.size();
    }

    private boolean evaluateQuestionWithOptions(QuestionModel question, AnswerModel answerModel) {
        List<String> options = Arrays.stream(answerModel.getAnswer().split(",")).toList();
        List<String> correctAnswers = Arrays.stream(question.getCorrectAnswer().split(",")).toList();
        for(String option : options){
            if(!correctAnswers.contains(option)){
                return false;
            }
        }
        return true;
    }
}
