package com.example.wangmengyun.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangmengyun.Bean.City;
//import com.example.wangmengyun.Fragment.PickCityFragment;
import com.example.wangmengyun.Fragment.PickCityFragment1;
import com.example.wangmengyun.Fragment.SearchFlightFragment;
import com.example.wangmengyun.Utils.PingYinUtil;
import com.example.wangmengyun.activity.SingleFragmentActivity;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.HotCityAdapter;
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

public class
DaodaCityActivity extends Activity implements AdapterView.OnItemClickListener {


    private MyLetterView myLetterView;//�Զ����View
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
    private boolean isScroll = false;
    private boolean mReady = false;
    private Handler handler;

    private List<City> mCities;
    private AdapterView.OnItemClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city);

        initView();
        initData();
        setListener();

        //��ʼ�����г����б�
        initAllCityData();
        initRecentVisitCityData();//��ʼ��������ʵĳ�������
        initHotCityData();//��ʼ�����ų���
        setAdapter();//����������
        mReady = true;
    }

     private void setAdapter() {
        cityListAdapter = new CityListAdapter(this, mCities);
        searchResultAdapter = new SearchResultAdapter(this, searchCityList, (SearchResultAdapter.MyClickListener) mListener);
        lvCity.setAdapter(cityListAdapter);
        lvResult.setAdapter(searchResultAdapter);

    }

    private void initView() {

        tvDialog = (TextView) findViewById(R.id.tv_dialog);

        lvCity = (ListView) findViewById(R.id.lv_city);
        etSearch = (EditText) findViewById(R.id.et_search);
        lvResult = (ListView) findViewById(R.id.lv_result);
        tvNoResult = (TextView) findViewById(R.id.tv_noresult);
    }

    private void setListener() {


        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() == null || "".equals(s.toString())) {

                    lvCity.setVisibility(View.VISIBLE);
                    lvResult.setVisibility(View.GONE);
                    tvNoResult.setVisibility(View.GONE);
                } else {
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
        cityOpenHelper = new CitySqliteOpenHelper(DaodaCityActivity.this);
        cityDb = cityOpenHelper.getWritableDatabase();
        allCityList = new ArrayList<City>();
        hotCityList = new ArrayList<City>();
        searchCityList = new ArrayList<City>();
        recentCityList = new ArrayList<String>();
        handler = new Handler();

    }



    private void initAllCityData() {

//        City city = new City(UUID.randomUUID(), "0"); // ��ǰ��λ����
//        allCityList.add(city);
//        city = new City(UUID.randomUUID(), "1");
//        allCityList.add(city);
//        city = new City(UUID.random
//UUID(), "2");
//        allCityList.add(city);
//        city = new City(UUID.randomUUID(), "3");
//        allCityList.add(city);
        allCityList.addAll(getCityList());
    }

    @SuppressWarnings("unchecked")
    private ArrayList<City> getCityList() {
        SQLiteDatabase db;
        Cursor cursor = null;

        AllCitySqliteOpenHelper op = new AllCitySqliteOpenHelper(DaodaCityActivity.this);
        ArrayList<City> cityList = new ArrayList<City>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor = db.rawQuery("select * from city", null);

            while (cursor.moveToNext()) {
                String cityName = cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
            //    City city = new City(UUID.randomUUID(), "Shanghai");
        //        cityList.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return cityList;

    }

    private void initRecentVisitCityData() {
//        InsertCity("Nanjing");
//        InsertCity("Shanghai");
//        InsertCity("Beijing");
        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            String recentVisitCityName = cursor.getString(cursor.getColumnIndex("name"));
            recentCityList.add(recentVisitCityName);
        }
        cursor.close();
        recentVisitDb.close();
    }

    private void initHotCityData() {
//        City city = new City(UUID.randomUUID(), "2");
//        hotCityList.add(city);
//        city = new City(UUID.randomUUID(), "1");
//        hotCityList.add(city);
//        city = new City(UUID.randomUUID(), "3");
 //       hotCityList.add(city);
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
                String cityName = cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
             //   city = new City(UUID.randomUUID(), "Beijing");
            //    searchCityList.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String city = allCityList.get(position).getName();

        Intent in = new Intent();

        in.putExtra("Departure_city",city );

        setResult(Activity.RESULT_OK,in);

        finish();
    }
}