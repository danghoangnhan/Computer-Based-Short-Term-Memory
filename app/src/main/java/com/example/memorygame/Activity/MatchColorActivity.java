package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.ButtonList;
import com.example.memorygame.CallBack.BoardButtonCallBack;
import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.CallBack.ImageRecycleVIewCallBack;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Listener.DragListener.BoardDragListener;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MatchColorActivity extends AppCompatActivity implements
        HandleStageButton,
        RecycleViewInterface,
        BoardButtonCallBack,
        ButtonImageCall {

    private ArrayList<ImageRecycleViewObject> selectedImage;
    private ArrayList<MatchingObject> objectList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button nextButton,escButton,replayButton;
    private ImageRecycleViewObject tmpClickedImage;
    private GlobalObject globalObject;
    private ArrayList<MatchingObject> selectedButtonList;
    private View tmpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        initialButton();
        this.selectedImage = getIntent().getParcelableArrayListExtra("selectedImages");
        initialRecyleView();
        this.globalObject = GlobalObject.getInstance();
        this.selectedButtonList = new ArrayList<>();
        this.objectList = this.generatingMatchingObject(3);
        this.globalObject.setObjectList(this.objectList);
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
        Iterator<Integer> corlorListIterator =  ButtonList.getInstance().getSuffleCorlorList().iterator();
        ArrayList<MatchingObject> matchingObjects = (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId->{
            MatchingObject currentObject = new MatchingObject();
            Integer nextCorlor = corlorListIterator.next();
            ShapeableImageView button = findViewById(elementId);
            button.setImageResource(nextCorlor);
            button.setStrokeColorResource(nextCorlor);
            currentObject.setColor(nextCorlor);
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
        this.globalObject.getResult().setCorrect(this.selectedButtonList);
        args.putParcelableArrayList("ARRAYLIST", this.selectedButtonList);
        intent.putExtras(args);
        this.startActivity(intent);
    }

    @Override
    public void handleReplayButton(View view) {this.startActivity(new Intent(this,MatchColorActivity.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}

    @Override
    public void onItemClick(View view,int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
        this.tmpView = view;
        this.globalObject.setTmpClickedImage(this.tmpClickedImage);
    }
    @Override
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
        this.selectedButtonList.add(matchingObject);
    }

    @Override
    public void HandleUnSelected(ImageRecycleViewObject image, MatchingObject matchingObject) {
        HandleUnSelected(image);

    }
}
