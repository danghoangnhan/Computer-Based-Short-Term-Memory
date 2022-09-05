package com.example.memorygame.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    ArrayList<String> data;
    public RecyclerViewAdapter(ArrayList<String>data){
        this.data = data;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}