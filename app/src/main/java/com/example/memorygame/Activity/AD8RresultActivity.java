package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.memorygame.Database.DB_Instance;
import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.Database.Operate.DB_Session_Operate;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.R;

import java.util.Date;

public class AD8RresultActivity extends AppCompatActivity {
    TextView ScoreView;
    TextView RecommendView;
    String text1 = "您可能有記憶力衰退的狀況，建議您進一步到醫院諮詢醫生以及追蹤。";
    String text2 = "您目前沒有記憶力衰退的狀況，建議您保持良好生活習慣，繼續維持！";
    Integer result = GlobalObject.getInstance().getForm().caculated();
    DB_Instance DB;
    Button exitButton;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad8_rresult);
        DB = new DB_Instance(this);
        this.ScoreView = findViewById(R.id.score);
        this.RecommendView= findViewById(R.id.recommend);
        this.ScoreView.setText("您的分數是"+result+"分");
        if (result<=1){
            this.RecommendView.setText(text2);
            this.ScoreView.setBackgroundResource(R.color.green);
        }
        else {
            this.RecommendView.setText(text1);
            this.ScoreView.setBackgroundResource(R.color.red);
        }
        Session session = GlobalObject.getInstance().getSession();
        session.setEndAD8Time(new Date());
        session.setAD8_Score(result);
        DB.insertData(session);
        exitButton = findViewById(R.id.escButton);
        exitButton.setOnClickListener(view -> {
            exit();
        });
    }
    public void exit(){
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }
}