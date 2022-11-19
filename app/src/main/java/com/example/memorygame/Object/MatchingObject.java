package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MatchingObject  {
    private Integer initCorlor,color,row,column,viewId,width,height;
    ImageRecycleViewObject image;

    public boolean sameColum(@NonNull MatchingObject anotherObject){return this.column.compareTo(anotherObject.getColumn())==0;}

    public  boolean sameRow(@NonNull MatchingObject anotherObject){return this.row.compareTo(anotherObject.getRow())==0;}

    public  boolean sameObject(MatchingObject anotherObject){
        if (this.image==null||anotherObject.getImage()==null)return false;
        int RESULT = this.image.compareTo(anotherObject.getImage());
        return RESULT == 0;
    }
    public  boolean sameCorlor(MatchingObject element) {
        if (element==null)return false;
        if (element.getColor()==null) return false;
        return this.color.compareTo(element.getColor())==0;
    }

    public MatchingObject() {}

    public MatchingObject(@NonNull ImageRecycleViewObject another){this.setImage(another);}


    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

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

    public Integer getInitCorlor() {
        return initCorlor;
    }

    public void setInitCorlor(Integer initCorlor) {
        this.initCorlor = initCorlor;
    }
}
