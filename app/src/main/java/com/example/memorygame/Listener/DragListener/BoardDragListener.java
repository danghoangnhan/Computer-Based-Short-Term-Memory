package com.example.memorygame.Listener.DragListener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.memorygame.CallBack.ButtonCorlorCall;
import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.CallBack.CorlorRecycleViewCallBack;
import com.example.memorygame.CallBack.ImageRecycleVIewCallBack;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.Listener.ClickListener.BoardClickListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.google.android.material.imageview.ShapeableImageView;

public class BoardDragListener implements View.OnDragListener {

    private     ImageRecycleVIewCallBack ImageListener;
    private     CorlorRecycleViewCallBack corlorRecycleViewCallBack;
    private     BoardClickListener boardClickListener ;
    private     ButtonImageCall buttonImageCall;
    private     ButtonCorlorCall buttonCorlorCall;
    private     MatchingObject currentObject;

    public BoardDragListener(ButtonImageCall buttonImageCall,MatchingObject init) {
        this.buttonImageCall = buttonImageCall;
        boardClickListener =  new BoardClickListener(this.buttonImageCall);
        this.currentObject = init;
    }
    public BoardDragListener(ButtonCorlorCall buttonCorlorCall,MatchingObject init) {
        this.buttonCorlorCall = buttonCorlorCall;
        boardClickListener =  new BoardClickListener(this.buttonCorlorCall);
        this.currentObject = init;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint({"NotifyDataSetChanged", "ResourceType"})
    @Override
    public boolean onDrag(View v, @NonNull DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DROP:
                View parent = (View)v.getParent();
                if (parent != null) {
                    GlobalObject globalObject = GlobalObject.getInstance();
                    ShapeableImageView destination = parent.findViewById(v.getId());
                    if (globalObject.getTmpClickedImage()!=null&&this.buttonImageCall!=null){
                            ImageRecycleViewObject targetObject = globalObject.getTmpClickedImage();
                            this.buttonImageCall.HandleSelected(v.getId(),targetObject,currentObject);
                            currentObject.setImage(targetObject);
                            this.boardClickListener.setImageRecycleViewObject(targetObject);
                            this.boardClickListener.setObject(currentObject);
                            this.boardClickListener.setTargetObject(targetObject);
                            globalObject.setTmpClickedImage(null);
                    }
                    if (globalObject.getTmpCorlorObject()!=null&&this.buttonCorlorCall!=null){
                            CorlorRecycleViewObject selectCorlorObject =  globalObject.getTmpCorlorObject();
                            boardClickListener.setCorlorRecycleViewObject(selectCorlorObject);
                            this.buttonCorlorCall.HandleSelected(v.getId(),selectCorlorObject,currentObject);
                            this.boardClickListener.setObject(currentObject);
                            this.boardClickListener.setCorlorRecycleViewObject(selectCorlorObject);
                            globalObject.setTmpCorlorObject(null);
                    }
                    destination.setOnClickListener(boardClickListener);
                }
                break;
            default:
                break;
        }
        return true;
    }
}