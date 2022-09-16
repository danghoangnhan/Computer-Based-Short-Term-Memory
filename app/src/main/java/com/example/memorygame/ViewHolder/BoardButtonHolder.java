package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardButtonHolder extends RecyclerView.ViewHolder{

    Integer color,row,column,image;
    ImageButton imageButton;

    public BoardButtonHolder(@NonNull View itemView,Integer backgroundColor,Integer backgroundReource) {
        super(itemView);
        this.imageButton.setBackgroundColor(backgroundColor);
        this.imageButton.setBackgroundResource(backgroundReource);
    }

}
