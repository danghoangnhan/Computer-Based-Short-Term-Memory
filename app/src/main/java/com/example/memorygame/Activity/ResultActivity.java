package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;

public class ResultActivity extends AppCompatActivity implements HandleStageButton {
    Button escButton;
    Button replayButton;
    TextView result1,result2,result3,result4;
    Result result;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);
        this.escButton.setOnClickListener(this::handleEscButton);

        this.result1 = findViewById(R.id.result1);
        this.result2 = findViewById(R.id.result2);
        this.result3 = findViewById(R.id.result3);
        this.result4 = findViewById(R.id.result4);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        result = args.getParcelable("result");

        this.result4.setText(result.ObjectCorlorLocationValidation()+"/"+result.getCorrect().size());
        this.result1.setText(result.ObjectValidation()+"/"+result.getCorrect().size());
        this.result2.setText(result.LocationValidation()+"/"+result.getCorrect().size());
        this.result3.setText(result.ObjectLocationValidation()+"/"+result.getCorrect().size());
    }
    @Override
    public void handleNextButton(View view) {

    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }
}