package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(view -> changeActiity());
    }
    private void changeActiity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}