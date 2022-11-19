package com.example.memorygame.CallBack;

import com.example.memorygame.Object.CorlorRecycleViewObject;
import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;

public interface ButtonCorlorCall {
    void HandleSelected(Integer viewId, CorlorRecycleViewObject image, MatchingObject matchingObject);
    void HandleUnSelected(Integer viewId, CorlorRecycleViewObject image, MatchingObject matchingObject);
}
