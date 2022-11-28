package com.example.memorygame.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.Object.AD8Object.AD8Form;
import com.example.memorygame.Object.AD8Object.AD8_Answer;
import com.example.memorygame.Object.AD8Object.AD8_Question;
import com.example.memorygame.R;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AD8Activity extends AppCompatActivity {
    private List<String> questionTile = Arrays.asList("第一題:","第二題:","第三題:","第四題:","第五題:","第六題:","第七題:","第八題:");
    Integer currentQuestionNumber;
    Button nextQuestion,previousQuestion,completed;
    AD8Form questionForm;
    TextView question_section,question_id;
    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad8);

        previousQuestion = findViewById(R.id.previous_question_button);
        nextQuestion = findViewById(R.id.next_question_button);
        completed = findViewById(R.id.complete_button);
        question_section = findViewById(R.id.question_area);
        mRadioGroup = findViewById(R.id.radiogroup);
        question_id = findViewById(R.id.question_id);
        currentQuestionNumber = 0;
        questionForm  = new AD8Form();
        controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));


        previousQuestion.setOnClickListener(v -> {
            currentQuestionNumber--;
            controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
        });
        GlobalObject.getInstance().getSession().setStartAD8Time(new Date());
    }
    public void controlVisual(@NonNull AD8_Question question){
        if (question.getQuestionNumber().equals(0)){
            previousQuestion.setVisibility(View.INVISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            completed.setVisibility(View.INVISIBLE);
            setNextActivate();
            setPrevInActivate();
            setCompletedInActivate();
        }
        else if (question.getQuestionNumber().equals(questionForm.getQuestionList().size()-1)){
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.INVISIBLE);
            completed.setVisibility(View.VISIBLE);
            setPrevActivate();
            setNextInActivate();
            setCompletedActivate();
        }
        else{
            nextQuestion.setVisibility(View.VISIBLE);
            previousQuestion.setVisibility(View.VISIBLE);
            completed.setVisibility(View.INVISIBLE);
            setPrevActivate();
            setNextActivate();
            setCompletedInActivate();
        }
        question_section.setText(question.getQuestion());
        question_id.setText(this.questionTile.get(currentQuestionNumber));
        createRadioButton(question);
    }
    public void setNextActivate(){
        nextQuestion.setOnClickListener(v -> {
                if (questionForm.getQuestionList().get(currentQuestionNumber).getCurrentAnswer()!=null){
                    currentQuestionNumber++;
                    controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
                }else{
                    Toast.makeText(AD8Activity.this, "向未選擇答案", Toast.LENGTH_SHORT).show();
                }
        });
    }
    public void setPrevInActivate(){
        this.previousQuestion.setOnClickListener(null);
    }
    public void setPrevActivate(){
        this.previousQuestion.setOnClickListener(v -> {
            if (questionForm.getQuestionList().get(currentQuestionNumber).getCurrentAnswer()!=null){
                currentQuestionNumber--;
                controlVisual(questionForm.getQuestionList().get(currentQuestionNumber));
            }else{
                Toast.makeText(AD8Activity.this, "向未選擇答案", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setNextInActivate(){
        nextQuestion.setOnClickListener(null);
    }

    public void setCompletedActivate(){
        completed.setOnClickListener(v -> {
            GlobalObject.getInstance().setForm(this.questionForm);
            Intent intent = new Intent(this,AD8RresultActivity.class);
            this.startActivity(intent);
        });
    }
    public void setCompletedInActivate(){
        completed.setOnClickListener(null);
    }
    private void createRadioButton(@NonNull AD8_Question question) {
        mRadioGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        mRadioGroup.removeAllViews();
        for (int i =0;i<question.getAnswerList().size();i++){
            AD8_Answer answer = question.getAnswerList().get(i);
            RadioButton radioButton  = new RadioButton(this);
            radioButton.setText(answer.getAnswerDisplay());
            radioButton.setChecked(answer.equals(question.getCurrentAnswer()));
            radioButton.setOnClickListener(v -> onClickRadioOption(answer));
            radioButton.setTextSize(25);
            mRadioGroup.addView(radioButton);
        }
    }
    public void onClickRadioOption(AD8_Answer answer) {
        this.questionForm.getQuestionList().get(currentQuestionNumber).setCurrentAnswer(answer);
    }
}