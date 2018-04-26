package com.example.wangmengyun.database;

import com.example.wangmengyun.activity.MyApplication;
import com.example.wangmengyun.gen.DaoMaster;
import com.example.wangmengyun.gen.DaoSession;

/**
 * Created by wangmengyun on 2018/4/26.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;

    private static DaoMaster mDaoMaster;

    private static DaoSession mDaoSession;

    public GreenDaoManager(){

        if(mInstance ==null){
           DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(),"mydb",null);

           mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

           mDaoSession = mDaoMaster.newSession();

        }
    }

    public static GreenDaoManager getInstance(){
        if(mInstance ==null){
            synchronized (GreenDaoManager.class){
                if (mInstance==null){
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public static DaoMaster getMaster(){
        return mDaoMaster;
    }

    public static DaoSession getSession(){
        return mDaoSession;
    }

    public static DaoSession getNewSession(){

        mDaoSession = mDaoMaster.newSession();

        return mDaoSession;

    }
}

