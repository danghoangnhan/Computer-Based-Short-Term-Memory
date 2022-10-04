package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MatchingObject implements Parcelable {
    private Integer color,row,column,image,viewId,width,height;



    public boolean sameColum(MatchingObject anotherObject){
            return this.column.compareTo(anotherObject.getColumn())==0;
    };
    public boolean sameRow(MatchingObject anotherObject){
            return this.row.compareTo(anotherObject.getRow())==0;
    }
    public boolean sameObject(MatchingObject anotherObject){
        if (this.image==null||anotherObject.getImage()==null)return false;
        Integer RESULT = this.image.compareTo(anotherObject.getImage());
        return RESULT==0;
    }
    public boolean sameCorlor(MatchingObject element) {
        return this.color.compareTo(element.getColor())==0;
    }


    public MatchingObject(Parcel in) {
        if (in.readByte() == 0) {
            color = null;
        } else {
            color = in.readInt();
        }
        if (in.readByte() == 0) {
            row = null;
        } else {
            row = in.readInt();
        }
        if (in.readByte() == 0) {
            column = null;
        } else {
            column = in.readInt();
        }
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
        if (in.readByte() == 0) {
            viewId = null;
        } else {
            viewId = in.readInt();
        }
    }

    public MatchingObject() {

    }
    public MatchingObject(ImageRecycleViewObject another){
        this.setImage(another.getImageId());
    }


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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
    public Integer getWidth() {return width;}

    public void setWidth(Integer width) {this.width = width;}

    public Integer getHeight() {return height;}

    public void setHeight(Integer height) {this.height = height;}

    public void clone(@NonNull MatchingObject another){
        this.setColor(another.getColor());
        this.setImage(another.getImage());
        this.setViewId(another.getViewId());
        this.setColumn(another.getColumn());
        this.setRow(another.getRow());
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (color == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(color);
        }
        if (row == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(row);
        }
        if (column == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(column);
        }
        if (image == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(image);
        }
        if (viewId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(viewId);
        }
    }
    public static final Parcelable.Creator<MatchingObject> CREATOR = new Parcelable.Creator<MatchingObject>() {
        public MatchingObject createFromParcel(Parcel in) {
            return new MatchingObject(in);
        }
        public MatchingObject[] newArray(int size) {
            return new MatchingObject[size];
        }
    };

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }


}
