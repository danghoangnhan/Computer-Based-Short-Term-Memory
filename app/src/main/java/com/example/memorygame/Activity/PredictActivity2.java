package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.ButtonList;
import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Listener.DragListener.BoardDragListener;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredictActivity2 extends AppCompatActivity implements
        HandleStageButton,
        RecycleViewInterface,
        ButtonImageCall {

    private ArrayList<ImageRecycleViewObject> selectedImage;
    private Button nextButton,escButton,replayButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ImageRecycleViewObject tmpClickedImage;
    private View tmpView;
    private GlobalObject globalObject;
    private ArrayList<MatchingObject> objectList;
    private ArrayList<MatchingObject> selectedObject;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict2);
        this.selectedImage = getIntent().getParcelableArrayListExtra("selectedImages");
        this.globalObject = GlobalObject.getInstance();
        this.objectList = this.generatingMatchingObject(3);
        this.globalObject.setObjectList(this.objectList);
        initialButton();
        initialRecyleView();
        this.selectedObject = new ArrayList<>();
    }
    public void initialButton(){
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);
    }
    public  void initialRecyleView(){
        this.recyclerView = findViewById(R.id.recycleview);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this,selectedImage,this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(PredictActivity2.this,LinearLayoutManager.HORIZONTAL,false));
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
    }
    @Override
    public void handleReplayButton(View view) {this.startActivity(new Intent(this,PredictActivity2.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}
    @Override
    public void handleNextButton(View view) {
        Intent intent = new Intent(this,PredictActivity3.class);
        intent.putParcelableArrayListExtra("selectedImages", (ArrayList<MatchingObject>) this.selectedObject);
        this.globalObject.getResult().setSelected2(this.selectedObject);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
        this.tmpView = view;
        this.globalObject.setTmpClickedImage(this.tmpClickedImage);
    }

    public void handleImageRecycleView(Integer ViewID) {
        ImageView view = findViewById(ViewID);
        view.setImageResource(R.drawable.delete);
        view.setOnDragListener(null);
        view.setOnClickListener(null);
    }

    public void HandleSelected(ImageRecycleViewObject target) {

        Integer filterIndex = IntStream.range(0,this.selectedImage
                        .size())
                .filter(i->this.selectedImage.get(i).getImageId()==target.getImageId())
                .findFirst()
                .orElseGet(null);

        target.setSelected(true);
        this.selectedImage.set(filterIndex,target);
        this.recyclerViewAdapter.notifyItemChanged(filterIndex);
    }
    public ArrayList<MatchingObject> generatingMatchingObject(Integer NumberPerrow){
        AtomicReference<Integer> currentRow = new AtomicReference<>(0);
        AtomicReference<Integer> currentColumn= new AtomicReference<>(0);
        ArrayList<MatchingObject> matchingObjects = (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId->{
            MatchingObject currentObject = new MatchingObject();
            ShapeableImageView button = findViewById(elementId);
            button.setImageResource(R.color.white);
            currentObject.setColor(R.color.white);
            button.setOnDragListener( new BoardDragListener(this));
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

    public void HandleUnSelected(ImageRecycleViewObject target) {
        Integer filterIndex = IntStream.range(0,this.selectedImage
                        .size())
                .filter(i->this.selectedImage.get(i).getImageId()==target.getImageId())
                .findFirst()
                .orElseGet(null);

        target.setSelected(false);
        this.selectedImage.set(filterIndex,target);
        this.recyclerViewAdapter.notifyItemChanged(filterIndex);
    }

    @Override
    public void HandleSelected(ImageRecycleViewObject image, MatchingObject matchingObject) {
        HandleSelected(image);
        this.selectedObject.add(matchingObject);
    }

    @Override
    public void HandleUnSelected(ImageRecycleViewObject image, MatchingObject matchingObject) {
        HandleUnSelected(image);
    }
}