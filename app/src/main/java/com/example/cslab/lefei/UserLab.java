package com.example.cslab.lefei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.usb.UsbRequest;
import android.icu.lang.UScript;
import android.net.LinkAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.UserDbSchema.User;
import database.UserDbSchema.UserBaseHelper;
import database.UserDbSchema.UserCursorWrapper;
import database.UserDbSchema.UserDbSchema;

/**
 * Created by CSLab on 2018/1/15.
 */

public class UserLab {
    private static UserLab sUserLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static UserLab get(Context context) {
        if (sUserLab == null) {
            sUserLab = new UserLab(context);
        }
        return sUserLab;
    }

    private UserLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserBaseHelper(mContext).getWritableDatabase();
    }

    public void addUser(User u) {
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    public List<User> getUsers() {
        List<User> users= new ArrayList<>();
        UserCursorWrapper cursor= queryUsers(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return users;
    }

    public User getUser(UUID UserId) {
       UserCursorWrapper cursor = queryUsers(
               UserDbSchema.UserTable.Cols.UserID + " =?",
               new String [] {UserId.toString()}
       );
        try {
            if(cursor.getCount()== 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        }
        finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UserID, user.getUserId().toString());
        values.put(UserDbSchema.UserTable.Cols.UserName, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.UserPassword, user.getUserPassword());
        return values;
    }

    public void updateUser(User user) {
        String UserId = user.getUserId().toString();
        ContentValues values = getContentValues(user);
        mDatabase.update(UserDbSchema.UserTable.NAME, values, UserDbSchema.UserTable.Cols.UserID + "=?",
                new String[]{UserId});
    }
    private UserCursorWrapper queryUsers (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                UserDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UserCursorWrapper(cursor);
    }

}