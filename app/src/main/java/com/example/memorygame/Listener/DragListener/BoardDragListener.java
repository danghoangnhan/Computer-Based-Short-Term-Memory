package com.example.memorygame.Listener.DragListener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;

import androidx.annotation.NonNull;
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
    private     MatchingObject currentObject;

    public BoardDragListener(CorlorRecycleViewCallBack corlorListener) {
        this.corlorRecycleViewCallBack = corlorListener;
        boardClickListener =  new BoardClickListener(this.corlorRecycleViewCallBack);
    }
    public BoardDragListener(ButtonImageCall buttonImageCall,MatchingObject init) {
        this.buttonImageCall = buttonImageCall;
        boardClickListener =  new BoardClickListener(this.buttonImageCall);
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

                    ArrayList<MatchingObject> selectedList = globalObject.getSelectedButtonList();
                    List<MatchingObject> objectList = globalObject.getObjectList();

                    if (globalObject.getTmpClickedImage()!=null){

                        ImageRecycleViewObject targetObject = globalObject.getTmpClickedImage();
                        globalObject.setSelectedButtonList(selectedList);
                        boardClickListener.setImageRecycleViewObject(targetObject);

                        if (this.buttonImageCall!=null){
                            this.buttonImageCall.HandleSelected(v.getId(),targetObject,currentObject);
                            currentObject.setImage(targetObject);
                            this.boardClickListener.setObject(currentObject);
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
                        globalObject.setSelectedButtonList(selectedList);
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