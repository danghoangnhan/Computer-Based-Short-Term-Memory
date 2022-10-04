package com.example.memorygame;

import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;

import java.util.ArrayList;

public class GlobalObject {
    private static GlobalObject instance = new GlobalObject();
    private ArrayList<MatchingObject> selectedButtonList,objectList;
    private Integer tmpImageViewID,tmpCorlorViewID;
    private ImageRecycleViewObject tmpSelectRecycle;
    private CorlorRecycleViewObject tmpCorlorObject;
    private Result result;

    // Getter-Setters
    public static GlobalObject getInstance() {
        if(instance == null){
            instance = new GlobalObject();
        }
        return instance;
    }
    public GlobalObject(){
        this.selectedButtonList = new ArrayList<>();
        this.result = new Result();
    }

    public ArrayList<MatchingObject> getObjectList() {return objectList;}

    public void setObjectList(ArrayList<MatchingObject> objectList) {this.objectList = objectList;}

    public ImageRecycleViewObject getTmpClickedImage() {return this.tmpSelectRecycle;}

    public void setTmpClickedImage(ImageRecycleViewObject tmpClickedImage) {this.tmpSelectRecycle = tmpClickedImage;}





    public ArrayList<MatchingObject> getSelectedButtonList() {
        return selectedButtonList;
    }

    public void setSelectedButtonList(ArrayList<MatchingObject> selectedButtonList) {this.selectedButtonList = selectedButtonList;}

    public Integer getTmpImageViewID() {return tmpImageViewID;}

    public void setTmpImageViewID(Integer tmpImageViewID) {this.tmpImageViewID = tmpImageViewID;}

    public Integer getTmpCorlorViewID() {return tmpCorlorViewID;}

    public void setTmpCorlorViewID(Integer tmpCorlorViewID) {this.tmpCorlorViewID = tmpCorlorViewID;}

    public ImageRecycleViewObject getTmpSelectRecycle() {
        return tmpSelectRecycle;
    }

    public void setTmpSelectRecycle(ImageRecycleViewObject tmpSelectRecycle) {this.tmpSelectRecycle = tmpSelectRecycle;}

    public CorlorRecycleViewObject getTmpCorlorObject() {
        return tmpCorlorObject;
    }

    public void setTmpCorlorObject(CorlorRecycleViewObject tmpCorlorObject) {this.tmpCorlorObject = tmpCorlorObject;}

    public Result getResult() {return result;}

    public void setResult(Result result) {this.result = result;}
}

