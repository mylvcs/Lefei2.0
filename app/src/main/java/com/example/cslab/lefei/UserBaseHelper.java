package com.example.cslab.lefei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PageRange;

import com.example.cslab.lefei.UserDbSchema.UserTable;

import java.security.PublicKey;

/**
 * Created by CSLab on 2018/1/15.
 */

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME= "userBase.db";

    public UserBaseHelper (Context context){
        super(context,DATABASE_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase Userdb)  {
        Userdb.execSQL("create table"+ UserTable.NAME+"(" +"_id integer primary key autoincrement," +
                UserTable.Cols.UserID+", "+UserTable.Cols.UserName+", "+ UserTable.Cols.UserPassword+", "+
        UserTable.Cols.UserTicket+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Userdb, int oldVersion, int newVersion) {

    }

}
