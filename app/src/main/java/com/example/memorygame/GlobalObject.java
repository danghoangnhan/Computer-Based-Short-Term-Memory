package com.example.memorygame;

import com.example.memorygame.Database.Model.Session;
import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.Object.Result;

import java.util.ArrayList;
import java.util.List;

public class GlobalObject {
    private static GlobalObject instance = new GlobalObject();
    private ArrayList<MatchingObject> selectedButtonList,objectList;
    private Integer tmpImageViewID,tmpCorlorViewID;
    private ImageRecycleViewObject tmpSelectRecycle;
    private CorlorRecycleViewObject tmpCorlorObject;
    private Result result;
    private int gameState;
    private List<Integer> ranImg;
    private List<ImageRecycleViewObject> selectedImage;
    private Session session;

    public static GlobalObject getInstance() {
        if(instance == null){
            instance = new GlobalObject();
        }
        return instance;
    }
    public GlobalObject(){
        this.selectedButtonList = new ArrayList<>();
        this.result = new Result();
        this.gameState = 1;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(User session) {
        this.session = new Session(session);
    }

    public List<ImageRecycleViewObject> getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(List<ImageRecycleViewObject> selectedImage) {
        this.selectedImage = selectedImage;
    }

    public List<Integer> getRanImg() {
        return ranImg;
    }

    public void setRanImg(List<Integer> ranImg) {
        this.ranImg = ranImg;
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

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}

