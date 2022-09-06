package com.example.memorygame.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.*;

import com.example.memorygame.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class DragDropActivity extends AppCompatActivity {
    View dragView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);
        dragView = findViewById(R.id.dragview);
        dragView.setOnLongClickListener(longClickListener);
    }
    OnLongClickListener longClickListener = new  OnLongClickListener(){
        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("","");
            DragShadowBuilder myShadowBuilder = new DragShadowBuilder(view);
            view.startDrag(data,myShadowBuilder,view,0);
            return false;
        }
    };
    OnDragListener dragListener = new OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int dragEventActiont = dragEvent.getAction();
            switch (dragEventActiont){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View localView = (View) dragEvent.getLocalState();
                    if (localView.getId() == R.id.dragview){
                        dragView.setTooltipText("drag");
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };
}