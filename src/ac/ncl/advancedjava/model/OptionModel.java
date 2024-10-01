package ac.ncl.advancedjava.model;

public class OptionModel {
    private String option;
    private boolean selected;
    private boolean isCorrect;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String toString(){
        return option;
    }
}
