package com.example.memorygame.Object;

import androidx.annotation.NonNull;

public class MatchingObject  {
    private Integer row,column,viewId,width,height;
    ImageRecycleViewObject image;
    CorlorRecycleViewObject corlor,initCorlor;

    public boolean sameColum(@NonNull MatchingObject anotherObject){return this.column.compareTo(anotherObject.getColumn())==0;}

    public  boolean sameRow(@NonNull MatchingObject anotherObject){return this.row.compareTo(anotherObject.getRow())==0;}

    public  boolean sameObject(@NonNull MatchingObject anotherObject){
        return this.image.compareTo(anotherObject.getImage()) == 0;
    }
    public  boolean sameCorlor(@NonNull MatchingObject anotherObject) {
        return this.corlor.compareTo(anotherObject.getCorlor());
    }

    public MatchingObject() {

    }

    public MatchingObject(@NonNull ImageRecycleViewObject another){this.setImage(another);}


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public ImageRecycleViewObject getImage() {
        return image;
    }
    public int getImageId() {
        return image.getImageId();
    }

    public void setImage(@NonNull ImageRecycleViewObject image) {
        this.image = image;
    }
    public Integer getWidth() {return width;}

    public void setWidth(Integer width) {this.width = width;}

    public Integer getHeight() {return height;}

    public void setHeight(Integer height) {this.height = height;}

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public CorlorRecycleViewObject getInitCorlor() {
        return initCorlor;
    }

    public void setInitCorlor(CorlorRecycleViewObject initCorlor) {
        this.initCorlor = initCorlor;
    }
    public CorlorRecycleViewObject getCorlor() {
        return corlor;
    }
    public void setCorlor(CorlorRecycleViewObject corlor) {
        this.corlor = corlor;
    }
}
