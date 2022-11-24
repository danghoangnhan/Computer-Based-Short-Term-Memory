                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                package com.example.memorygame.Database.Operate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.memorygame.Database.Model.User;

import java.util.ArrayList;
import java.util.List;

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
    public Boolean checkNameExist(@NonNull User user) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?", new String[]{user.getName()});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public List<User> getdata()
    {
        List<User> result = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from users", null);
        while(cursor.moveToNext())
        {
            result.add(new User(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)));

        }
        return result;
    }
    public Boolean deleteuserdata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from users where name = ?", new String[]{name});
        if(cursor.getCount()>0)
        {
            return DB.delete("users", "name=?", new String[]{name}) !=-1 ;
        }
        else
        {
            return false;
        }

    }
}