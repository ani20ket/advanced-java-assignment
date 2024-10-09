package uk.ac.ncl.advancedjava.model;

import java.util.List;

public class QuestionModel {
    private String question;
    private List<OptionModel> options;
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<OptionModel> options) {
        this.options = options;
    }

    public String toString() {
        StringBuilder questionObject = new StringBuilder(question + "\n");
        if (options != null) {
            for (OptionModel option : options) {
                questionObject.append(option).append("\n");
            }
        }
        return questionObject.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof QuestionModel) {
            QuestionModel questionModel = (QuestionModel) obj;
            return question.equals(questionModel.question);
        }
        return false;
    }
}
