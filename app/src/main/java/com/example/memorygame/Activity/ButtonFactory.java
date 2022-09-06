package com.example.memorygame.Activity;

import com.example.memorygame.R;

import java.util.Arrays;
import java.util.List;

public class ButtonFactory {
    private static ButtonFactory instance = null;
    public static  ButtonFactory  getInstance(){
        if (instance==null){
            instance = new ButtonFactory();
        }
        return  instance;
    }
    private List<Integer>images = Arrays.asList(R.drawable.camel, R.drawable.fox, R.drawable.lion, R.drawable.coala,R.drawable.camel, R.drawable.fox, R.drawable.lion, R.drawable.coala,R.drawable.lion);
        public List<Integer> getTestImageList(){return  this.images;}
}
