package com.example.memorygame.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Listener.DragListener.CorlorDragListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.example.memorygame.ViewHolder.CorlorViewHolder;

import java.util.List;

public class CorlorListAdapter extends RecyclerView.Adapter<CorlorViewHolder>
        implements View.OnTouchListener
{

    private Context context;
    private List<CorlorRecycleViewObject> corlorList;
    private CorlorListInterface corlorListInterface;


    public CorlorListAdapter(Context context, List<CorlorRecycleViewObject> data, CorlorListInterface corlorListInterface){
        this.corlorList = data;
        this.context =context;
        this.corlorListInterface = corlorListInterface;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull CorlorViewHolder holder, int position) {
        final CorlorRecycleViewObject item = this.corlorList.get(position);
        if (item.isSelected()){
            holder.imageView.setImageResource(R.drawable.delete);
            holder.imageView.setOnTouchListener(null);
            holder.imageView.setOnDragListener(null);
        }
        else {
            holder.imageView.setImageResource(item.getCorlorId());
            holder.imageView.setOnTouchListener(this);
            holder.imageView.setOnDragListener(new CorlorDragListener(this.corlorListInterface,item));
        }

    }
    @NonNull
    @Override
    public CorlorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.corlor_recycleview_item,parent,false);
        return new CorlorViewHolder(view,this.corlorListInterface);
    }
    @Override
    public int getItemCount() {
        return this.corlorList.size();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
            return true;
        }
        return false;
    }
}
