package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

public class CorlorRecycleViewObject implements Parcelable {
    private int corlorId;
    private boolean isSelected;

    public CorlorRecycleViewObject(Parcel in) {
        corlorId = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<CorlorRecycleViewObject> CREATOR = new Creator<CorlorRecycleViewObject>() {
        @Override
        public CorlorRecycleViewObject createFromParcel(Parcel in) {
            return new CorlorRecycleViewObject(in);
        }

        @Override
        public CorlorRecycleViewObject[] newArray(int size) {
            return new CorlorRecycleViewObject[size];
        }
    };

    public CorlorRecycleViewObject() {
        setSelected(false);
    }



    public boolean isSelected() {return isSelected;}

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCorlorId() {
        return corlorId;
    }

    public void setCorlorId(int corlorId) {
        this.corlorId = corlorId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(corlorId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
