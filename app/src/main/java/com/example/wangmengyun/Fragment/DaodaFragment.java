package com.example.wangmengyun.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Bean.CityLab;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.adapter.CityAdapter;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.HotCityAdapter;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


/**
 * Created by wangmengyun on 2018/4/1.
 *
 * 总共四个组成部分：1 搜索框 2 定位城市 3 搜索历史城市 4 热门城市
 */

public class DaodaFragment extends Fragment {

    private static final String ARG_CITY = "departureCity";
    public static final String EXTRA_DEPARTURE = "NULL";

    private static final int REQUEST_DEPARTURE = 1;
    private static final String EXTRA_ARRIVE = "NULL";

    private RecyclerView mCityRecyclerView;
    private CityAdapter mCityAdapter;


    private ListView lvCity;
    private EditText etSearch;
    private ListView lvResult;
    private TextView tvNoResult;
    private List<City> allCityList;
    private List<City> hotCityList;
    private List<City> searchCityList;
    private List<String> recentCityList;
    private View.OnClickListener mListener;
    private List<City> mCities;

    public CitySqliteOpenHelper cityOpenHelper;
    public SQLiteDatabase cityDb;
    public CityListAdapter cityListAdapter;
    public HotCityAdapter hotCityAdapter;
    private boolean mReady = false;
    private Handler handler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAdapter();

        initData();

        initAllCityData();

        initHotCityData();

        initRecentVisitCityData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_city, container, false);

        lvCity = view.findViewById(R.id.lv_city);


        return view;

    }

    private void initData() {

        cityOpenHelper = new CitySqliteOpenHelper(getActivity());
        cityDb = cityOpenHelper.getWritableDatabase();

        allCityList = new ArrayList<City>();
        hotCityList = new ArrayList<City>();
        recentCityList = new ArrayList<String>();
        handler = new Handler();

    }




    private void setAdapter() {
        cityListAdapter = new CityListAdapter(getContext(), mCities);

//        lvCity.setAdapter(cityListAdapter);


        hotCityAdapter = new HotCityAdapter(getContext(),hotCityList);

//        hotCityAdapter.setsubClickListener(new SubClickListener() {
//            @Override
//            public void OntopicClickListener(View v, City city, int position) {
//                //TODO
//
//            }
//        });

        lvCity.setAdapter(hotCityAdapter);

    }


    private void initAllCityData() {

//        City city = new City(UUID.randomUUID(),"Shanghai");
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(), "Beijing");
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(), "New York");
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(), "Paris");
//        allCityList.add(city);

//        allCityList.addAll(getCityList());
    }

    private ArrayList<City> getCityList() {
        SQLiteDatabase db;
        Cursor cursor = null;

        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(getActivity());
        ArrayList<City> cityList=new ArrayList<City>();

        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor= db.rawQuery("select * from city",null);

            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
          //      City city=new City(UUID.randomUUID(),"LA ");

         //       cityList.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            cursor.close();
        }

        return cityList;

    }

    private void initRecentVisitCityData() {

        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            String recentVisitCityName=cursor.getString(cursor.getColumnIndex("name"));
            recentCityList.add(recentVisitCityName);
        }
        cursor.close();
        recentVisitDb.close();
    }

    private void initHotCityData() {

 //       City city=new City(UUID.randomUUID(), "上海");
 //       hotCityList.add(city);
    }


    private void getResultCityList(String keyword) {
        AllCitySqliteOpenHelper dbHelper = new AllCitySqliteOpenHelper(getContext());
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(
                    "select * from city where name like \"%" + keyword
                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
            City city;
            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("CityName"));

//                city = new City(UUID.randomUUID(),"Nanjing");
//                searchCityList.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}