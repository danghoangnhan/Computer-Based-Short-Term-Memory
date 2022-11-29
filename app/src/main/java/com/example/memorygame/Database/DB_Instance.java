package com.example.memorygame.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.Database.Entity.User;
import com.example.memorygame.Database.Operate.DB_Session_Operate;
import com.example.memorygame.Database.Operate.DB_User_Operate;

import java.util.List;

public class DB_Instance extends SQLiteOpenHelper {
    private Context context;
    private DB_User_Operate db_user_operate;
    private DB_Session_Operate db_session_operate;

    public DB_Instance(Context context){
        super(context, "db.login", null, 1);
        getInstance();
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        getInstance();
        db_session_operate.onCreate(sqLiteDatabase);
        db_user_operate.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        getInstance();
        this.db_user_operate.onUpgrade(sqLiteDatabase);
        this.db_session_operate.onUpgrade(sqLiteDatabase);
    }

    public DB_User_Operate getDb_user_operate() {
        return db_user_operate;
    }

    public DB_Session_Operate getDb_session_operate() {
        return db_session_operate;
    }

    public  void getInstance(){
        if(this.db_user_operate==null){
            this.db_user_operate = new DB_User_Operate();
        }
        if (this.db_session_operate==null){
            this.db_session_operate = new DB_Session_Operate();
        }
    }

    public boolean checkNameExist(User user) {return this.db_user_operate.checkNameExist(user,this.getWritableDatabase());}

    public List<Session> getSessionData() {return this.getDb_session_operate().getdata(this.getWritableDatabase());}

    public List<User> getUserData() {return this.getDb_user_operate().getdata(this.getWritableDatabase());}

    public boolean insertData(User user) {
        return  this.db_user_operate.insertData(user,this.getWritableDatabase());
    }
    public boolean insertData(Session session) {
       return this.db_session_operate.insertData(session,this.getWritableDatabase());
    }
}
