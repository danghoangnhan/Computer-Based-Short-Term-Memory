package com.example.memorygame;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
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
            R.color.white,
            R.color.aqua,
            R.color.fuchsia,
            R.color.dark_magenta,
            R.color._light_green,
            R.color.azure,
            R.color.blue,
            R.color.red,
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

    private static Random rand = new Random();

    public  List<Integer> getButtonBoard(){return buttonIdList;}
    public  List<Integer> getCorlorCodeList(){return CorlorList;}

    public Integer getRandomDrawable(){
        int randomIndex = this.rand.nextInt(buttonIdList.size());
        return buttonIdList.get(randomIndex);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> randomColor(Integer listSize){
        return IntStream.range(1,listSize).mapToObj(i->randomColor()).collect(Collectors.toList());
    }
    public Integer randomColor(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public List<Integer> getIconTest() {
        return iconTest;
    }
}
