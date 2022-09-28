package com.example.memorygame.Listener.DragListener;


import android.annotation.SuppressLint;
import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

public class MyDragListener implements View.OnDragListener {


    private boolean isDropped = false;
    private final RecycleViewInterface listener;
    private final ImageRecycleViewObject data;

    public MyDragListener(RecycleViewInterface listener, ImageRecycleViewObject position) {
        this.listener = listener;
        this.data = position;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
                case DragEvent.ACTION_DRAG_ENTERED:
                    GlobalObject globalObject = GlobalObject.getInstance();
                    globalObject.setTmpClickedImage(this.data);
        }
        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}