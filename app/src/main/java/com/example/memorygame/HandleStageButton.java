package com.example.memorygame;

import android.view.View;
import android.widget.Button;

public interface HandleStageButton {
    Button nextButton=null,escButton=null,replayButton=null;
    public void handleNextButton(View view);
    public void handleReplayButton(View view);
    public void handleEscButton(View view);
}
