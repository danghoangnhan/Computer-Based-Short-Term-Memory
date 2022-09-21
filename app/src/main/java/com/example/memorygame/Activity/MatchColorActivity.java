package com.example.memorygame.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import java.util.stream.IntStream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MatchColorActivity extends AppCompatActivity implements
        HandleStageButton,
        RecycleViewInterface,
        CorlorListInterface {

    private ArrayList<Integer> selectedImage;
    private List<Integer>  colorList;
    private ArrayList<MatchingObject> objectList;
    private RecyclerView recyclerView,corlorRecycleView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private CorlorListAdapter corlorListAdapter;
    private Button nextButton,escButton,replayButton;
    private Integer tmpClickedImage,tmpClickedColor;
    private ArrayList<MatchingObject> selectedButtonList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        initialButton();
        Intent receiverIntent = getIntent();
        this.selectedImage = receiverIntent.getIntegerArrayListExtra("selectedImages");
        this.objectList = this.generatingMatchingObject(3);
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
            currentObject.setViewId(button.getId());
            currentObject.setColumn(currentColumn.get());
            currentObject.setRow(currentRow.get());
            if (currentColumn.get() ==NumberPerrow-1){
                currentRow.getAndSet(currentRow.get() + 1);
                currentColumn.set(0);
            }
            else {
                currentColumn.set(currentColumn.get()+1);
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
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onItemClick(View view,int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
    }
    @Override
    public void onCorlorItemClick(View view,int Position) {
        this.tmpClickedColor = this.colorList.get(Position);
    }
    public void onBoardClick(View imageButton){
        if (this.tmpClickedImage!=null){
            ShapeableImageView targetView = findViewById(imageButton.getId());
            targetView.setImageResource(this.tmpClickedImage);
            MatchingObject object = this.selectedButtonList.stream()
                    .filter(matchingObject -> matchingObject.getViewId()==imageButton.getId())
                    .findAny()
                    .orElseGet(()->{
                        MatchingObject newMatch = this.objectList.stream().filter(element->element.getViewId()==imageButton.getId()).findFirst().get();
                        this.selectedButtonList.add(newMatch);
                        return newMatch;
                    });
            object.setImage(this.tmpClickedImage);
            Integer selted = this.selectedImage
                    .stream()
                    .filter(elemnt->elemnt==this.tmpClickedColor)
                    .findFirst().orElse(null);

            Integer seletedInteger = IntStream.range(0,this.selectedImage.size())
                    .filter(i->this.selectedImage.get(i)==this.tmpClickedImage)
                    .findFirst()
                    .orElse(-1);
            removeAt(seletedInteger);
            this.tmpClickedImage =null;
        }
        if (this.tmpClickedColor!=null){
           setStrokeCorlor(imageButton,this.tmpClickedColor);
            MatchingObject object = this.selectedButtonList.stream()
                    .filter(matchingObject -> matchingObject.getViewId()==imageButton.getId())
                    .findAny()
                    .orElseGet(()->{
                        MatchingObject newMatch = this.objectList.stream().filter(element->element.getViewId()==imageButton.getId()).findFirst().get();
                        this.selectedButtonList.add(newMatch);
                        return newMatch;
                    });
            object.setColor(this.tmpClickedColor);
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
        targetView.setStrokeColorResource(colorId);
    }

    public void removeAt(int position) {
        this.selectedImage.remove(position);
        recyclerViewAdapter.notifyItemRemoved(position);
        recyclerViewAdapter.notifyItemRangeChanged(position,this.selectedImage.size());
    }
}