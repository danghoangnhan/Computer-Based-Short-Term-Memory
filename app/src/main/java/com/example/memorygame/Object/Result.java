package com.example.memorygame.Object;


import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Predicate;

public class Result   {
    private List<MatchingObject>selected1,selected2,selected3, correct;
    private Integer result1,result2,result3;

    public Result() {

    }
    Predicate<MatchingObject>   containObject       =       element-> this.correct.stream().anyMatch(pattern->pattern.sameObject(element));
    Predicate<MatchingObject>   containRow          =       element-> this.correct.stream().anyMatch(pattern->pattern.sameRow(element));
    Predicate<MatchingObject>   containColumn       =       element-> this.correct.stream().anyMatch(pattern->pattern.sameColum(element));
    Predicate<MatchingObject>   containColor        =       element-> this.correct.stream().anyMatch(pattern->pattern.sameCorlor(element));

    public long ObjectValidation(@NonNull List<MatchingObject> selected){return selected.stream().filter(containObject).count();}
    public long SpatialValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containColumn.and(containRow)).count();}
    public long ObjectSpatialValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containObject.and(containColumn).and(containRow)).count();}
    public long CorlorValidation(@NonNull List<MatchingObject> selected){
        return  selected.stream().filter(containColor).count();
    }
    public long ObjectCorlorLocationValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containObject.and(containColor).and(containColumn).and(containRow)).count();}

    public List<MatchingObject> getCorrect() {
        return correct;
    }

    public void setSelected1(List<MatchingObject> selected1) {this.selected1 = selected1;}

    public List<MatchingObject> getSelected2() {return selected2;}

    public void setSelected2(List<MatchingObject> selected2) {
        this.selected2 = selected2;
    }

    public void setSelected3(List<MatchingObject> selected3) {this.selected3 = selected3;}

    public void setCorrect(List<MatchingObject> correct) {
        this.correct = correct;
    }

    public List<MatchingObject> getSelected1() {return selected1;}

    public List<MatchingObject> getSelected3() {return selected3;}

    public Integer getResult1() {
        return result1;
    }

    public void setResult1(Integer result1) {
        this.result1 = result1;
    }

    public Integer getResult2() {
        return result2;
    }

    public void setResult2(Integer result2) {
        this.result2 = result2;
    }

    public Integer getResult3() {
        return result3;
    }

    public void setResult3(Integer result3) {
        this.result3 = result3;
    }
}
