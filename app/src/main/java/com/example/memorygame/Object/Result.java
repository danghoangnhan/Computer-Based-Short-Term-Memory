package com.example.memorygame.Object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Result implements Parcelable {
    private List<MatchingObject>selected, correct;

    protected Result(Parcel in) {
        selected = in.createTypedArrayList(MatchingObject.CREATOR);
        correct = in.createTypedArrayList(MatchingObject.CREATOR);
    }
    public Result(List<MatchingObject> selected, List<MatchingObject> correct) {
        this.selected = selected;
        this.correct = correct;
    }

    public static Integer ObjectValidation(ArrayList<MatchingObject>selected, ArrayList<MatchingObject> correct){
        return 0;
    }
    public static Integer LocationValidation(ArrayList<MatchingObject>selected,ArrayList<MatchingObject> correct){
        return 0;
    }
    public Integer ObjectValidation(){
        return 0;
    }
    public static Integer LocationValidation(){
        return 0;
    }
    public static Integer ObjectLocationValidation(){
        return 0;
    }
    public static Integer ColorValidation(){
        return 0;
    }

    public List<MatchingObject> getSelected() {
        return selected;
    }

    public void setSelected(ArrayList<MatchingObject> selected) {
        this.selected = selected;
    }

    public List<MatchingObject> getCorrect() {
        return correct;
    }

    public void setCorrect(ArrayList<MatchingObject> correct) {
        this.correct = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(selected);
        parcel.writeTypedList(correct);
    }
    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
