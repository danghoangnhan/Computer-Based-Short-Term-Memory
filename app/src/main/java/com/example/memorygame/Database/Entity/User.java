package com.example.memorygame.Database.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class User implements Serializable {
    UUID id;
    String Name; //姓氏
    String sex;// 、性別、
    Integer age; // 年齡、
    String educationLevel;// 教育程度、
    Boolean isWorking;// 是否工作中
    Date lastedLoginTime;

    public User(String Name, int age, String sex, String educationLevel, int isWorking,String lasLogin) {
        this.Name = Name;
        this.age = age;
        this.sex = sex;
        this.educationLevel = educationLevel;
        this.isWorking = isWorking==1;
        this.lastedLoginTime = new Date(lasLogin);
    }

    public User() {

    }

    public void generateUUID(){
        this.id = UUID.randomUUID();
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
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getLastedLoginTime() {
        return lastedLoginTime;
    }

    public void setLastedLoginTime(Date lastedLoginTime) {
        this.lastedLoginTime = lastedLoginTime;
    }
}
