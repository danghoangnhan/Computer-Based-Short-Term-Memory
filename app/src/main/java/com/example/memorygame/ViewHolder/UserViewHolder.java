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
}