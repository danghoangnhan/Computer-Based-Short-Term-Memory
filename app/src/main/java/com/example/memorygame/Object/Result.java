package com.example.memorygame.Object;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.List;

public class Result   {
    private List<MatchingObject>selected1,selected2,selected3, correct;


    public Result(List<MatchingObject> correct) {
        this.correct = correct;
    }

    public Result() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long ObjectValidation(){
        return this.selected1
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long LocationValidation(){
        return  this.selected2
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long ObjectLocationValidation(){
        return  this.selected2
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .count();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Long ObjectCorlorLocationValidation(){
        return  this.selected3
                .stream()
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameColum(element)&&checkelement.sameRow(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameObject(element)))
                .filter(element-> this.correct.stream().anyMatch(checkelement->checkelement.sameCorlor(element)))
                .count();
    }

    public List<MatchingObject> getCorrect() {
        return correct;
    }

    public List<MatchingObject> getSelected1() {return selected1;}

    public void setSelected1(List<MatchingObject> selected1) {this.selected1 = selected1;}

    public List<MatchingObject> getSelected2() {return selected2;}

    public void setSelected2(List<MatchingObject> selected2) {
        this.selected2 = selected2;
    }

    public List<MatchingObject> getSelected3() {return selected3;}

    public void setSelected3(List<MatchingObject> selected3) {this.selected3 = selected3;}

    public void setCorrect(List<MatchingObject> correct) {
        this.correct = correct;
    }
}
