package com.example.memorygame.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Activity.SelectObjectActivity;
import com.example.memorygame.Listener.MyDragListener;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder>   implements View.OnTouchListener {
    private Context context;
    private List<Integer> data;
    private RecycleViewInterface recycleViewInterface;


    public RecyclerViewAdapter(Context context, List<Integer> data, RecycleViewInterface recycleViewInterface) {
        this.data = data;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }

    public RecyclerViewAdapter(SelectObjectActivity context, List<Integer> selectedImage) {
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
        final Integer item = this.data.get(position);
        holder.imageView.setImageResource(item);

        holder.imageView.setOnTouchListener(this);
        holder.imageView.setOnDragListener(new MyDragListener(this.recycleViewInterface));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private Integer getListItem(int position) {
        return this.data.get(position);
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
