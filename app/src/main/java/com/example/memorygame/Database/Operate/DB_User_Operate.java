                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                package com.example.memorygame.Database.Operate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Entity.User;

import java.util.ArrayList;
import java.util.List;
public class DB_User_Operate   {
    private SQLiteDatabase sqLiteDatabase;

    public DB_User_Operate(){
    }

    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table users(" +
                "name TEXT primary key," +
                "age int," +
                "gender varchar(5)," +
                "educationYears varchar(100)," +
                "isworking boolean," +
                "lastLoginTime Text)");
    }
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }
    public Boolean insertData(@NonNull User user,@NonNull SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());
        contentValues.put("gender",user.getSex());
        contentValues.put("educationYears",user.getEducationLevel());
        contentValues.put("isWorking",user.getWorking());
        contentValues.put("lastLoginTime",user.getLastedLoginTime().toString());
        long result = sqLiteDatabase.insert("users", null, contentValues);
        return result != -1;
    }
    public Boolean checkNameExist(@NonNull User user,@NonNull SQLiteDatabase sqLiteDatabase) {
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where name = ?", new String[]{user.getName()});
        return cursor.getCount() > 0;
    }
    public List<User> getdata(@NonNull SQLiteDatabase sqLiteDatabase)
    {
        List<User> result = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor  = sqLiteDatabase.rawQuery("Select * from users", null);
        while(cursor.moveToNext())
        {
            result.add(new User(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)));

        }
        return result;
    }

}