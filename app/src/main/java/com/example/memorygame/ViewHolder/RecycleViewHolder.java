package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Listener.MyDragListener;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.RecycleViewInterface;


public class RecycleViewHolder extends RecyclerView.ViewHolder  {
    public ImageView imageView;
    public RecycleViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.itemImage);
        this.imageView.setOnClickListener(view -> {
            if (recycleViewInterface != null){
                recycleViewInterface.onItemClick(view,getAdapterPosition());
            }
        });
    }
}
