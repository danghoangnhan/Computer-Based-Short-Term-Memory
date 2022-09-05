package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;


public class RecycleViewHolder extends RecyclerView.ViewHolder {
    TextView tvTile;
    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTile = itemView.findViewById(R.id.tvTitle);
    }
}
