package com.example.memorygame.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memorygame.R;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
public class WaitingActivity extends AppCompatActivity {
    Timer timer;
    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WaitingActivity.this,PredictActivity.class);
                startActivity(intent);
            }
        },5000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },1000);
    }
}