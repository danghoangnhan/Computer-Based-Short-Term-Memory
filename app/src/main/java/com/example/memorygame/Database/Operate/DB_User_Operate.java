                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                package com.example.memorygame.Database.Operate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Model.User;

public class DB_User_Operate extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DB_User_Operate(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(" +
                "name TEXT primary key," +
                "age int," +
                "gender varchar(5)," +
                "educationYears int(100)," +
                "isworking boolean)");
    }
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }
    public Boolean insertData(@NonNull User user){
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
    public Boolean checkNameExist(User user) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?", new String[]{user.getName()});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}