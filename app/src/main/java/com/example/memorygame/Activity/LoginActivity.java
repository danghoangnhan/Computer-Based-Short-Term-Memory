package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.memorygame.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(view -> changeActiity());
    }
    private void changeActiity(){
        Intent intent = new Intent(this,SelectObjectActivity.class);
        startActivity(intent);
    }
}