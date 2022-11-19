package com.example.memorygame.Listener.ClickListener;

import android.view.View;

import com.example.memorygame.CallBack.ButtonImageCall;
import com.example.memorygame.CallBack.CorlorRecycleViewCallBack;
import com.example.memorygame.CallBack.ImageRecycleVIewCallBack;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BoardClickListener implements View.OnClickListener{

    private ImageRecycleViewObject imageRecycleViewObject;
    private CorlorRecycleViewObject corlorRecycleViewObject;
    private ImageRecycleVIewCallBack ImageListener;
    private CorlorRecycleViewCallBack corlorRecycleViewCallBack;
    private ButtonImageCall buttonImageCall;
    private ImageRecycleViewObject targetObject;
    private MatchingObject object;

    public BoardClickListener(CorlorRecycleViewCallBack imageListener) {
        this.corlorRecycleViewCallBack = imageListener;
    }
    public BoardClickListener(ButtonImageCall buttonImageCall){
        this.buttonImageCall = buttonImageCall;
    }

    @Override
    public void onClick(View v) {
        View parent = (View) v.getParent();
        ShapeableImageView buttonView =parent.findViewById(v.getId());

        if (this.ImageListener!=null){
            buttonView.setImageResource(R.color.white);
            if(this.ImageListener!=null){
                this.ImageListener.HandleUnSelected(this.imageRecycleViewObject);
            }
            this.imageRecycleViewObject = null;
            return;
        }

        if (this.corlorRecycleViewCallBack!=null){
            buttonView.setStrokeColorResource(R.color.white);
            this.corlorRecycleViewCallBack.HandleUnSelected(this.corlorRecycleViewObject);
            this.corlorRecycleViewObject = null;
            return;
        }
        if (this.buttonImageCall!=null){
            this.buttonImageCall.HandleUnSelected(v.getId(),targetObject,object);
        }
    }

    public void setImageRecycleViewObject(ImageRecycleViewObject imageRecycleViewObject) {
        this.imageRecycleViewObject = imageRecycleViewObject;
    }

    public void setCorlorRecycleViewObject(CorlorRecycleViewObject corlorRecycleViewObject) {
        this.corlorRecycleViewObject = corlorRecycleViewObject;
    }

    public void setTargetObject(ImageRecycleViewObject targetObject) {
        this.targetObject = targetObject;
    }

    public void setObject(MatchingObject object) {
        this.object = object;
    }
}
