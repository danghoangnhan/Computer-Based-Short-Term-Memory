package com.example.memorygame.CallBack;

import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;

public interface CorlorRecycleViewCallBack {
    void HandleSelected(CorlorRecycleViewObject target);
    void HandleUnSelected(CorlorRecycleViewObject target);
}
