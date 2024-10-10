package uk.ac.ncl.advancedjava.model;

public class OptionModel {
    private char option;
    private String description;
    private boolean isCorrect;

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    public static OptionModel valueOf(String option) {
        OptionModel optionModel = new OptionModel();
        String[] options = option.split("\\|");
        optionModel.option = options[0].charAt(0);
        optionModel.description = options[1];
        optionModel.isCorrect = Boolean.parseBoolean(options[2]);
        return optionModel;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return option + ": " + description;
    }
}
