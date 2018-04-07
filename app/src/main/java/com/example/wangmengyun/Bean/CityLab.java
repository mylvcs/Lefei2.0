package com.example.wangmengyun.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/4/2.
 */

public class CityLab {

    private static CityLab sCityLab;
    private Context mContext;

    private List<City> mCities;
    private SQLiteDatabase mDatabase;

    public CityLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CityBaseHelper(mContext).getWritableDatabase();

        mCities= new ArrayList<>();

        }


    public static CityLab get(Context context){
        if(sCityLab == null){
            sCityLab = new CityLab(context);
        }
        return sCityLab;
    }


    public List<City> getCity(){
        return mCities;
    }

    public City getCity(String CityName){
        for(City city: mCities){
            if(city.getName().equals(CityName)){
                return city;
            }
        }
        return null;
    }

}
