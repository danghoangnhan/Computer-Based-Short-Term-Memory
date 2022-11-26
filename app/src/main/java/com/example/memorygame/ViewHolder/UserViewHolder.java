package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView name_id, gender, age_id;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name_id = itemView.findViewById(R.id.textname);
        this.gender = itemView.findViewById(R.id.sex);
        this.age_id = itemView.findViewById(R.id.textage);
    }

    public TextView getName_id() {
        return name_id;
    }

    public void setName_id(TextView name_id) {
        this.name_id = name_id;
    }

    public TextView getAge_id() {
        return age_id;
    }

    public void setAge_id(TextView age_id) {
        this.age_id = age_id;
    }

    public TextView getGender() {
        return gender;
    }

    public void setGender(TextView gender) {
        this.gender = gender;
    }
}