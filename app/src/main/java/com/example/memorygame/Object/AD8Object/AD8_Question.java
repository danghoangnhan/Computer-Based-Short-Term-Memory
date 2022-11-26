package com.example.memorygame.Object.AD8Object;

import java.util.List;

public class AD8_Question {

    private Integer questionNumber;
    private String question;
    private List<AD8_Answer> AnswerList;
    private AD8_Answer currentAnswer;

    public AD8_Question(Integer questionNumber,String question, List<AD8_Answer> answerList) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.AnswerList = answerList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AD8_Answer> getAnswerList() {
        return AnswerList;
    }

    public void setAnswerList(List<AD8_Answer> answerList) {
        AnswerList = answerList;
    }

    public AD8_Answer getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(AD8_Answer currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public  int getScore(){
        return this.currentAnswer.getPoint();
    }
}
