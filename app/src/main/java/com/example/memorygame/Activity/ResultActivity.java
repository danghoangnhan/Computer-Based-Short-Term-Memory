package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;

public class ResultActivity extends AppCompatActivity implements HandleStageButton {
    Button escButton,nextButton,replayButton;
    TextView result1,result2,result3,result4,result5,result_1,result_2,total_result;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Result result = GlobalObject.getInstance().getResult();
        super.onCreate(savedInstanceState);
        if (GlobalObject.getInstance().getGameState()==1){
            setContentView(R.layout.activity_result_1);

            this.nextButton = findViewById(R.id.nextButton);
            this.nextButton.setOnClickListener(this::handleNextButton);

            result.setResult1((int) result.ObjectValidation(result.getSelected1()));
            result.setResult2((int) result.SpatialValidation(result.getSelected2()));
            result.setResult3((int) result.ObjectSpatialValidation(result.getSelected2()));

        }
        else {
            setContentView(R.layout.activity_result_2);

            this.result4 = findViewById(R.id.result4);
            this.result4 = findViewById(R.id.result4);
            this.result4.setText(result.CorlorValidation(result.getSelected3())+"/"+result.getCorrect().size());

            this.result5 = findViewById(R.id.result5);
            this.result5 = findViewById(R.id.result5);
            this.result5.setText(result.ObjectCorlorLocationValidation(result.getSelected3())+"/"+result.getCorrect().size());

            this.replayButton = findViewById(R.id.replayButton);
            this.replayButton.setOnClickListener(this::handleReplayButton);

            this.result5.setText(result.ObjectCorlorLocationValidation(result.getSelected3())+"/"+result.getCorrect().size());

            this.total_result  = findViewById(R.id.result_total);
            this.result_1  = findViewById(R.id.result_1);
            this.result_2  = findViewById(R.id.result_2);

            int score1 =  result.getResult1()+
                    result.getResult2()+
                    result.getResult3();
            int score2 = (int) ((int) result.ObjectValidation (result.getSelected1())
                                +result.SpatialValidation(result.getSelected2())
                                +result.ObjectSpatialValidation(result.getSelected2())
                                +result.CorlorValidation(result.getSelected3())
                                + result.ObjectCorlorLocationValidation(result.getSelected3()));

            int score = score1+score2 ;
            this.result_1.setText(score1+"/"+9);
            this.result_2.setText(score2+"/"+15);
            this.total_result.setText(score+"/"+(24));

        }
        this.escButton = findViewById(R.id.escButton);
        this.escButton.setOnClickListener(this::handleEscButton);


        this.result1 = findViewById(R.id.result1);
        this.result1.setText(result.ObjectValidation(result.getSelected1())+"/"+result.getCorrect().size());

        this.result2 = findViewById(R.id.result2);
        this.result2.setText(result.SpatialValidation(result.getSelected2())+"/"+result.getCorrect().size());

        this.result3 = findViewById(R.id.result3);
        this.result3.setText(result.ObjectSpatialValidation(result.getSelected2())+"/"+result.getCorrect().size());

    }
    @Override
    public void handleNextButton(View view) {
        if (GlobalObject.getInstance().getGameState()==1){
            GlobalObject.getInstance().setGameState(2);
        }
        else {
            GlobalObject.getInstance().setGameState(1);
        }
        Intent intent = new Intent(this,SelectObjectActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this,SelectObjectActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }
}