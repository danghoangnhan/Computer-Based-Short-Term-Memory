package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memorygame.R;

public class PredictActivity extends AppCompatActivity {

    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> handleNextButton(v));
    }
    public void handleNextButton(View view){
        Intent intent = new Intent(this,ResultActivity.class);
        startActivity(intent);
    }
}