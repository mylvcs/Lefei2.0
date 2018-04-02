package com.example.wangmengyun.Bean;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/4/2.
 */

public class CityLab {

    private static CityLab sCityLab;

    private List<City> mCities;

    public CityLab(Context context) {

        mCities= new ArrayList<>();

        for (int i = 0;i <100; i++){
            City city = new City();
            city.setName("City #" +i);

            mCities.add(city);
        }

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
