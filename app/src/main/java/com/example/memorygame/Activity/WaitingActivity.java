package com.example.memorygame.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.Dialog.SkipWaitingDialog;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Language;
import com.example.memorygame.Listener.ClickListener.SkipWaitingDialogButtonAction;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;

import java.util.ArrayList;
import java.util.Timer;
public class WaitingActivity extends AppCompatActivity
        implements HandleStageButton, SkipWaitingDialogButtonAction {
    TextView textTime, notifyText;
    CountDownTimer myCountDownTimer;
    private ArrayList<MatchingObject> objectList;
    private Button nextButton;
    private Button replayButton;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        this.textTime = findViewById(R.id.time);
        this.notifyText = findViewById(R.id.notifytext);
        this.notifyText.setText(Language.Chinese.get(GlobalObject.getInstance().getGameState() == 1 ? Language.Key.PleaseRememberObject : Language.Key.PleaseRememberObjectLocationColor));
        this.nextButton = findViewById(R.id.nextButton);
        Button escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.nextButton.setOnClickListener(this::handleNextButton);
        escButton.setOnClickListener(this::handleEscButton);


        this.replayButton.setOnClickListener(this::handleReplayButton);
        Bundle args = getIntent().getExtras();
        this.objectList = args.getParcelableArrayList("ARRAYLIST");
        timerStart(60000,1000);
    }


    @Override
    public void handleNextButton(View view) {
        openDialog();
    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this, WaitingActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    public void openDialog() {
        myCountDownTimer.cancel();
        SkipWaitingDialog skipWaitingDialog = new SkipWaitingDialog();
        skipWaitingDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void HandleConfirmButton() {
        Intent intent = new Intent(WaitingActivity.this, PredictActivity1.class);
        Bundle args = new Bundle();
        args.putParcelableArrayList("ARRAYLIST", objectList);
        intent.putExtra("BUNDLE", args);
        startActivity(intent);
        startActivity(intent);
    }

    @Override
    public void HandleCancleButton() {
        timerStart(this.timeLeft,1000);
    }

    public void timerStart(long timeLengthMilli,Integer interval) {
        myCountDownTimer = new CountDownTimer(timeLengthMilli, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                long totalTime = (millisUntilFinished / 1000);
                Long minutes = totalTime / 60;
                long seconds = totalTime % 60;
                textTime.setText(minutes + "分" + seconds + "秒");
            }

            @Override
            public void onFinish() {
                HandleConfirmButton();
            }
        };
        myCountDownTimer.start();
    }
}