package com.example.memorygame.Listener;


import android.annotation.SuppressLint;
import android.view.DragEvent;
import android.view.View;

import com.example.memorygame.RecycleView.RecycleViewInterface;

public class MyDragListener implements View.OnDragListener {


    private boolean isDropped = false;
    private final RecycleViewInterface listener;

    public MyDragListener(RecycleViewInterface listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int viewId;
        switch (event.getAction()){
                case DragEvent.ACTION_DROP:
                    viewId = v.getId();
                    isDropped = true;
                    int positionTarget = -1;
                    View viewSource = (View) event.getLocalState();
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    viewId = v.getId();
                    break;
            }
        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}