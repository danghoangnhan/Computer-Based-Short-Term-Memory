package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class WaitingActivity extends AppCompatActivity implements HandleStageButton {
    Timer timer;
    TextView textView1,textView2;
    private ArrayList<MatchingObject> objectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Bundle args = getIntent().getExtras();
        this.objectList = args.getParcelableArrayList("ARRAYLIST");
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               Intent intent = new Intent(WaitingActivity.this,PredictActivity.class);
                               Bundle args = new Bundle();
                               args.putSerializable("ARRAYLIST", objectList);
                               intent.putExtra("BUNDLE",args);
                               startActivity(intent);
                               startActivity(intent);
                           }
                       },
                5000);
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {

                           }
                       },
                1000);
    }

    @Override
    public void handleNextButton(View view) {

    }

    @Override
    public void handleReplayButton(View view) {

    }

    @Override
    public void handleEscButton(View view) {

    }
}