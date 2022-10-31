package com.example.memorygame.Object;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class ImageRecycleViewObject implements Parcelable {
    private Integer imageId;
    private Boolean isSelected;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public ImageRecycleViewObject(Parcel in) {
        if (in.readByte() == 0) {
            imageId = null;
        } else {
            imageId = in.readInt();
        }
        if (in.readByte() == 0) {
            isSelected = null;
        } else {
            isSelected = in.readBoolean();
        }
    }

    public static final Creator<ImageRecycleViewObject> CREATOR = new Creator<ImageRecycleViewObject>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public ImageRecycleViewObject createFromParcel(Parcel in) {
            return new ImageRecycleViewObject(in);
        }

        @Override
        public ImageRecycleViewObject[] newArray(int size) {
            return new ImageRecycleViewObject[size];
        }
    };

    public ImageRecycleViewObject() {
        setSelected(false);
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (imageId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(imageId);
        }
        if (isSelected == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeBoolean(isSelected);
        }
    }
}
