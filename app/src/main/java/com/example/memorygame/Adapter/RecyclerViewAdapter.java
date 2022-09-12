package com.example.memorygame.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.CallBack.ItemTouchHelperContract;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> implements ItemTouchHelperContract {
    Context context;
    List<Integer> data;

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
        holder.imageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(RecycleViewHolder myViewHolder) {
        myViewHolder.imageView.setBackgroundColor(Color.GRAY);
    }
    @Override
    public void onRowClear(RecycleViewHolder myViewHolder) {
        myViewHolder.imageView.setBackgroundColor(Color.WHITE);
    }

}