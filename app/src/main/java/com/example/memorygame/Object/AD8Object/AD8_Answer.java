package com.example.memorygame.Object.AD8Object;

public class AD8_Answer {
    private Integer point;
    public String AnswerDisplay;

    public AD8_Answer(Integer point, String answerDisplay) {
        this.point = point;
        AnswerDisplay = answerDisplay;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getAnswerDisplay() {

        return AnswerDisplay;
    }

    public void setAnswerDisplay(String answerDisplay) {
        AnswerDisplay = answerDisplay;
    }
}
