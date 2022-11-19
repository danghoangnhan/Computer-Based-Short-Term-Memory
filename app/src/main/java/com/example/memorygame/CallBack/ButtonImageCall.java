package com.example.memorygame.CallBack;

import com.example.memorygame.Object.ImageRecycleViewObject;
import com.example.memorygame.Object.MatchingObject;

public interface ButtonImageCall {
    void HandleSelected(Integer viewId,ImageRecycleViewObject image, MatchingObject matchingObject);
    void HandleUnSelected(Integer viewId, ImageRecycleViewObject image, MatchingObject matchingObject);
    void HandleUpdated(Integer viewId,
                       ImageRecycleViewObject image,
                       MatchingObject matchingObject);
}
