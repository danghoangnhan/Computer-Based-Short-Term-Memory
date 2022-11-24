package com.example.memorygame.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.memorygame.Object.AD8Object.AD8Form;
import com.example.memorygame.Object.AD8Object.AD8_Question;
import com.example.memorygame.R;

public class AD8Activity extends AppCompatActivity {

    Integer currentQuestionNumber;
    Button nextQuestion,previousQuestion,completed;
    AD8Form questionForm;
    TextView question_section;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad8);

        previousQuestion = findViewById(R.id.previous_question_button);
        nextQuestion = findViewById(R.id.next_question_button);
        completed = findViewById(R.id.complete_button);
        question_section = findViewById(R.id.question_area);

        currentQuestionNumber = 0;
        questionForm  = new AD8Form();
        controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));


        previousQuestion.setOnClickListener(v -> {
            currentQuestionNumber--;
            controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
        });
    }
    public void controlVisual(@NonNull AD8_Question question){
        if (question.getQuestionNumber().equals(0)){
            previousQuestion.setVisibility(View.INVISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            completed.setVisibility(View.INVISIBLE);
        }
        else if (question.getQuestionNumber()==questionForm.getQuestionList().size()-1){
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.INVISIBLE);
            completed.setVisibility(View.VISIBLE);
        }
        else{
            nextQuestion.setVisibility(View.VISIBLE);
            completed.setVisibility(View.VISIBLE);
            completed.setVisibility(View.INVISIBLE);
        }
        question_section.setText(question.getQuestion());
    }
    public void setNextActivate(){
        nextQuestion.setOnClickListener(v -> {
            currentQuestionNumber++;
            controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
        });
    }
    public void setPrevInActivate(){
        nextQuestion.setOnClickListener(null);
    }
    public void setPrevActivate(){
        nextQuestion.setOnClickListener(v -> {
            currentQuestionNumber--;
            controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
        });
    }
    public void setNextInActivate(){
        nextQuestion.setOnClickListener(null);
    }

    public void setCompletedActivate(){
        completed.setOnClickListener(v -> {
        });
    }
    public void setCompletedInActivate(){
        completed.setOnClickListener(null);
    }
}