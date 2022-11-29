package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.SessionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder>   {

    private final Context context;
    private final ArrayList<Session> sessionList;
    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    public SessionAdapter(Context context, ArrayList<Session> sessionList) {
        this.context = context;
        this.sessionList = sessionList;
    }
    @Override
    public int getItemViewType(int position)
    {
        if (position % 2 == 0)
        {
            return TYPE_ROW_COLORFUL;
        }

        return TYPE_ROW;
    }


    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROW)
        {
            View view = LayoutInflater.from(this.context).inflate(R.layout.session_item, parent, false);
            return new SessionViewHolder(view);
        } else
        {
            View view = LayoutInflater.from(this.context).inflate(R.layout.session_item, parent, false);
            return new SessionViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        final Session item = this.sessionList.get(position);

        holder.getUsername().setText(item.getUser().getName());
        holder.getAd8score().setText(item.getAD8_Score().toString());
        holder.getAd8starttime().setText(item.getStartAD8Time().toString());
        holder.getAd8endtime().setText(item.getEndAD8Time().toString());
        holder.getGamescore().setText(item.getGameScore().toString());
        holder.getStartgametime().setText(item.getStartRound().toString());
        holder.getEndgametime().setText(item.getEndRound().toString());

    }

    @Override
    public int getItemCount() {
        return this.sessionList.size();
    }

}
