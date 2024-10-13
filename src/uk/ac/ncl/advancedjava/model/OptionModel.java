package uk.ac.ncl.advancedjava.model;

public class OptionModel {
    private char option;
    private String description;
    private boolean isCorrect;

    public char getOption() {
        return option;
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

    public String toString() {
        return option + ": " + description;
    }
}
