package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;

public class CorlorViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CorlorViewHolder(@NonNull View itemView, @NonNull CorlorListInterface corlorListInterface) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.corlor_image_item);
        this.imageView.setOnClickListener(view->corlorListInterface.onCorlorItemClick(view,getAdapterPosition()));
    }
}