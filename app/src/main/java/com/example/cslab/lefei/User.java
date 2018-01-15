package com.example.cslab.lefei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by CSLab on 2018/1/15.
 */

public class User {
    private static User mUser;
    private List<User> mUsers;
    private Context mContext;

    private SQLiteDatabase mDatabase;

    private User(Context context){
        mContext= context.getApplicationContext();
        mDatabase= new UserBaseHelper(mContext).getWritableDatabase();

    }
}
