package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memorygame.Activity.AD8Activity;
import com.example.memorygame.R;

public class AD8WelComeActivity extends AppCompatActivity {
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad8_wel_come);
        start = findViewById(R.id.ad8_start_button);
        start.setOnClickListener(v -> handleStartButton(v));
    }
    public void handleStartButton(View v){
        Intent intent = new Intent(this, AD8Activity.class);
        this.startActivity(intent);
    }
}