package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.ButtonList;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.HandleStageButton;
import com.example.memorygame.Language;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectObjectActivity extends AppCompatActivity implements
        HandleStageButton, Language {

    private List<ImageRecycleViewObject> selectedImage;
    private List<Integer> images;
    private Button nextButton,escButton,replayButton;
    private List<ShapeableImageView>buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_object);
        this.images = ButtonList.getInstance().getRandomImageResource();
        this.buttons = ButtonList.getInstance().getBoardButtonList().stream().map(element->{ShapeableImageView view = findViewById(element);return view;}).collect(Collectors.toList());
        this.nextButton = findViewById(R.id.nextButton);
        this.escButton = findViewById(R.id.escButton);
        this.replayButton = findViewById(R.id.replayButton);
        this.selectedImage = new ArrayList<>();
        this.initialImageTab();

        this.nextButton.setOnClickListener(this::handleNextButton);
        this.escButton.setOnClickListener(this::handleEscButton);
        this.replayButton.setOnClickListener(this::handleReplayButton);

        if (GlobalObject.getInstance().getGameState()==1){
            GlobalObject.getInstance().getSession().setStartRound(new Date());
        }
    }
    public static Map<ShapeableImageView, Integer> zipToMap(@NonNull List<ShapeableImageView> keys, @NonNull List<Integer> values) {
        return IntStream.range(0, keys.size())
                .boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void selectedClick(Integer image, ShapeableImageView button){
        if (this.selectedImage.stream().noneMatch(element->element.getImageId()==image)){
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
            Integer index = IntStream.range(0,this.selectedImage.size())
                    .filter(i->this.selectedImage.get(i).getImageId()==image)
                    .findFirst()
                    .orElseGet(null);
            ImageRecycleViewObject selectObject  = this.selectedImage
                    .stream()
                    .filter(element->element.getImageId()==image)
                    .findFirst()
                    .get();
            if (index!=null){
                this.selectedImage.remove(selectObject);
                button.setImageResource(image);
            }
        }
    }
    public  void initialImageTab(){
        Map <ShapeableImageView, Integer> buttonIntegerMap = zipToMap(this.buttons,this.images);
        buttonIntegerMap.forEach((button, Image) -> {
            button.setImageResource(Image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                button.setOnClickListener(view -> selectedClick(Image, button));
            }
        });
    }

    @Override
    public void handleNextButton(View view) {
        if(this.selectedImage.size()>0){
            GlobalObject.getInstance().setRanImg(this.images);
            Intent intent = new Intent(this,MatchColorActivity.class);
            GlobalObject.getInstance().setSelectedImage(this.selectedImage);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),Language.Chinese.get(Key.Objectionable), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void handleReplayButton(View view) {startActivity(new Intent(this,SelectObjectActivity.class));}
    @Override
    public void handleEscButton(View view) {this.startActivity(new Intent(this,LoginActivity.class));}
}