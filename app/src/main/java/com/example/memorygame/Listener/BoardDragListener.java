package com.example.memorygame.Listener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.google.android.material.imageview.ShapeableImageView;

public class BoardDragListener implements View.OnDragListener {

    private final RecycleViewInterface listener;

    public BoardDragListener(RecycleViewInterface listener) {
        this.listener = listener;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint({"NotifyDataSetChanged", "ResourceType"})
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int viewId;
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                View parent = (View)v.getParent();
                View targetView = (View) event.getLocalState();
                if (parent != null) {
                    ShapeableImageView view1 = parent.findViewById(v.getId());
                    view1.setImageResource((Integer) targetView.getTag());
                }
                break;
            default:
                break;
        }
        return true;
    }
}