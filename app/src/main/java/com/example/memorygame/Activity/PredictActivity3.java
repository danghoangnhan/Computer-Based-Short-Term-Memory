package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.CorlorListAdapter;
import com.example.memorygame.ButtonList;
import com.example.memorygame.CallBack.CorlorRecycleViewCallBack;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Language;
import com.example.memorygame.Listener.DragListener.BoardDragListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredictActivity3 extends AppCompatActivity implements HandleStageButton, CorlorListInterface,CorlorRecycleViewCallBack {
    private List<CorlorRecycleViewObject> corlorList;
    private CorlorRecycleViewObject tmpCorlor;
    private List<MatchingObject> objectList;
    private Button nextButton,escButton,replayButton;
    private RecyclerView recyclerView;
    private CorlorListAdapter corlorListAdapter;
    private View tmpView;
    private GlobalObject globalObject;
    private ArrayList<MatchingObject> selectedButtonList;
    private TextView userGuildText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict3);
        this.globalObject = GlobalObject.getInstance();
        this.selectedButtonList = new ArrayList<>(this.globalObject.getResult().getSelected2());
        this.objectList = this.generatingMatchingObject(3);
        this.globalObject.setObjectList((ArrayList<MatchingObject>) this.objectList);
        this.userGuildText = findViewById(R.id.userGuideText);
        this.userGuildText.setText(Language.Chinese.get(Language.Key.PleaseSelectCorlor));

        initialButton();
        initialRecyleView();

        this.selectedButtonList.forEach(element->{
            MatchingObject filter = this.objectList.stream()
                    .filter(currentElement-> Objects.equals(currentElement.getRow(), element.getRow()))
                    .filter(currentElement-> Objects.equals(currentElement.getColumn(), element.getColumn()))
                    .findFirst().orElse(null);

            assert filter != null;
            ShapeableImageView filterImage = findViewById(filter.getViewId());
            filterImage.setImageResource(element.getImage());
            filter.setInitCorlor(R.color.white);
            filterImage.setStrokeColorResource(R.color.white);

        });
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
        this.recyclerView = findViewById(R.id.color_recycle);
        this.corlorList = ButtonList.getInstance().getSuffleCorlorList().stream().map(element->{
            CorlorRecycleViewObject newObject = new CorlorRecycleViewObject();
            newObject.setSelected(false);
            newObject.setCorlorId(element);
            return newObject;
        }).collect(Collectors.toList());
        this.corlorListAdapter = new CorlorListAdapter(this,this.corlorList,this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(PredictActivity3.this,LinearLayoutManager.VERTICAL,false));
        this.recyclerView.setAdapter(this.corlorListAdapter);
    }
    public ArrayList<MatchingObject> generatingMatchingObject(Integer NumberPerrow){
        AtomicReference<Integer> currentRow = new AtomicReference<>(0);
        AtomicReference<Integer> currentColumn= new AtomicReference<>(0);
        return (ArrayList<MatchingObject>) ButtonList.getInstance().getButtonBoard().stream().map(elementId->{
            MatchingObject currentObject = new MatchingObject();
            ShapeableImageView button = findViewById(elementId);
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
    }
    @Override
    public void handleReplayButton(View view) {this.startActivity(new Intent(this,PredictActivity1.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}
    @Override
    public void handleNextButton(View view) {
        if(this.selectedButtonList.stream().anyMatch(element -> !Objects.equals(element.getInitCorlor(), element.getColor()))){
            this.startActivity(new Intent(this,ResultActivity.class));
            this.globalObject.getResult().setSelected3(this.objectList);
        }else{
            Toast.makeText(getApplicationContext(),"尚未配對顏色", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onCorlorItemClick(View view, int Position) {
        this.tmpCorlor = this.corlorList.get(Position);
        this.tmpView = view;
        this.globalObject.setTmpCorlorObject(this.tmpCorlor);
    }

    @Override
    public void HandleSelected(@NonNull CorlorRecycleViewObject target) {
        int filterIndex = IntStream.range(0,this.corlorList.size())
                .filter(i->this.corlorList.get(i).getCorlorId()==target.getCorlorId())
                .findFirst()
                .orElseGet(null);

        target.setSelected(true);
        this.corlorList.set(filterIndex,target);
        this.corlorListAdapter.notifyItemChanged(filterIndex);
    }

    @Override
    public void HandleUnSelected(@NonNull CorlorRecycleViewObject target) {
        int filterIndex = IntStream.range(0,this.corlorList.size())
                .filter(i->this.corlorList.get(i).getCorlorId()==target.getCorlorId())
                .findFirst()
                .orElseGet(null);

        target.setSelected(false);
        this.corlorList.set(filterIndex,target);
        this.corlorListAdapter.notifyItemChanged(filterIndex);
    }
}