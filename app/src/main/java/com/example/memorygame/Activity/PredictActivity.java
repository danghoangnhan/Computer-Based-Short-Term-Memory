package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.ButtonList;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PredictActivity extends AppCompatActivity implements HandleStageButton {

    private Button nextButton,escButton,replayButton;
    private List<MatchingObject> matchingObjectList,initialList,selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        this.selectedList = new ArrayList<>();
       initialButton();
        initBoard();
        MatchingExpectObject();
    }

    public void initialButton(){
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);

        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);
    }

    @Override
    public void handleNextButton(View view){
        Intent intent = new Intent(this,ResultActivity.class);
        Bundle args = new Bundle();
        Result result = new Result(this.matchingObjectList,this.selectedList);
        args.putParcelable("result", result);
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

        this.initialList = ButtonList.getInstance().getButtonBoard().stream().map(element->{
            MatchingObject currentMatchingObject = new MatchingObject();
            Integer randomColorCode = ButtonList.getInstance().randomColorResource();
            Integer randomImage = ButtonList.getInstance().getRandomImageResource();
            ShapeableImageView currentImageButton = findViewById(element);
            currentImageButton.setStrokeColorResource(randomColorCode);
            currentImageButton.setImageResource(randomImage);
            currentImageButton.setOnClickListener(v -> onClickSelected(v));
            currentMatchingObject.setViewId(currentImageButton.getId());
            currentMatchingObject.setColor(randomColorCode);
            currentMatchingObject.setColumn(currentColum.get());
            currentMatchingObject.setRow(currentRow.get());
            if (currentColum.get() ==2){
                currentRow.getAndSet(currentRow.get() + 1);
                currentColum.set(0);
            }
            else {
                currentColum.set(currentColum.get()+1);
            }
            return currentMatchingObject;
        }).collect(Collectors.toList());
    }

    public  void MatchingExpectObject(){
        this.matchingObjectList.stream().forEach(element->{
            MatchingObject filterObject = this.initialList.stream()
                    .filter(initElement->initElement.sameColum(element))
                    .filter(initElement->initElement.sameRow(element))
                    .findFirst()
                    .orElse(null);
            ShapeableImageView currentFIlredObject =  findViewById(filterObject.getViewId());
            currentFIlredObject.setImageResource(element.getImage());
            currentFIlredObject.setStrokeColorResource(element.getColor());
            filterObject.clone(element);
        });
    }
    public void onClickSelected(View view){
               MatchingObject filterdObject = this.initialList
                       .stream()
                       .filter(element->element.getViewId()==view.getId())
                       .findFirst()
                       .orElse(null);
               this.selectedList.add(filterdObject);
    }
}