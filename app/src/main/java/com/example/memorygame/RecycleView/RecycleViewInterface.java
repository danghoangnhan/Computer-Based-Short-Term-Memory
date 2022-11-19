package com.example.memorygame.RecycleView;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.memorygame.Object.ImageRecycleViewObject;

public interface RecycleViewInterface {
    void onItemClick(View view, int Position);

    void HandleUnSelected(@NonNull ImageRecycleViewObject target);
}
