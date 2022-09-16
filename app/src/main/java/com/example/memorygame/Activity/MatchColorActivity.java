package com.example.memorygame.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.BoardButtonViewAdapter;
import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.CallBack.ItemMoveCallback;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.Customer;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.BoardButtonInterface;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.example.memorygame.listener.OnCustomerListChangedListener;
import com.example.memorygame.listener.OnListChangedListener;
import com.example.memorygame.listener.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MatchColorActivity extends AppCompatActivity implements HandleStageButton, RecycleViewInterface {

    private ArrayList<Integer> selectedImage;
    private List<Integer>  colorList;
    private List<MatchingObject> objectList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private BoardButtonViewAdapter boardButtonViewAdapter;
    private Button nextButton,escButton,replayButton;
    private List<ImageButton>buttons;
    private Integer tmpClickedImage,tmpClickedColor;
    private Drawable myIcon ;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        initialButton();
        Intent receiverIntent = getIntent();
        this.selectedImage = receiverIntent.getIntegerArrayListExtra("selectedImages");
        this.colorList = randomColor(9);
        this.generatingMatchingObject(this.buttons,9);
        initialRecyleView();
    }
    public  void initialRecyleView(){
        this.recyclerView = findViewById(R.id.recycleview);
        this.linearLayoutManager = new LinearLayoutManager(MatchColorActivity.this,LinearLayoutManager.HORIZONTAL,false);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this,selectedImage,this);
//        this.boardButtonViewAdapter = new BoardButtonViewAdapter(this,this.objectList,);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<MatchingObject> generatingMatchingObject(List<ImageButton> buttonList, Integer NumberPerrow){
        List<MatchingObject> matchingObjects = new ArrayList<>();
        buttonList.forEach(imageButton -> imageButton.setOnClickListener(view -> onBoardClick(view)));
        for (int i=0;i<buttonList.size()/NumberPerrow;i++){
            for (int j =0;j<NumberPerrow;j++){
                MatchingObject currentObject = new MatchingObject();
                currentObject.setColor(randomColor());
                currentObject.setColumn(j);
                currentObject.setRow(i);
                currentObject.setImageButton(buttonList.get(i));
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
    public View.OnLongClickListener longClickListener = view -> {
        ClipData data = ClipData.newPlainText("","");
        View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data,myShadowBuilder,view,0);
        return false;
    };

    public void initialButton(){
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.buttons = Arrays.asList(findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9));
        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);
    }
    @Override
    public void handleNextButton(View view){
        Intent intent = new Intent(this,WaitingActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {

    }

    @Override
    public void handleEscButton(View view) {

    }

    @Override
    public void onItemClick(View view,int Position) {
        Integer selected = this.selectedImage.get(Position);
        this.tmpClickedImage = selected;
        myIcon = getResources().getDrawable(this.tmpClickedImage);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBoardClick(View imageButton){
        if (this.tmpClickedImage!=null){
            imageButton.setForeground(myIcon);
        }
        if (this.tmpClickedColor!=null){
            imageButton.setBackgroundColor(this.tmpClickedColor);
        }
    }
}