package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.GlobalObject;
import com.example.memorygame.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class LoginActivity extends AppCompatActivity {
     FloatingActionsMenu menuMultipleActions  ;
     FloatingActionButton action_user_data    ;
     FloatingActionButton action_session_data  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         menuMultipleActions   = (FloatingActionsMenu) findViewById(R.id.multiple_actions_left);
         action_user_data     = (FloatingActionButton) findViewById(R.id.user_data);
         action_session_data  = (FloatingActionButton) findViewById(R.id.session_data);

        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(view -> changeActivity());
        GlobalObject globalObject = GlobalObject.getInstance();
        globalObject.setGameState(1);
        action_session_data.setOnClickListener(this::handleSession_data);
        action_user_data.setOnClickListener(this::handleUser_data);
    }
    private void changeActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void handleUser_data(View view ){
        Intent intent = new Intent(this,AuthenticationActivity.class);
        this.startActivity(intent);
    }
    private void handleSession_data(View view ){
        Intent intent = new Intent(this, SessionActivity.class);
        this.startActivity(intent);
    }

}