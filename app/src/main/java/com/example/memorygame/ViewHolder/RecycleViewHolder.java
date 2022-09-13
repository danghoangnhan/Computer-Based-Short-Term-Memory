package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.utilities.ItemTouchHelperViewHolder;


public class RecycleViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public ImageView imageView;
    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.itemImage);
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {
        this.itemView.setBackgroundColor(0);
    }
}
