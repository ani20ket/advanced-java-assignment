package uk.ac.ncl.advancedjava.service;

import uk.ac.ncl.advancedjava.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * This class contains methods for taking quiz which evaluates the student and returns the score between 0 and 1 in double format
 * Inheritors - General.java and Revision.java
 */
public abstract class AbstractQuizGenerator implements Quiz {


    public static Quiz getInstance(String quiz) {
        if (quiz.equals("GENERAL")) {
            return new General();
        } else {
            return new Revision();
        }
    }



    /**
     * Student takes quiz and passes answers and question paper to this method
     *
     * @param student
     * @param questions
     * @param answers
     * @return score between 0 and 1
     */
    public Statistics takeQuiz(Student student, List<QuestionModel> questions, List<AnswerModel> answers, Statistics statistics) {
        double correctAnswers = 0;
        for (QuestionModel question : questions) {
            Optional<AnswerModel> answerModelOptional = answers.stream().filter(answer ->
                    answer.getQuestionNo() == (questions.indexOf(question) + 1)).findFirst();
            if (answerModelOptional.isPresent()) {
                AnswerModel answerModel = answerModelOptional.get();
                boolean isCorrect;
                if (question.getCorrectAnswer() != null) {
                    isCorrect = evaluateFreeResponseQuestion(question, answerModel);
                } else {
                    isCorrect = evaluateQuestionWithOptions(question, answerModel);
                }
                if (isCorrect) {
                    correctAnswers++;
                }
            }
        }
        double score = correctAnswers / questions.size();
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        return statisticsGenerator.generateStatistics(score, this instanceof Revision, statistics, questions);
    }

    /**
     * evaluates questions accepting answer as a free text
     *
     * @param question
     * @param answerModel
     * @return correct/incorrect
     */
    private boolean evaluateFreeResponseQuestion(QuestionModel question, AnswerModel answerModel) {
        List<String> freeTextAnswer = Arrays.stream(answerModel.getAnswer().trim().split(" ")).toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (String answer : freeTextAnswer) {
            stringBuilder.append(answer).append(" ");
        }
        String answerWithoutSpaces = stringBuilder.toString().trim();
        return question.getCorrectAnswer().equalsIgnoreCase(answerWithoutSpaces);
    }

    /**
     * evaluates questions accepting answer as Multiple Choice
     *
     * @param question
     * @param answerModel
     * @return if all options selected are correct or not
     */
    private boolean evaluateQuestionWithOptions(QuestionModel question, AnswerModel answerModel) {
        List<String> options = Arrays.stream(answerModel.getAnswer().toLowerCase().split(",")).toList();
        List<OptionModel> optionModelList = question.getOptions();
        for(String option : options) {
            Optional<OptionModel> optionalOptionModel = optionModelList.stream().filter(op -> option.equalsIgnoreCase(String.valueOf(op.getOption()))).findFirst();
            if(optionalOptionModel.isEmpty()) {
                return false;
            }else if(!optionalOptionModel.get().isCorrect()){
                return false;
            }
        }
        return true;
    }

}
