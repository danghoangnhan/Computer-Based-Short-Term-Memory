package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class CorlorRecycleViewObject implements Parcelable {
    private int corlorId;
    private boolean isSelected;

    public CorlorRecycleViewObject(@NonNull Parcel in) {
        corlorId = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<CorlorRecycleViewObject> CREATOR = new Creator<CorlorRecycleViewObject>() {
        @NonNull
        @Contract("_ -> new")
        @Override
        public CorlorRecycleViewObject createFromParcel(Parcel in) {
            return new CorlorRecycleViewObject(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public CorlorRecycleViewObject[] newArray(int size) {
            return new CorlorRecycleViewObject[size];
        }
    };

    public CorlorRecycleViewObject(boolean isSelected,int corlorId) {
        setSelected(isSelected);
        setCorlorId(corlorId);
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(corlorId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
    public boolean compareTo(@NonNull CorlorRecycleViewObject image) {
        return this.corlorId==image.corlorId;
    }
}
