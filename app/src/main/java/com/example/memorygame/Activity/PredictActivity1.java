package com.example.memorygame.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.memorygame.ButtonList;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;
import com.example.memorygame.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredictActivity1 extends AppCompatActivity implements HandleStageButton {
    private Button nextButton,escButton,replayButton;
    private List<ImageRecycleViewObject> selectedImage;
    private List<Integer> images;
    private List<ShapeableImageView>buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict1);
        this.images = ButtonList.getInstance().getImageList();
        this.buttons = ButtonList.getInstance().getBoardButtonList().stream().map(element->{ShapeableImageView view = findViewById(element);return view;}).collect(Collectors.toList());
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.selectedImage = new ArrayList<>();
        this.initialImageTab();
        initialButton();
    }

    public void initialButton(){
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);

        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);
    }


    public  void initialImageTab(){
        Map<ShapeableImageView, Integer> buttonIntegerMap = zipToMap(this.buttons,this.images);
        buttonIntegerMap.forEach((button, Image) -> {
            button.setImageResource(Image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                button.setOnClickListener(view -> selectedClick(Image, button));
            }
        });
    }
    public static <K, V> Map<ShapeableImageView, Integer> zipToMap(List<ShapeableImageView> keys, List<Integer> values) {
        return IntStream.range(0, keys.size())
                .boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void selectedClick(Integer image, ShapeableImageView button){
        ImageRecycleViewObject selectObject  = this.selectedImage
                .stream()
                .filter(element->element.getImageId()==image)
                .findFirst()
                .orElse(null);
        if (selectObject==null){
            button.setImageResource(image);
            button.setStrokeColorResource(R.color.yellow);
            ImageRecycleViewObject newObject = new ImageRecycleViewObject();
            newObject.setImageId(image);
            this.selectedImage.add(newObject);
        }
        else {
            button.setForeground(null);
            button.setImageResource(image);
            button.setStrokeColorResource(R.color.white);
            this.selectedImage.remove(selectObject);
        }
    }

    @Override
    public void handleReplayButton(View view) {this.startActivity(new Intent(this,PredictActivity1.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}
    @Override
    public void handleNextButton(View view) {
        if(this.selectedImage.size()>0){
            Intent intent = new Intent(this,PredictActivity2.class);
            intent.putParcelableArrayListExtra("selectedImages", (ArrayList<ImageRecycleViewObject>) this.selectedImage);
            GlobalObject.getInstance().getResult().setSelected1(this.selectedImage.stream().map(MatchingObject::new).collect(Collectors.toList()));
            this.startActivity(intent);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"尚未選擇物件", Toast.LENGTH_SHORT).show();
        }

    }
}