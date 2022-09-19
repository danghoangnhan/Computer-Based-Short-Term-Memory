package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageButton;

import com.google.android.material.imageview.ShapeableImageView;

public class MatchingObject implements Parcelable {
    Integer color,row,column,image;
    ShapeableImageView imageButton;

    public boolean sameColum(MatchingObject anotherObject){
            return this.column == anotherObject.getColumn();
    };
    public boolean sameRow(MatchingObject anotherObject){
            return this.row == anotherObject.getRow();
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
    }

    public MatchingObject() {

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

    public ShapeableImageView getImageButton() {
        return imageButton;
    }

    public void setImageButton(ShapeableImageView imageButton) {
        this.imageButton = imageButton;
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
    }
    public static final Parcelable.Creator<MatchingObject> CREATOR = new Parcelable.Creator<MatchingObject>() {
        public MatchingObject createFromParcel(Parcel in) {
            return new MatchingObject(in);
        }
        public MatchingObject[] newArray(int size) {
            return new MatchingObject[size];
        }
    };
}
