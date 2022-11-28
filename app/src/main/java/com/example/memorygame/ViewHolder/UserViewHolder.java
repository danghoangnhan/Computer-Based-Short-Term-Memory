package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView name, gender, age,educationyear,workingstatus,lastLogin;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name    = itemView.findViewById(R.id.name);
        this.gender     = itemView.findViewById(R.id.sex);
        this.age     = itemView.findViewById(R.id.age);
        this.educationyear = itemView.findViewById(R.id.session_educationyear);
        this.workingstatus = itemView.findViewById(R.id.workingstatus);
        this.lastLogin = itemView.findViewById(R.id.lastime_login);
    }
    public TextView getGender() {
        return gender;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setGender(TextView gender) {
        this.gender = gender;
    }

    public TextView getAge() {
        return age;
    }

    public void setAge(TextView age) {
        this.age = age;
    }

    public TextView getEducationyear() {
        return this.educationyear;
    }

    public void setEducationyear(TextView educationyear) {
        this.educationyear = educationyear;
    }

    public TextView getWorkingstatus() {
        return workingstatus;
    }

    public void setWorkingstatus(TextView workingstatus) {
        this.workingstatus = workingstatus;
    }

    public TextView getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(TextView lastLogin) {
        this.lastLogin = lastLogin;
    }
}