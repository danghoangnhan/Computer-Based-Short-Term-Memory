package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.ButtonList;
import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Language;
import com.example.memorygame.Listener.DragListener.BoardDragListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
        for (ImageRecycleViewObject imageRecycleViewObject : this.selectedImage = new ArrayList<>(GlobalObject.getInstance().getSelectedImage())) {
            imageRecycleViewObject.setSelected(false);
        }
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
        if(this.selectedObject.size()>0){
            this.globalObject.getResult().setSelected2(this.selectedObject);
            Intent intent = new Intent(this,GlobalObject.getInstance().getGameState()==1?ResultActivity.class:PredictActivity3.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), Language.Chinese.get(Language.Key.Objectionable), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(View view, int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
        this.tmpView = view;
        this.globalObject.setTmpClickedImage(this.tmpClickedImage);
    }

    public void HandleSelected(@NonNull ImageRecycleViewObject target) {

        Integer filterIndex = IntStream.range(0,this.selectedImage
                        .size())
                .filter(i->this.selectedImage.get(i).equals(target))
                .findFirst()
                .orElseGet(null);

        target.setSelected(true);
        this.selectedImage.set(filterIndex,target);
        this.recyclerViewAdapter.notifyItemChanged(filterIndex);
    }
    public ArrayList<MatchingObject> generatingMatchingObject(Integer NumberPerrow) {

        AtomicReference<Integer> currentRow = new AtomicReference<>(0);
        AtomicReference<Integer> currentColumn = new AtomicReference<>(0);

        List<CorlorRecycleViewObject> corlorList = Collections.nCopies(ButtonList.getInstance().getButtonBoard().size(), new CorlorRecycleViewObject(false, R.color.white));
        Iterator<CorlorRecycleViewObject> finalCorlorList = corlorList.iterator();
        return  (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId -> {
                    CorlorRecycleViewObject  initCorlor = finalCorlorList.next();
                    MatchingObject currentObject = new MatchingObject();
                    ShapeableImageView button = setShape(elementId, initCorlor.getCorlorId(), initCorlor.getCorlorId());
                    currentObject.setCorlor(initCorlor);
                    button.setOnDragListener(new BoardDragListener(this, currentObject));
                    currentObject.setViewId(button.getId());
                    currentObject.setColumn(currentColumn.get());
                    currentObject.setRow(currentRow.get());
                    currentObject.setInitCorlor(initCorlor);

                    if (currentColumn.get() == NumberPerrow - 1) {
                        currentRow.getAndSet(currentRow.get() + 1);
                        currentColumn.set(0);
                    } else {
                        currentColumn.set(currentColumn.get() + 1);
                    }
                    return currentObject;
                }).collect(Collectors.toList());
    }
    @Override
    public void HandleUnSelected(@NonNull ImageRecycleViewObject target) {
        Integer filterIndex = IntStream.range(0,this.selectedImage
                        .size())
                .filter(i->this.selectedImage.get(i).getImageId()==target.getImageId())
                .findFirst()
                .orElseGet(null);

        target.setSelected(false);
        this.selectedImage.set(filterIndex,target);
        this.recyclerViewAdapter.notifyItemChanged(filterIndex);
    }

    public void HandleSelected(Integer viewId,ImageRecycleViewObject image, MatchingObject matchingObject) {
        ShapeableImageView targetView = findViewById(viewId);

        MatchingObject selectedObject = this.selectedObject.size() <= 0 ? null : this.selectedObject
                .stream()
                .filter(element -> element.getViewId().equals(viewId))
                .findAny()
                .orElse(null);

        if (selectedObject==null){
            selectedObject = objectList.stream().filter(element->element.getViewId().equals(viewId)).findFirst().get();
            HandleSelected(image);
            selectedObject.setImage(image);
            this.selectedObject.add(selectedObject);
        }
        else{
            HandleUnSelected(matchingObject.getImage());
            HandleSelected(image);
        }
        targetView.setImageResource(image.getImageId());
    }


    @Override
    public void HandleUnSelected(Integer viewId, ImageRecycleViewObject image, @NonNull MatchingObject matchingObject) {
        HandleUnSelected(image);
        ShapeableImageView shapeableImageView = findViewById(viewId);
        shapeableImageView.setStrokeColorResource(matchingObject.getInitCorlor().getCorlorId());
        shapeableImageView.setImageResource(matchingObject.getInitCorlor().getCorlorId());
        this.selectedObject.remove(matchingObject);
    }

    public ShapeableImageView setShape(Integer viewId,Integer Image,Integer corlor){
        ShapeableImageView button = findViewById(viewId);
        button.setImageResource(Image);
        button.setStrokeColorResource(corlor);
        return button;
    }
}