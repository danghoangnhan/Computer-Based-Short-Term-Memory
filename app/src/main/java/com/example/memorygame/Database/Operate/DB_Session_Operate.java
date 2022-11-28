package com.example.memorygame.Database.Operate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.Database.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class DB_Session_Operate extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DB_Session_Operate(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("create Table sessions(" +
                "id int  primary key," +
                "user_name  TEXT," +
                "game_score int(100)," +
                "ad8_score  int(100)," +
                "game_startTime  TEXT," +
                "game_endTime TEXT," +
                "ad8_startTime TEXT," +
                "ad8_endTime TEXT)");
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists sessions");
    }
    public Boolean insertData(@NonNull Session session){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("user_name", session.getUser().getName());
        contentValues.put("game_score", session.getGameScore());
        contentValues.put("ad8_score", session.getAD8_Score());
        contentValues.put("game_startTime", session.getStartRound().toString());
        contentValues.put("game_endTime", session.getEndRound().toString());
        contentValues.put("ad8_startTime", session.getStartAD8Time().toString());
        contentValues.put("ad8_endTime", session.getEndAD8Time().toString());
        long result = MyDB.insert("session", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public List<Session> getdata()
    {
        List<Session> result = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from sessions", null);
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
