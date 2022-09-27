package com.example.memorygame.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.example.memorygame.ViewHolder.CorlorViewHolder;
import com.example.memorygame.utilities.ItemTouchHelperContract;

import java.util.Collections;
import java.util.List;

public class CorlorListAdapter extends RecyclerView.Adapter<CorlorViewHolder> implements ItemTouchHelperContract {

    private Context context;
    private List<Integer> corlorList;
    private CorlorListInterface corlorListInterface;


    public CorlorListAdapter(Context context,List<Integer> data,CorlorListInterface corlorListInterface){
        this.corlorList = data;
        this.context =context;
        this.corlorListInterface = corlorListInterface;
    }
    @SuppressLint("ClickableViewAccessibility")
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

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(this.corlorList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(this.corlorList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(CorlorViewHolder myViewHolder) {
        myViewHolder.imageView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(CorlorViewHolder myViewHolder) {

    }
}
