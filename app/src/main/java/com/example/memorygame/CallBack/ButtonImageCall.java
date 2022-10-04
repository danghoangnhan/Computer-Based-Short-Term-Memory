package com.example.memorygame.CallBack;

import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;

public interface ButtonImageCall {
    void HandleSelected(ImageRecycleViewObject image, MatchingObject matchingObject);
    void HandleUnSelected(ImageRecycleViewObject image, MatchingObject matchingObject);
}
