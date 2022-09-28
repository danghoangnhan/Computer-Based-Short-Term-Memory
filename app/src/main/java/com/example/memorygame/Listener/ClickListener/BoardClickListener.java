package com.example.memorygame.Listener.ClickListener;

import android.view.View;

import com.example.memorygame.CallBack.CorlorRecycleViewCallBack;
import com.example.memorygame.CallBack.ImageRecycleVIewCallBack;
import com.example.memorygame.Listener.DragListener.CorlorDragListener;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.example.memorygame.RecycleView.CorlorListInterface;
import com.google.android.material.imageview.ShapeableImageView;

public class BoardClickListener implements View.OnClickListener{

    private ImageRecycleViewObject imageRecycleViewObject;
    private CorlorRecycleViewObject corlorRecycleViewObject;
    private ImageRecycleVIewCallBack ImageListener;
    private CorlorRecycleViewCallBack corlorRecycleViewCallBack;


    public BoardClickListener(ImageRecycleVIewCallBack imageListener, CorlorRecycleViewCallBack corlorListener) {
        this.ImageListener = imageListener;
        this.corlorRecycleViewCallBack = corlorListener;
    }

    @Override
    public void onClick(View v) {
        View parent = (View) v.getParent();
        ShapeableImageView buttonView =parent.findViewById(v.getId());

        if (this.imageRecycleViewObject!=null){
            buttonView.setImageResource(R.color.white);
            this.ImageListener.HandleUnSelected(this.imageRecycleViewObject);
            this.imageRecycleViewObject = null;
            return;
        }

        if (this.corlorRecycleViewObject!=null){
            buttonView.setStrokeColorResource(R.color.white);
            this.corlorRecycleViewCallBack.HandleUnSelected(this.corlorRecycleViewObject);
            this.corlorRecycleViewObject = null;
            return;
        }
    }

    public void setImageRecycleViewObject(ImageRecycleViewObject imageRecycleViewObject) {
        this.imageRecycleViewObject = imageRecycleViewObject;
    }

    public void setCorlorRecycleViewObject(CorlorRecycleViewObject corlorRecycleViewObject) {
        this.corlorRecycleViewObject = corlorRecycleViewObject;
    }

}
