package com.example.memorygame.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchColorActivity extends AppCompatActivity {
    ArrayList<Integer> selectedImage;
    List<Integer>  colorList;
    List<MatchingObject> objectList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    Button nextButton,escButton,replayButton;
    List<Button>buttons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        Intent receiverIntent = getIntent();
        this.selectedImage = receiverIntent.getIntegerArrayListExtra("selectedImages");
        this.colorList = randomColor(9);
        this.linearLayoutManager = new LinearLayoutManager(MatchColorActivity.this,LinearLayoutManager.HORIZONTAL,false);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this,selectedImage);
        this.recyclerView = findViewById(R.id.recycleview);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
        this.generatingMatchingObject(this.buttons,9);
    }

    public List<MatchingObject> generatingMatchingObject(List<Button> buttonList, Integer NumberPerrow){
        List<MatchingObject> matchingObjects = new ArrayList<>();
        for (int i=0;i<buttonList.size()/NumberPerrow;i++){
            for (int j =0;j<NumberPerrow;j++){
                MatchingObject currentObject = new MatchingObject();
                currentObject.setColor(randomColor());
                currentObject.setColumn(j);
                currentObject.setRow(i);
                matchingObjects.add(currentObject);
            }
        }
        return  matchingObjects;
    }
    public List<Integer> randomColor(Integer listSize){
        List<Integer> colorList = new ArrayList<>();
        for(int i =0;i<listSize;i++){colorList.add(randomColor());}
        return colorList;
    }
    public Integer randomColor(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}