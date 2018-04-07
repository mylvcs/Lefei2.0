package com.example.wangmengyun.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Fragment.PickCityFragment;
import com.example.wangmengyun.Utils.PingYinUtil;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.SearchResultAdapter;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;
import com.example.wangmengyun.view.MyLetterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/4/7.
 */

public class PickCityActivity  extends Activity{
//    @Override
//    protected Fragment createFragment() {
//        return new PickCityFragment();
//    }
//}
    private TextView tvDialog;//��������ʾ��ĸ��TextView
    private ListView lvCity;//���г����б�չʾ
    private EditText etSearch;
    private ListView lvResult;//��������б�չʾ
    private TextView tvNoResult;//�����޽��ʱ����չʾ

    private List<City> allCityList;//���еĳ���
    private List<City> hotCityList;//���ų����б�
    private List<City> searchCityList;//���������б�
    private List<String> recentCityList;//������ʳ����б�

    public CitySqliteOpenHelper cityOpenHelper;//�Ա�����������ʳ��е����ݿ�����İ�����
    public SQLiteDatabase cityDb;//����������ʳ��е����ݿ�
    public CityListAdapter cityListAdapter;
    public SearchResultAdapter searchResultAdapter;
    private boolean isScroll=false;
    private boolean mReady=false;
    private Handler handler;
    private OverlayThread overlayThread; //��ʾ����ĸ�Ի���

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city);

        initView();
        initData();
        setListener();

        initAllCityData();
        initRecentVisitCityData();
        initHotCityData();
        setAdapter();

    }

    private void setAdapter() {
        cityListAdapter = new CityListAdapter(this,allCityList,hotCityList,recentCityList);
        searchResultAdapter=new SearchResultAdapter(this,searchCityList);
        lvCity.setAdapter(cityListAdapter);
        lvResult.setAdapter(searchResultAdapter);
    }

    private void initView() {

        tvDialog = (TextView) findViewById(R.id.tv_dialog);
        lvCity=(ListView) findViewById(R.id.lv_city);
        etSearch=(EditText) findViewById(R.id.et_search);
        lvResult=(ListView) findViewById(R.id.lv_result);
        tvNoResult=(TextView) findViewById(R.id.tv_noresult);
    }

    private void setListener() {


        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString()==null||"".equals(s.toString())){

                    lvCity.setVisibility(View.VISIBLE);
                    lvResult.setVisibility(View.GONE);
                    tvNoResult.setVisibility(View.GONE);
                }else{
                    searchCityList.clear();

                    lvCity.setVisibility(View.GONE);
                    getResultCityList(s.toString());
                    if (searchCityList.size() <= 0) {
                        lvResult.setVisibility(View.GONE);
                        tvNoResult.setVisibility(View.VISIBLE);
                    } else {
                        lvResult.setVisibility(View.VISIBLE);
                        tvNoResult.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initData() {
        cityOpenHelper=new CitySqliteOpenHelper(PickCityActivity.this);
        cityDb=cityOpenHelper.getWritableDatabase();
        allCityList=new ArrayList<City>();
        hotCityList=new ArrayList<City>();
        searchCityList=new ArrayList<City>();
        recentCityList=new ArrayList<String>();
        handler = new Handler();
        overlayThread = new OverlayThread();
    }

    private void initAllCityData() {

        City city = new City(UUID.randomUUID(), "Shanghai");
        allCityList.add(city);
        city=new City(UUID.randomUUID(),"Beijing");
        allCityList.add(city);
        city=new City(UUID.randomUUID(),"Nanjing");
        allCityList.add(city);
        city=new City(UUID.randomUUID(),"Hangzhou");
        allCityList.add(city);
        allCityList.addAll(getCityList());
    }

    @SuppressWarnings("unchecked")
    private ArrayList<City> getCityList() {
        SQLiteDatabase db;
        Cursor cursor = null;

        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(PickCityActivity.this);
        ArrayList<City> cityList=new ArrayList<City>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor= db.rawQuery("select * from city",null);

            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
                City city=new City(UUID.randomUUID(), "Nanjing");
                cityList.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            cursor.close();
        }

        return cityList;

    }

    private void initRecentVisitCityData() {
        InsertCity("Shanghai");
        InsertCity("Nanjing");
        InsertCity("Beijing");
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
        City city=new City(UUID.randomUUID(), "Shanghai");
        hotCityList.add(city);
        city=new City(UUID.randomUUID(), "Beijing");
        hotCityList.add(city);
        city=new City(UUID.randomUUID(), "New York");
        hotCityList.add(city);
        city=new City(UUID.randomUUID(), "Guangzhou");
        hotCityList.add(city);
    }


    @SuppressWarnings("unchecked")
    private void getResultCityList(String keyword) {
        AllCitySqliteOpenHelper dbHelper = new AllCitySqliteOpenHelper(this);
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(
                    "select * from city where name like \"%" + keyword
                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
            City city;
            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
                city = new City(UUID.randomUUID(), "Nanjing");
                searchCityList.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

private class OverlayThread implements Runnable {
    @Override
    public void run() {
        tvDialog.setVisibility(View.INVISIBLE);
    }
}

    public void InsertCity(String name) {
        SQLiteDatabase db = cityOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) { //
            db.delete("recentcity", "name = ?", new String[] { name });
        }
        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
                + System.currentTimeMillis() + ")");
        db.close();
    }
}