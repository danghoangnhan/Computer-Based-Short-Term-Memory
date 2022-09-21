package com.example.memorygame.Object;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long ObjectValidation(){
        return this.selected
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long LocationValidation(){
        return  this.selected
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long ObjectLocationValidation(){
        return  this.selected
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Long ObjectCorlorLocationValidation(){
        return  this.selected
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameCorlor(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public  Long ColorValidation(){
        return this.selected
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameCorlor(element)))
                .count();
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
