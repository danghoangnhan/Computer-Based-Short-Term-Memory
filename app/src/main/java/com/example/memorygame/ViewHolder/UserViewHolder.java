package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name_id, email_id, age_id;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        name_id = itemView.findViewById(R.id.textname);
        email_id = itemView.findViewById(R.id.textemail);
        age_id = itemView.findViewById(R.id.textage);
    }

    public TextView getName_id() {
        return name_id;
    }

    public void setName_id(TextView name_id) {
        this.name_id = name_id;
    }

    public TextView getEmail_id() {
        return email_id;
    }

    public void setEmail_id(TextView email_id) {
        this.email_id = email_id;
    }

    public TextView getAge_id() {
        return age_id;
    }

    public void setAge_id(TextView age_id) {
        this.age_id = age_id;
    }
}