package com.example.memorygame.CallBack;

import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;

public interface ImageRecycleVIewCallBack {
    void HandleSelected(ImageRecycleViewObject Position);
    void HandleUnSelected(ImageRecycleViewObject Position);
}
