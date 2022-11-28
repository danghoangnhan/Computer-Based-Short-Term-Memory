package com.example.memorygame.Database.Entity;

import java.util.Date;

public class Session {
    private User user;
    private Date logInTime;
    private Date startRound1;
    private Date endRound1;
    private Date startRound2;
    private Date getEndRound2;
    private Date startAD8Time;
    private Date endAD8Time;
    private Integer Round1Score;
    private Integer Round2Score;
    private Integer FinalScore;
    private Integer AD8_Score;

    public Session(User user) {
        this.user = user;
        this.logInTime = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(Date logInTime) {
        this.logInTime = logInTime;
    }

    public Date getStartRound1() {
        return startRound1;
    }

    public void setStartRound1(Date startRound1) {
        this.startRound1 = startRound1;
    }

    public Date getEndRound1() {
        return endRound1;
    }

    public void setEndRound1(Date endRound1) {
        this.endRound1 = endRound1;
    }

    public Date getStartRound2() {
        return startRound2;
    }

    public void setStartRound2(Date startRound2) {
        this.startRound2 = startRound2;
    }

    public Date getGetEndRound2() {
        return getEndRound2;
    }

    public void setGetEndRound2(Date getEndRound2) {
        this.getEndRound2 = getEndRound2;
    }

    public Integer getRound1Score() {
        return Round1Score;
    }

    public void setRound1Score(Integer round1Score) {
        Round1Score = round1Score;
    }

    public Integer getRound2Score() {
        return Round2Score;
    }

    public void setRound2Score(long round2Score) {
        Round2Score = Math.toIntExact(round2Score);
    }

    public Integer getFinalScore() {
        return FinalScore;
    }

    public void setFinalScore(Integer finalScore) {
        FinalScore = finalScore;
    }

    public Integer getAD8_Score() {
        return AD8_Score;
    }

    public void setAD8_Score(Integer AD8_Score) {
        this.AD8_Score = AD8_Score;
    }

    public Date getStartAD8Time() {
        return startAD8Time;
    }

    public void setStartAD8Time(Date startAD8Time) {
        this.startAD8Time = startAD8Time;
    }

    public Date getEndAD8Time() {
        return endAD8Time;
    }

    public void setEndAD8Time(Date endAD8Time) {
        this.endAD8Time = endAD8Time;
    }
}
