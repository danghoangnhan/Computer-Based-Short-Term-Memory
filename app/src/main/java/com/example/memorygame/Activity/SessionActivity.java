package com.example.memorygame.Activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.SessionAdapter;
import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.Database.Operate.DB_Session_Operate;
import com.example.memorygame.Database.Operate.DB_User_Operate;
import com.example.memorygame.FixedGridLayoutManager;
import com.example.memorygame.R;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends AppCompatActivity {
    int scrollX = 0;
    RecyclerView recyclerView;
    List<Session> SessionList;
    DB_Session_Operate DB;
    SessionAdapter sessionAdapter;
    HorizontalScrollView headerScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        initViews();
        DB = new DB_Session_Operate(this);
        setUpRecyclerView();
    }

    private void initViews() {
        this.recyclerView = findViewById(R.id.rvClub);
        this.headerScroll = findViewById(R.id.headerScroll);
    }
    private void setUpRecyclerView()
    {
        this.SessionList = this.DB.getdata();
        this.sessionAdapter = new SessionAdapter(SessionActivity.this, (ArrayList<Session>) this.SessionList);
        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(this.sessionAdapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(SessionActivity.this, DividerItemDecoration.VERTICAL));
    }
}