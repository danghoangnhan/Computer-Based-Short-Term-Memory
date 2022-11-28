package com.example.memorygame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.R;

public class AD8RresultActivity extends AppCompatActivity {
    TextView ScoreView;
    TextView RecommendView;
    String text1 = "您可能有認知功能障礙，建議您進一步到醫院諮詢醫生以及追蹤";
    String text2 = "您目前沒有認知功能障礙，建議您保持良好生活習慣，繼續維持!";
    Integer result = GlobalObject.getInstance().getForm().caculated();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad8_rresult);
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
    }
}