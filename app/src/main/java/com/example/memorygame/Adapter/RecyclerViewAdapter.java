package com.example.memorygame.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.SelectObjectActivity;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    ArrayList<String> data;
    public RecyclerViewAdapter(ArrayList<String>data){
        this.data = data;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewitem,parent,false);
        return new RecycleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.tvTile.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}