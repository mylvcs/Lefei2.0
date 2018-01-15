package com.example.cslab.lefei;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return new ArrayList<>();
    }

    public User getUser(UUID UserId) {
        return null;
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

}