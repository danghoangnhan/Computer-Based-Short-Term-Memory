package com.example.memorygame.Listener.DragListener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.CallBack.CorlorRecycleViewCallBack;
import com.example.memorygame.CallBack.ImageRecycleVIewCallBack;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.Listener.ClickListener.BoardClickListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class BoardDragListener implements View.OnDragListener {

    private     ImageRecycleVIewCallBack ImageListener;
    private     CorlorRecycleViewCallBack corlorRecycleViewCallBack;
    private     BoardClickListener boardClickListener ;
    private     ButtonImageCall buttonImageCall;
    private     MatchingObject init;

    public BoardDragListener(ImageRecycleVIewCallBack imageListener) {
        this.ImageListener = imageListener;
        boardClickListener =  new BoardClickListener(this.ImageListener);
    }
    public BoardDragListener(CorlorRecycleViewCallBack corlorListener) {
        this.corlorRecycleViewCallBack = corlorListener;
        boardClickListener =  new BoardClickListener(this.corlorRecycleViewCallBack);
    }
    public BoardDragListener(ButtonImageCall buttonImageCall,MatchingObject init) {
        this.buttonImageCall = buttonImageCall;
        boardClickListener =  new BoardClickListener(this.buttonImageCall);
        this.init = init;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint({"NotifyDataSetChanged", "ResourceType"})
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DROP:
                View parent = (View)v.getParent();
                if (parent != null) {
                    GlobalObject globalObject = GlobalObject.getInstance();
                    ShapeableImageView destination = parent.findViewById(v.getId());
                    List<MatchingObject> selectedList = globalObject.getSelectedButtonList();
                    List<MatchingObject> objectList = globalObject.getObjectList();

                    if (globalObject.getTmpClickedImage()!=null){
                        ImageRecycleViewObject targetObject = globalObject.getTmpClickedImage();
                        destination.setImageResource(targetObject.getImageId());
                        MatchingObject object = selectedList.stream()
                                .filter(matchingObject -> matchingObject.getViewId()==v.getId())
                                .findAny()
                                .orElseGet(()->{
                                    MatchingObject newMatch = objectList.stream().filter(element->element.getViewId()==v.getId()).findFirst().get();
                                    selectedList.add(newMatch);
                                    return newMatch;
                                });

                        object.setImage(globalObject.getTmpClickedImage().getImageId());
                        globalObject.setSelectedButtonList((ArrayList<MatchingObject>) selectedList);
                        boardClickListener.setImageRecycleViewObject(targetObject);
                        if (this.ImageListener!=null){
                            this.ImageListener.HandleSelected(targetObject);
                        }
                        if (this.buttonImageCall!=null){
                            this.buttonImageCall.HandleSelected(v.getId(),targetObject,object);
                            this.boardClickListener.setObject(init);
                            this.boardClickListener.setTargetObject(targetObject);
                        }
                        globalObject.setTmpClickedImage(null);
                    }
                    if (globalObject.getTmpCorlorObject()!=null){
                        CorlorRecycleViewObject selectCorlorObject =  globalObject.getTmpCorlorObject();
                        destination.setStrokeColorResource(selectCorlorObject.getCorlorId());
                        MatchingObject object = selectedList.stream()
                                .filter(matchingObject -> matchingObject.getViewId()==v.getId())
                                .findAny()
                                .orElseGet(()->{
                                    MatchingObject newMatch = objectList.stream().filter(element->element.getViewId()==v.getId()).findFirst().get();
                                    selectedList.add(newMatch);
                                    return newMatch;
                                });
                        object.setColor(selectCorlorObject.getCorlorId());
                        globalObject.setSelectedButtonList((ArrayList<MatchingObject>) selectedList);
                        boardClickListener.setCorlorRecycleViewObject(selectCorlorObject);
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