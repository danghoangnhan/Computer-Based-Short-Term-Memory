package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar();
        List<Integer> images = Arrays.asList(R.drawable.coala,R.drawable.camel,R.drawable.fox,R.drawable.lion,R.drawable.fox);
        List<Button> buttons = Arrays.asList(R.drawable.);
        Integer clicked = 0;
        boolean turnOver = false;
        Integer cardBack = 1;

    }
}