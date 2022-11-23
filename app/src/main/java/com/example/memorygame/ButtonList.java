package com.example.memorygame;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ButtonList {
    private static final ButtonList instance = new ButtonList();

    //private constructor to avoid client applications to use constructor
    private ButtonList(){}

    public static ButtonList getInstance(){return instance;}

    private  List<Integer> buttonIdList = Arrays.asList(R.id.button1,
    R.id.button2,
    R.id.button3,
    R.id.button4,
    R.id.button5,
    R.id.button6,
    R.id.button7,
    R.id.button8,
    R.id.button9);
    private final List<Integer> CorlorList = Arrays.asList(
            R.color.black,
            R.color.orange,
            R.color.green,
            R.color.red,
            R.color.saddle_brown,
            R.color.gray,
            R.color.blue,
            R.color.hot_pink,
            R.color.yellow
    );
    private final List<Integer> iconTest = Arrays.asList(
            R.drawable.fox,
            R.drawable.lion,
            R.drawable.camel,
            R.drawable.code,
            R.drawable.coala,
            R.drawable.monkey,
            R.drawable.wolf
    );
    private final List<Integer> imageList = Arrays.asList(
            R.drawable.clock,
            R.drawable.fan,
            R.drawable.clothhanger,
            R.drawable.glasses,
            R.drawable.glass,
            R.drawable.phone,
            R.drawable.key,
            R.drawable.toothbursh,
            R.drawable.hat,
            R.drawable.pens,
            R.drawable.umbrella,
            R.drawable.spoon,
            R.drawable.television,
            R.drawable.book,
            R.drawable.wallet
    );

    private final List<Integer> BoardButtonList = Arrays.asList(
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9
    );



    private static Random rand = new Random();

    public  List<Integer> getButtonBoard(){return buttonIdList;}
    public  List<Integer> getCorlorCodeList(){
        return CorlorList;
    }
    @NonNull
    public static List<Integer> suffledImage(List<Integer>originalList){
        List<Integer> result = new ArrayList<>(originalList);
        Collections.shuffle(result);
        return result;
    }

    public Integer getRandomDrawable(){
        int randomIndex = this.rand.nextInt(buttonIdList.size());
        return buttonIdList.get(randomIndex);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> randomColorList(Integer listSize){return IntStream.range(1,listSize).mapToObj(i->randomColor()).collect(Collectors.toList());}
    public Integer randomColor(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    public Integer randomColorResource(){
        int randomIndex = this.rand.nextInt(this.CorlorList.size());
        return this.CorlorList.get(randomIndex);
    }
    public Integer getRandomIconResource(){
        int randomIndex = this.rand.nextInt(this.iconTest.size());
        return this.iconTest.get(randomIndex);
    }
    public List<Integer> getRandomImageResource(){
        List<Integer>randomImageList = new ArrayList<>(this.imageList);
        Collections.shuffle(randomImageList);
        return randomImageList.subList(0,9);
    }
    public List<Integer> getSuffleCorlorList(){
        List<Integer>randomColorList = new ArrayList<>(this.CorlorList);
        Collections.shuffle(randomColorList);
        return randomColorList;
    }

    public List<Integer> getImageList() {
        return imageList;
    }

    public List<Integer> getBoardButtonList() {
        return BoardButtonList;
    }

    public List<?> suffer(){
        List<Integer>randomColorList = new ArrayList<>(this.CorlorList);
        Collections.shuffle(randomColorList);
        return this.CorlorList;
    }
}
