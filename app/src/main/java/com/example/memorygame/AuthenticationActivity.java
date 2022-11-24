package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.memorygame.Adapter.UserAdapter;
import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Database.Operate.DB_User_Operate;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<User> userList;
    DB_User_Operate DB;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        DB = new DB_User_Operate(this);
        recyclerView = findViewById(R.id.recyclerview);
        userAdapter = new UserAdapter(this, (ArrayList<User>) userList);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {
        userList = DB.getdata();
        if(userList.size()==0)
        {
            Toast.makeText(AuthenticationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {

        }
    }
}
}