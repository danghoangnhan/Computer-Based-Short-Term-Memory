package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.example.memorygame.ViewHolder.CorlorViewHolder;

import java.util.List;

public class CorlorListAdapter extends RecyclerView.Adapter<CorlorViewHolder> {

    private Context context;
    private List<Integer> corlorList;
    private CorlorListInterface corlorListInterface;


    public CorlorListAdapter(Context context,List<Integer> data,CorlorListInterface corlorListInterface){
        this.corlorList = data;
        this.context =context;
        this.corlorListInterface = corlorListInterface;
    }
    @Override
    public void onBindViewHolder(@NonNull CorlorViewHolder holder, int position) {
        final Integer item = this.corlorList.get(position);
        holder.imageView.setImageResource(item);
    }
    @Override
    public CorlorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.corlor_recycleview_item,parent,false);
        return new CorlorViewHolder(view,this.corlorListInterface);
    }
    @Override
    public int getItemCount() {
        return this.corlorList.size();
    }
}
