package com.example.memorygame.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.memorygame.Database.DB_Instance;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;

import java.util.Date;

public class ResultActivity extends AppCompatActivity implements HandleStageButton {
    Button escButton,nextButton,replayButton,AD8Button;
    CardView finalScore;
    TextView result1,result2,result3,result4,result5,result_1,result_2,total_result;
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
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
            GlobalObject.getInstance().getSession().setRound1Score(result.getResult1()+
                    result.getResult2()+
                    result.getResult3());
        }
        else {
            GlobalObject.getInstance().getSession().setEndRound(new Date());


            setContentView(R.layout.activity_result_2);
            this.finalScore = findViewById(R.id.final_result_cardview);
            this.result4 = findViewById(R.id.result4);
            this.AD8Button = findViewById(R.id.ad8_button);
            result.setResult4((int) result.CorlorValidation(result.getSelected3()));
            this.result4.setText(result.CorlorValidation(result.getSelected3())+"/"+result.getCorrect().size());

            this.result5 = findViewById(R.id.result5);
            this.result5.setText(result.ObjectCorlorLocationValidation(result.getSelected3())+"/"+result.getCorrect().size());

            this.replayButton = findViewById(R.id.replayButton);
            this.replayButton.setOnClickListener(this::handleReplayButton);

            this.total_result  = findViewById(R.id.result_total);
            this.result_1  = findViewById(R.id.result_1);
            this.result_2  = findViewById(R.id.result_2);

            GlobalObject.getInstance().getSession().setRound2Score((int) ((int) result.ObjectValidation (result.getSelected1())
                                            +result.SpatialValidation(result.getSelected2())
                                            +result.ObjectSpatialValidation(result.getSelected2())
                                            +result.CorlorValidation(result.getSelected3())
                                            + result.ObjectCorlorLocationValidation(result.getSelected3())));

            int score1  = GlobalObject.getInstance().getSession().getRound1Score();
            int score2  = GlobalObject.getInstance().getSession().getRound2Score();
            int score   = score1 + score2;
            this.result_1.setText(score1+"/"+9);
            this.result_2.setText(score2+"/"+15);
            this.total_result.setText(score+"/"+(24));
            controlAD8(score);
            GlobalObject.getInstance().getSession().setGameScore(score);
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
            Intent intent = new Intent(this,SelectObjectActivity.class);
            this.startActivity(intent);
        }
        else {
            GlobalObject.getInstance().setGameState(1);
        }

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
        @SuppressLint("ResourceAsColor")
        public void controlAD8(Integer score){
            if(score<18){
                finalScore.setBackgroundResource(R.color.red);
                this.AD8Button.setVisibility(View.VISIBLE);
                this.AD8Button.setOnClickListener(view -> handleAD8Button(view));
            }else{
                finalScore.setBackgroundResource(R.color.green);
                this.AD8Button.setVisibility(View.INVISIBLE);
                DB_Instance db_instance = new  DB_Instance(this);
                db_instance.insertData(GlobalObject.getInstance().getSession());
            }
        }
    public void handleAD8Button(View view) {
        Intent intent = new Intent(this,AD8WelComeActivity.class);
        this.startActivity(intent);
    }
}