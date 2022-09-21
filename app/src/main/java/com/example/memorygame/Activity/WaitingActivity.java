package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;

import java.util.ArrayList;
import java.util.Timer;
public class WaitingActivity extends AppCompatActivity implements HandleStageButton {
    Timer timer;
    TextView textTime;
    CountDownTimer myCountDownTimer;
    private ArrayList<MatchingObject> objectList;
    private Button nextButton,escButton,replayButton;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        textTime = findViewById(R.id.time);

        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);

        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);

        Bundle args = getIntent().getExtras();
        this.objectList = args.getParcelableArrayList("ARRAYLIST");
        timer = new Timer();
        myCountDownTimer =  new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Long totalTime = (millisUntilFinished / 1000);
                Long minutes = totalTime /60;
                Long seconds = totalTime %60;
                textTime.setText(minutes +"分" +seconds+"秒");
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {
                handleNextButton(null);
            }
        };
        myCountDownTimer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void handleNextButton(View view) {
        myCountDownTimer.cancel();

        Intent intent = new Intent(WaitingActivity.this,PredictActivity.class);
        Bundle args = new Bundle();
        args.putParcelableArrayList("ARRAYLIST",objectList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
        startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this,WaitingActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }

}