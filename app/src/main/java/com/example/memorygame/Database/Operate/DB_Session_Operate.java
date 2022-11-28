package com.example.memorygame.Database.Operate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Entity.Session;

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
                "name TEXT primary key," +
                "age int," +
                "gender varchar(5)," +
                "educationYears int(100)," +
                "isworking boolean)");
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists sessions");
    }
    public Boolean insertData(@NonNull Session session){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());
        contentValues.put("gender",user.getSex());
        contentValues.put("educationYears",user.getEducationLevel());
        contentValues.put("isWorking",user.getWorking());
        long result = MyDB.insert("users", null, contentValues);
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
            result.add(new Session(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)));

        }
        return result;
    }
}
