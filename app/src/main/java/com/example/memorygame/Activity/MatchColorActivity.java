package com.example.memorygame.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.CallBack.ItemMoveCallback;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.Customer;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.listener.OnCustomerListChangedListener;
import com.example.memorygame.listener.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MatchColorActivity extends AppCompatActivity implements OnCustomerListChangedListener, OnStartDragListener, HandleStageButton {
    ArrayList<Integer> selectedImage;
    List<Integer>  colorList;
    List<MatchingObject> objectList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    Button nextButton,escButton,replayButton;
    List<ImageButton>buttons;
    private ItemTouchHelper mItemTouchHelper;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
        Intent receiverIntent = getIntent();
        this.selectedImage = receiverIntent.getIntegerArrayListExtra("selectedImages");
        this.colorList = randomColor(9);
        this.buttons = Arrays.asList(findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9));
        this.nextButton = findViewById(R.id.nextButton);
        this.recyclerView = findViewById(R.id.recycleview);
        this.linearLayoutManager = new LinearLayoutManager(MatchColorActivity.this,LinearLayoutManager.HORIZONTAL,false);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this,selectedImage);
        this.recyclerView = findViewById(R.id.recycleview);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
//        this.buttons.forEach(buttonElement->{buttonElement.setOnDragListener(dragListener);});
        this.generatingMatchingObject(this.buttons,9);
        this.nextButton.setOnClickListener(view -> handleNextButton(view));

        ItemTouchHelper.Callback callback = new ItemMoveCallback(this.recyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    public List<MatchingObject> generatingMatchingObject(List<ImageButton> buttonList, Integer NumberPerrow){
        List<MatchingObject> matchingObjects = new ArrayList<>();
        for (int i=0;i<buttonList.size()/NumberPerrow;i++){
            for (int j =0;j<NumberPerrow;j++){
                MatchingObject currentObject = new MatchingObject();
                currentObject.setColor(randomColor());
                currentObject.setColumn(j);
                currentObject.setRow(i);
                currentObject.setImageButton(buttonList.get(i));
//                currentObject.getImageButton().setOnDragListener((v, event) -> dragListener.onDrag(v,event));
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

//    public View.OnDragListener dragListener = (view, dragEvent) -> {
//        final View localStateView = (View) dragEvent.getLocalState();
//        switch (dragEvent.getAction()){
//            case DragEvent.ACTION_DRAG_ENDED:
//                break;
//            case DragEvent.ACTION_DRAG_EXITED:
//                break;
//            case DragEvent.ACTION_DRAG_LOCATION:
//                break;
//            case DragEvent.ACTION_DRAG_STARTED:
//                dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
//                break;
//            case DragEvent.ACTION_DRAG_ENTERED:
//                view.invalidate();
//                break;
//            case DragEvent.ACTION_DROP:
//                ClipData.Item item  = dragEvent.getClipData().getItemAt(0);
//                String dragData = item.getText().toString();
//                Toast.makeText(this,dragData,Toast.LENGTH_SHORT).show();
//                View localVIew = (View) dragEvent.getLocalState();
//                ViewParent owner = localVIew.getParent();
//        }
//        return false;
//    };
    public boolean onTouch(View view, MotionEvent motionEvent){
        int action = motionEvent.getAction();
        switch (action){
            case (MotionEvent.ACTION_DOWN):
                Log.d(TAG,"Action was down");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(TAG,"Action was move");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(TAG,"Action was up");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(TAG,"Action was cancel");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(TAG,"Action was outside");
                return true;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }
    public boolean onDrag(View view, DragEvent dragEvent){
        switch(dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "onDrag: drag started.");

                return true;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(TAG, "onDrag: drag entered.");
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d(TAG, "onDrag: current point: ( " + dragEvent.getX() + " , " + dragEvent.getY() + " )"  );

                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "onDrag: exited.");
                return true;

            case DragEvent.ACTION_DROP:

                Log.d(TAG, "onDrag: dropped.");

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "onDrag: ended.");
                return true;
            // An unknown action type was received.
            default:
                Log.e(TAG,"Unknown action type received by OnStartDragListener.");
                return true;
        }
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
    public void onNoteListChanged(List<Customer> customers) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}