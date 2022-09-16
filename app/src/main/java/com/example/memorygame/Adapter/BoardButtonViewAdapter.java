package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Object.MatchingObject;
import com.example.memorygame.RecycleView.RecycleViewInterface;
import com.example.memorygame.ViewHolder.BoardButtonHolder;

import java.util.List;

public class BoardButtonViewAdapter extends RecyclerView.Adapter<BoardButtonHolder> {
    List<MatchingObject> data;
    Context context;
    public BoardButtonViewAdapter(Context context, List<MatchingObject> data, RecycleViewInterface recycleViewInterface){
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public BoardButtonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BoardButtonHolder boardButtonHolder = new BoardButtonHolder(null,null,null);
        return boardButtonHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardButtonHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 0;
    }
}
