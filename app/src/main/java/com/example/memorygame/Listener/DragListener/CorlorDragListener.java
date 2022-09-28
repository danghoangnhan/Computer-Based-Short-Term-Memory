package com.example.memorygame.Listener.DragListener;

import android.view.DragEvent;
import android.view.View;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.RecycleView.CorlorListInterface;

public class CorlorDragListener implements View.OnDragListener{

    private boolean isDropped = false;
    private final CorlorListInterface listener;
    private final CorlorRecycleViewObject item;

    public CorlorDragListener(CorlorListInterface listener, CorlorRecycleViewObject item) {
        this.listener = listener;
        this.item = item;
    }
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                GlobalObject globalObject = GlobalObject.getInstance();
                globalObject.setTmpCorlorObject(this.item);
            }
            return true;
    }
}