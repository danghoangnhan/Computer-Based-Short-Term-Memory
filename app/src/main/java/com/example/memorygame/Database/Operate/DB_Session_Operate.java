package com.example.memorygame.Database.Operate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Entity.Session;

import java.util.ArrayList;
import java.util.List;

public class DB_Session_Operate   {

    public DB_Session_Operate() {
    }

    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table sessions(" +
                "id int  primary key," +
                "user_name  TEXT," +
                "game_score int(100)," +
                "ad8_score  int(100)," +
                "game_startTime  TEXT," +
                "game_endTime TEXT," +
                "ad8_startTime TEXT," +
                "ad8_endTime TEXT)");
    }

    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase) {sqLiteDatabase.execSQL("drop Table if exists sessions");}

    public Boolean insertData(@NonNull Session session,@NonNull SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues();
        contentValues.put("user_name", session.getUser().getName());
        contentValues.put("game_score", session.getGameScore());
        contentValues.put("ad8_score", session.getAD8_Score());
        contentValues.put("game_startTime", session.getStartRound().toString());
        contentValues.put("game_endTime", session.getEndRound().toString());
        contentValues.put("ad8_startTime", session.getStartAD8Time().toString());
        contentValues.put("ad8_endTime", session.getEndAD8Time().toString());
        long result = sqLiteDatabase.insert("sessions", null, contentValues);
        return result != -1;
    }
    public List<Session> getdata(@NonNull SQLiteDatabase sqLiteDatabase)
    {
        List<Session> result = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor  = sqLiteDatabase.rawQuery("Select * from sessions", null);
        while(cursor.moveToNext())
        {
            result.add(new Session(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)));
        }
        return result;
    }
}
