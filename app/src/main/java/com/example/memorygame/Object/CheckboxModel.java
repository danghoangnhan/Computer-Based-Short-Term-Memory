package com.example.memorygame.Object;

public class CheckboxModel {
        String name;
        int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

        public CheckboxModel(String name, int value){
            this.name = name;
            this.value = value;
        }
        public String getName(){
            return this.name;
        }
        public int getValue(){
            return this.value;
        }
}
