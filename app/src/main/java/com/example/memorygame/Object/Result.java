package com.example.memorygame.Object;


import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Predicate;

public class Result   {
    private List<MatchingObject>selected1,selected2,selected3, correct;


    public Result() {

    }
    Predicate<MatchingObject>   containObject       =       element-> this.correct.stream().anyMatch(MatchingObject::sameObject);
    Predicate<MatchingObject>   containRow          =       element-> this.correct.stream().anyMatch(MatchingObject::sameRow);
    Predicate<MatchingObject>   containColumn       =       element-> this.correct.stream().anyMatch(MatchingObject::sameColum);
    Predicate<MatchingObject>   containColor        =       element-> this.correct.stream().anyMatch(MatchingObject::sameCorlor);

    public long ObjectValidation(@NonNull List<MatchingObject> selected){return selected.stream().filter(containObject).count();}
    public long SpatialValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containColumn.and(containRow)).count();}
    public long ObjectSpatialValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containObject.and(containColumn).and(containRow)).count();}
    public long CorlorValidation(@NonNull List<MatchingObject> selected){return  selected.stream().filter(containColor).count();}
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
}
