package com.example.memorygame.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.R;
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
        View v = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User item = this.userList.get(position);
        holder.getName_id().setText(item.getName());
        holder.getGender().setText(item.getSex());
        holder.getAge_id().setText(String.valueOf(item.getAge()));
    }


    @Override
    public int getItemCount() {
        return this.userList.size();
    }
}
