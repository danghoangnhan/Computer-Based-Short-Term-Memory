package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    Context context;
    List<Integer> data;
    public RecyclerViewAdapter(List<Integer> data){
        this.data = data;
    }
    public RecyclerViewAdapter(Context context,List<Integer> data){
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleviewitem,parent,false);
        return new RecycleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.imageView.setImageResource(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}