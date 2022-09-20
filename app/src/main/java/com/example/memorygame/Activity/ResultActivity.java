package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.memorygame.HandleStageButton;
import com.example.memorygame.R;

public class ResultActivity extends AppCompatActivity implements HandleStageButton {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public void handleNextButton(View view) {

    }

    @Override
    public void handleReplayButton(View view) {

    }

    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }
}