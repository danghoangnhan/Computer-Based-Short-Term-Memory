package com.example.memorygame.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Activity.SelectObjectActivity;
import com.example.memorygame.Listener.DragListener.MyDragListener;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder>
        implements View.OnTouchListener
{
    private Context context;
    private List<ImageRecycleViewObject> data;
    private RecycleViewInterface recycleViewInterface;


    public RecyclerViewAdapter(Context context, List<ImageRecycleViewObject> data, RecycleViewInterface recycleViewInterface) {
        this.data = data;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }

    public RecyclerViewAdapter(SelectObjectActivity context, List<ImageRecycleViewObject> selectedImage) {
        this.context = context;
        this.data = selectedImage;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleviewitem, parent, false);
        return new RecycleViewHolder(view, recycleViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        final ImageRecycleViewObject item = this.data.get(position);
        if (item.isSelected()){
            Drawable cross = context.getResources().getDrawable(R.drawable.cross);
            holder.imageView.setForeground(cross);
            holder.imageView.setImageResource(item.getImageId());
            holder.imageView.setOnTouchListener(null);
            holder.imageView.setOnDragListener(null);
        }else {
            holder.imageView.setImageResource(item.getImageId());
            holder.imageView.setOnTouchListener(this);
            holder.imageView.setOnDragListener(new MyDragListener(this.recycleViewInterface,this.getListItem(position)));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private ImageRecycleViewObject getListItem(int position) {return this.data.get(position);}


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
