package ac.ncl.advancedjava.model;

public class AnswerModel {
    private int questionNo;
    private String answer;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return questionNo + " : " + answer;
    }
}
