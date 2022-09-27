package com.example.memorygame.Listener;

import android.annotation.SuppressLint;
import android.view.DragEvent;
import android.view.View;

import com.example.memorygame.RecycleView.RecycleViewInterface;

public class BoardDragListener implements View.OnDragListener {

    private final RecycleViewInterface listener;

    public BoardDragListener(RecycleViewInterface listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int viewId;
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                viewId = v.getId();
                break;
            default:
                break;
        }
        return true;
    }
}