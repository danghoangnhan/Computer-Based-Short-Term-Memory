package com.example.memorygame.Database.Entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    String Name; //姓氏
    String sex;// 、性別、
    Integer age; // 年齡、
    Integer educationLevel;// 教育程度、
    Boolean isWorking;// 是否工作中
    Date lastedLoginTime;

    public User(String Name, int age, String sex, int educationLevel, int isWorking) {
        this.Name = Name;
        this.age = age;
        this.sex = sex;
        this.educationLevel = educationLevel;
        this.isWorking = isWorking==1;

    }

    public User() {

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = Integer.parseInt(educationLevel);
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }
}
