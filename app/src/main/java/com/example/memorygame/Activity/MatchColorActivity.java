package com.example.memorygame.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.CorlorListAdapter;
import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.ButtonList;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MatchColorActivity extends AppCompatActivity implements
        HandleStageButton,
        RecycleViewInterface,
        CorlorListInterface {

    private ArrayList<Integer> selectedImage;
    private List<Integer>  colorList;
    private ArrayList<MatchingObject> objectList;
    private RecyclerView recyclerView,corlorRecycleView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private CorlorListAdapter corlorListAdapter;
    private Button nextButton,escButton,replayButton;
    private List<ImageButton>buttons;
    private Integer tmpClickedImage,tmpClickedColor;
    private Drawable myIcon,corlor ;
    private ArrayList<MatchingObject> selectedButtonList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        initialButton();
        Intent receiverIntent = getIntent();
        this.selectedImage = receiverIntent.getIntegerArrayListExtra("selectedImages");
        this.objectList = this.generatingMatchingObject(9);
        this.selectedButtonList = new ArrayList<>();
        initialRecyleView();
        initCorlorViews();
    }
    public  void initialRecyleView(){
        this.recyclerView = findViewById(R.id.recycleview);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this,selectedImage,this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(MatchColorActivity.this,LinearLayoutManager.HORIZONTAL,false));
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
    }

    public ArrayList<MatchingObject> generatingMatchingObject(Integer NumberPerrow){
        AtomicReference<Integer> currentRow = new AtomicReference<>(0);
        AtomicReference<Integer> currentColumn= new AtomicReference<>(0);
        ArrayList<MatchingObject> matchingObjects = (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId->{
            MatchingObject currentObject = new MatchingObject();
            Drawable defaultColor = getDrawable(R.color.black);
            ShapeableImageView button = findViewById(elementId);
            button.setBackground(defaultColor);
            currentObject.setColor(R.color.black);
            button.setOnClickListener(this::onBoardClick);
            currentObject.setImageButton(button);
            currentObject.setColumn(currentColumn.get());
            currentObject.setRow(currentRow.get());
            if (currentColumn.get() ==NumberPerrow){
                currentRow.getAndSet(currentRow.get() + 1);
                currentColumn.set(0);
            }
            return currentObject;
        }).collect(Collectors.toList());
        return  matchingObjects;
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
        Intent intent = new Intent(this,WaitingActivity.class);
        Bundle args = new Bundle();
        args.putParcelableArrayList("ARRAYLIST", this.selectedButtonList);
        intent.putExtras(args);
        this.startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {
        Intent intent = new Intent(this,MatchColorActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleEscButton(View view) {

    }

    @Override
    public void onItemClick(View view,int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
        myIcon = getResources().getDrawable(this.tmpClickedImage);
    }
    @Override
    public void onCorlorItemClick(View view,int Position) {
        this.tmpClickedColor = this.colorList.get(Position);
        corlor = getResources().getDrawable(this.tmpClickedColor);
    }
    public void onBoardClick(View imageButton){
        if (this.tmpClickedImage!=null){
            imageButton.setBackgroundResource(R.drawable.camel);
            List<MatchingObject> currentImageButton = this.objectList
                    .stream()
                    .filter(selectedImage->selectedImage.getImageButton().getForeground()==myIcon)
                    .collect(Collectors.toList());
            this.selectedButtonList.addAll(currentImageButton);
            this.tmpClickedImage =null;
        }
        if (this.tmpClickedColor!=null){
           setStrokeCorlor(imageButton,this.tmpClickedColor);
            List<MatchingObject> currentImageButton = this.objectList
                    .stream()
                    .filter(selectedImage->selectedImage.getImageButton().getForeground()==corlor)
                    .collect(Collectors.toList());
            this.selectedButtonList.addAll(currentImageButton);
            this.tmpClickedColor =null;
        }
    }
    private  void initCorlorViews(){
        this.colorList = ButtonList.getInstance().getCorlorCodeList();
        this.corlorRecycleView= findViewById(R.id.button_recycleview);
        this.corlorListAdapter= new CorlorListAdapter(this,this.colorList,this);
        this.corlorRecycleView.setLayoutManager(new LinearLayoutManager(MatchColorActivity.this,LinearLayoutManager.VERTICAL,false));
        this.corlorRecycleView.setAdapter(this.corlorListAdapter);
    }
    @SuppressLint("ResourceAsColor")
    public void setStrokeCorlor(View view, Integer colorId){
        ShapeableImageView targetView = findViewById(view.getId());
        targetView.setStrokeColorResource(R.color.red);
    }
}