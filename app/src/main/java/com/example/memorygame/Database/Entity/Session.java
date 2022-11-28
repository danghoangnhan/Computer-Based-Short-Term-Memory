package com.example.memorygame.Database.Entity;

import java.util.Date;

public class Session {
    private int sessionId;
    private User user;
    private Date logInTime;
    private Date startRound;
    private Date endRound;
    private Date startAD8Time;
    private Date endAD8Time;


    private Integer GameScore;
    private Integer AD8_Score;

    private Integer round1Score;
    private Integer round2Score;

    public Session(User user) {
        this.user = user;
        this.logInTime = new Date();
    }

    public Session(Integer SessionId,
                   String user_name,
                   Integer finalScore,
                   Integer AD8_Score,
                   String startRound,
                   String endRound,
                   String startAD8Time,
                   String endAD8Time) {
        this.sessionId = SessionId;

        this.user = new User();
        this.user.setName(user_name);
        this.GameScore = finalScore;
        this.setAD8_Score(AD8_Score);
        this.startRound = new Date(startRound);
        this.endRound = new Date(endRound);
        this.startAD8Time = new Date(startAD8Time);
        this.endAD8Time = new Date(endAD8Time);

        this.AD8_Score = AD8_Score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getStartRound() {
        return startRound;
    }

    public void setStartRound(Date startRound) {
        this.startRound = startRound;
    }

    public Date getEndRound() {
        return endRound;
    }

    public void setEndRound(Date endRound) {
        this.endRound = endRound;
    }

    public Integer getGameScore() {
        return GameScore;
    }

    public void setGameScore(Integer gameScore) {
        GameScore = gameScore;
    }

    public Integer getRound1Score() {
        return round1Score;
    }

    public void setRound1Score(Integer round1Score) {
        this.round1Score = round1Score;
    }

    public Integer getRound2Score() {
        return round2Score;
    }

    public void setRound2Score(Integer round2Score) {
        this.round2Score = round2Score;
    }
}
