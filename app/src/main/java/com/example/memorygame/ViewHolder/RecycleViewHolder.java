package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;


public class RecycleViewHolder extends RecyclerView.ViewHolder {
    TextView imageText;
    public ImageView imageView;
    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageText = itemView.findViewById(R.id.itemTitle);
        this.imageView = itemView.findViewById(R.id.itemImage);
    }
}
