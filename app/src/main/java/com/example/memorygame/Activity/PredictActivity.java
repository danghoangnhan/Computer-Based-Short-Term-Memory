package com.example.memorygame.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.ButtonList;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PredictActivity extends AppCompatActivity implements HandleStageButton {

    private Button nextButton,escButton,replayButton;
    private List<MatchingObject> matchingObjectList,initialList,selectedList;
    private List<ImageButton> imageButtonList;
    private ArrayList<MatchingObject> matchingObjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        nextButton = findViewById(R.id.nextButton);
        escButton = findViewById(R.id.escButton);
        replayButton = findViewById(R.id.replayButton);

        nextButton.setOnClickListener(this::handleNextButton);
        escButton.setOnClickListener(this::handleEscButton);
        replayButton.setOnClickListener(this::handleReplayButton);
        initBoard();
    }
    @Override
    public void handleNextButton(View view){
        Intent intent = new Intent(this,ResultActivity.class);
        Bundle args = new Bundle();
        Result result = new Result(this.matchingObjectArrayList,this.matchingObjectArrayList);
        args.putSerializable("ObjectValidation", (Serializable) result);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this,PredictActivity.class);
        startActivity(intent);
    }
    @Override
    public void handleEscButton(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public  void  initBoard(){
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.matchingObjectList = (ArrayList<MatchingObject>) args.getSerializable("ARRAYLIST");
        AtomicReference<Integer> currentRow= new AtomicReference<>(0);
        AtomicReference<Integer> currentColum = new AtomicReference<>(0);
        this.initialList = IntStream.range(0,imageButtonList.size()).mapToObj(element->{
            MatchingObject currentMatchingObject = new MatchingObject();
            Integer randomColorCode = ButtonList.getInstance().getRandomDrawable();
            ShapeableImageView currentImageButton = findViewById(randomColorCode);
            currentMatchingObject.setImageButton(currentImageButton);
            currentMatchingObject.setColor(randomColorCode);
            if (currentColum.get() ==3){
                currentColum.set(0);
                currentRow.getAndSet(currentRow.get() + 1);
            }
            return currentMatchingObject;
        }).collect(Collectors.toList());

        this.matchingObjectList.forEach(matchingObject -> this.initialList
                .stream()
                .filter(element->element.sameColum(matchingObject))
                .filter(element-> element.sameRow(matchingObject))
                .forEach(filteredElement->{
                    Drawable corlor =   matchingObject.getImageButton().getBackground();
                    Drawable image =    matchingObject.getImageButton().getForeground();
                    filteredElement.getImageButton().setBackground(corlor);
                    filteredElement.getImageButton().setForeground(image);
                }));
    }
}