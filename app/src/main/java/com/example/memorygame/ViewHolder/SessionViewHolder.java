package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;

public class SessionViewHolder  extends RecyclerView.ViewHolder{
    private TextView username,gamescore,startgametime,endgametime,ad8score,ad8starttime,ad8endtime;
    public SessionViewHolder(@NonNull View itemView)
    {
        super(itemView);
        username = itemView.findViewById(R.id.user_name);
        gamescore = itemView.findViewById(R.id.finalScore);
        startgametime = itemView.findViewById(R.id.startgame_time);
        endgametime = itemView.findViewById(R.id.endgame_time);
        ad8score = itemView.findViewById(R.id.ad8_score);
        ad8starttime = itemView.findViewById(R.id.ad8_starttime);
        ad8endtime = itemView.findViewById(R.id.ad8_endtime);
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public TextView getGamescore() {
        return gamescore;
    }

    public void setGamescore(TextView gamescore) {
        this.gamescore = gamescore;
    }

    public TextView getStartgametime() {
        return startgametime;
    }

    public void setStartgametime(TextView startgametime) {
        this.startgametime = startgametime;
    }

    public TextView getEndgametime() {
        return endgametime;
    }

    public void setEndgametime(TextView endgametime) {
        this.endgametime = endgametime;
    }

    public TextView getAd8score() {
        return ad8score;
    }

    public void setAd8score(TextView ad8score) {
        this.ad8score = ad8score;
    }

    public TextView getAd8starttime() {
        return ad8starttime;
    }

    public void setAd8starttime(TextView ad8starttime) {
        this.ad8starttime = ad8starttime;
    }

    public TextView getAd8endtime() {
        return ad8endtime;
    }

    public void setAd8endtime(TextView ad8endtime) {
        this.ad8endtime = ad8endtime;
    }
}
