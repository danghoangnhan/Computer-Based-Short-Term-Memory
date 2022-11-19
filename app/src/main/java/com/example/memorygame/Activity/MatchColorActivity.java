package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatchColorActivity extends AppCompatActivity implements
        HandleStageButton,
        RecycleViewInterface,
        BoardButtonCallBack,
        ButtonImageCall ,
        ImageRecycleVIewCallBack {

    private ArrayList<ImageRecycleViewObject> selectedImage;
    private ArrayList<MatchingObject> objectList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button nextButton,escButton,replayButton;
    private ImageRecycleViewObject tmpClickedImage;
    private GlobalObject globalObject;
    private ArrayList<MatchingObject> selectedButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(GlobalObject.getInstance().getGameState()==1?R.layout.activity_match_color_1:R.layout.activity_match_color_2);
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
        return (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId->{
            MatchingObject currentObject = new MatchingObject();
            int nextCorlor = GlobalObject.getInstance().getGameState()==1?R.color.white:corlorListIterator.next();
            ShapeableImageView button = findViewById(elementId);
            button.setImageResource(nextCorlor);
            button.setStrokeColorResource(nextCorlor);
            currentObject.setColor(nextCorlor);
            currentObject.setInitCorlor(nextCorlor);
            button.setOnDragListener( new BoardDragListener(this,currentObject));
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
        if(this.selectedImage.stream().filter(ImageRecycleViewObject::isSelected).count()>0){
            this.globalObject.getResult().setCorrect(this.selectedButtonList);

            Intent intent = new Intent(this,WaitingActivity.class);
            this.startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"尚未配對顏色", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleReplayButton(View view) {this.startActivity(new Intent(this,MatchColorActivity.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}

    @Override
    public void onItemClick(View view,int Position) {
        this.tmpClickedImage = this.selectedImage.get(Position);
        this.globalObject.setTmpClickedImage(this.tmpClickedImage);
    }

    public void handleImageRecycleView(Integer ViewID) {
        ImageView view = findViewById(ViewID);
        view.setImageResource(R.drawable.delete);
        view.setOnDragListener(null);
        view.setOnClickListener(null);
    }

    @Override
    public void HandleSelected(ImageRecycleViewObject target) {
        Integer filterIndex = getFilterIndex(target);
        if (filterIndex!=null){
            target.setSelected(true);
            this.recyclerViewAdapter.notifyItemChanged(filterIndex);
        }
    }
public Integer getFilterIndex(ImageRecycleViewObject target){
    return IntStream.range(0,this.selectedImage.size())
            .filter(i->this.selectedImage.get(i).equals(target))
            .findAny().orElse(-1);
}
    @Override
    public void HandleUnSelected(ImageRecycleViewObject target) {
        Integer filterIndex = getFilterIndex(target);
        if (filterIndex!=null){
            target.setSelected(false);
            this.recyclerViewAdapter.notifyItemChanged(filterIndex);
        }
    }

    @Override
    public void HandleSelected(Integer viewId,ImageRecycleViewObject image, MatchingObject matchingObject) {
        ShapeableImageView targetView = findViewById(viewId);

        MatchingObject selectedObject = this.selectedButtonList.size() <= 0 ? null : this.selectedButtonList
                .stream()
                .filter(element -> element.getViewId().equals(viewId))
                .findAny()
                .orElse(null);

        if (selectedObject==null){
            selectedObject = objectList.stream().filter(element->element.getViewId().equals(viewId)).findFirst().get();
            HandleSelected(image);
            selectedObject.setImage(image);
            this.selectedButtonList.add(selectedObject);
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
        shapeableImageView.setImageResource(matchingObject.getColor());
        this.selectedButtonList.remove(matchingObject);
    }

    @Override
    public void HandleUpdated(Integer viewId, ImageRecycleViewObject image, MatchingObject matchingObject) {

    }

}