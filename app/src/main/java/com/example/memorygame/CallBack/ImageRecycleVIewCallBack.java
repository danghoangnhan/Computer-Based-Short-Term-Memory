package com.example.memorygame.CallBack;

import com.example.memorygame.Object.ImageRecycleViewObject;

public interface ImageRecycleVIewCallBack {
    void handleImageRecycleView(Integer tmpClickedImage);
    void HandleSelected(ImageRecycleViewObject Position);
    void HandleUnSelected(ImageRecycleViewObject Position);
}
