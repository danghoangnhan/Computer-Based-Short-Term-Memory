package com.example.memorygame.Object;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.Contract;

public class ImageRecycleViewObject implements Parcelable {
    private Integer imageId;
    private Boolean isSelected;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public ImageRecycleViewObject(@NonNull Parcel in) {
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

    public static final Creator<ImageRecycleViewObject> CREATOR = new Creator<>() {
        @NonNull
        @Contract("_ -> new")
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public ImageRecycleViewObject createFromParcel(Parcel in) {
            return new ImageRecycleViewObject(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
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
        return isSelected.booleanValue();
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

    public int compareTo(@NonNull ImageRecycleViewObject image) {
        return this.imageId.compareTo(image.imageId);
    }
}
