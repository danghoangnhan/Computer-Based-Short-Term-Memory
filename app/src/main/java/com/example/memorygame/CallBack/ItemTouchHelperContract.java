package com.example.memorygame.CallBack;

import com.example.memorygame.Adapter.RecyclerViewAdapter;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

public interface ItemTouchHelperContract {
        void onRowMoved(int fromPosition, int toPosition);
        void onRowSelected(RecycleViewHolder myViewHolder);
        void onRowClear(RecycleViewHolder myViewHolder);
}
