package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.UserAdapter;
import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Database.Operate.DB_User_Operate;
import com.example.memorygame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<User> userList;
    DB_User_Operate DB;
    UserAdapter userAdapter;
    FloatingActionButton Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        DB = new DB_User_Operate(this);
        displaydata();
        Register = findViewById(R.id.addingBtn);
        Register.setOnClickListener(this::handleRegisterButton);
        initialRecyleView();
    }

    private void initialRecyleView() {
        this.recyclerView = findViewById(R.id.authentication_recyclerview);
        this.userAdapter = new UserAdapter(this, (ArrayList<User>) userList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(AuthenticationActivity.this,LinearLayoutManager.VERTICAL,false));
        this.recyclerView.setAdapter(this.userAdapter);
    }

    public void handleRegisterButton(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        this.startActivity(intent);
    }

    private void displaydata()
    {
        userList = DB.getdata();
        if(userList.size()==0)
        {
            Toast.makeText(AuthenticationActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }

    }
}