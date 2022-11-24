package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Database.Model.User;
import com.example.memorygame.ViewHolder.UserViewHolder;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>
        implements View.OnTouchListener{
    private Context context;
    private ArrayList<User> userList;


    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
