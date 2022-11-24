package com.example.memorygame;

import android.view.View;
import android.widget.Button;

public interface HandleStageButton {
    void handleNextButton(View view);
    void handleReplayButton(View view);
    void handleEscButton(View view);
}
