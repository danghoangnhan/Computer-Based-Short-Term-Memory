package com.example.memorygame.utilities;

import com.example.memorygame.ViewHolder.CorlorViewHolder;
import com.example.memorygame.ViewHolder.RecycleViewHolder;

public interface ItemTouchHelperContract {
        void onRowMoved(int fromPosition, int toPosition);
        void onRowSelected(CorlorViewHolder myViewHolder);
        void onRowClear(CorlorViewHolder myViewHolder);
}
