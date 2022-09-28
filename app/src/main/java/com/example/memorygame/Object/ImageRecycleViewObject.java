package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageRecycleViewObject implements Parcelable {
    private int imageId;
    private boolean isSelected;

    public ImageRecycleViewObject(Parcel in) {
        imageId = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<ImageRecycleViewObject> CREATOR = new Creator<ImageRecycleViewObject>() {
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

    public int getImageId() {return imageId;}

    public void setImageId(int imageId) {this.imageId = imageId;}

    public boolean isSelected() {return isSelected;}

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
