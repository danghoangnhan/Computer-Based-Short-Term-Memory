package com.example.memorygame.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Adapter.SessionAdapter;
import com.example.memorygame.Database.DB_Instance;
import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.Database.Operate.DB_Session_Operate;
import com.example.memorygame.FixedGridLayoutManager;
import com.example.memorygame.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SessionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Session> SessionList;
    DB_Instance DB;
    SessionAdapter sessionAdapter;
    HorizontalScrollView headerScroll;
    Button exit,exportCsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        initViews();
        DB = new DB_Instance(this);
        setUpRecyclerView();
        exportDB();
        handleRegisterButton();
    }
    public void handleRegisterButton(){
        this.exit = findViewById(R.id.exitButton);
        this.exit.setOnClickListener(view1 -> {exit();});
    }
    public void exit(){
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
    }


    private void initViews() {
        this.recyclerView = findViewById(R.id.session_recyclerview);
        this.headerScroll = findViewById(R.id.headerScroll);
    }
    private void setUpRecyclerView()
    {
        this.SessionList = this.DB.getSessionData();
        this.sessionAdapter = new SessionAdapter(SessionActivity.this, (ArrayList<Session>) this.SessionList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(SessionActivity.this,LinearLayoutManager.VERTICAL,false));
        this.recyclerView.setAdapter(this.sessionAdapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(SessionActivity.this, DividerItemDecoration.VERTICAL));
    }
    private void exportDB() {

        this.exportCsv = findViewById(R.id.exportcsvButton);
        this.exportCsv.setOnClickListener(view -> {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);}
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis());
            String[] title ={"Id","姓氏","性別","年齡","教育年數","工作狀態","最新登入時間"};
            String fileName = "[" + date + "]碼農日常輸出的.csv";

            StringBuffer csvText = new StringBuffer();
            for (String s : title) {
                csvText.append(s).append(",");
            }
            for (int i = 0; i <this.SessionList.size(); i++) {
                csvText.append("\n").append(i + 1);
                csvText.append(this.SessionList.get(i).getEndRound());
                //此處巢狀迴圈為設置每一列的內容
            }
            runOnUiThread(() -> {
                try {
                    FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
                    out.write((csvText.toString().getBytes()));
                    out.close();
                    File fileLocation = new File(Environment.
                            getExternalStorageDirectory().getAbsolutePath(), fileName);
                    FileOutputStream fos = new FileOutputStream(fileLocation);
                    fos.write(csvText.toString().getBytes());
                    Uri path = Uri.fromFile(fileLocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, fileName);
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "輸出檔案"));
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w(TAG, "makeCSV: "+ e);
                }
            });
        });
    }
}