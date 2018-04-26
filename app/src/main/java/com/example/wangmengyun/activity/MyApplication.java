package com.example.wangmengyun.activity;

import android.app.Application;
import android.content.Context;

import com.example.wangmengyun.database.GreenDaoManager;

/**
 * Created by wangmengyun on 2018/4/26.
 */

public class MyApplication extends Application {

    public static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        GreenDaoManager.getInstance();
    }
}
