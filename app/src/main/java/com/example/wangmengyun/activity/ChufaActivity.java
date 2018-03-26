package com.example.wangmengyun.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.HotCityAdapter;
import com.example.wangmengyun.adapter.RecentVisitCityAdapter;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;
import com.example.wangmengyun.view.SideBar;

import java.util.ArrayList;
import java.util.List;

/**
 *出发界面
 *选择好城市后返回城市 到SearchActivity的出发EditText
 */
public class ChufaActivity extends Activity {
    private ListView sortListView;
    private SideBar sideBar;
    private SQLiteDatabase cityDb;
    private SQLiteOpenHelper cityOpenHelper;

    private EditText mCityName;
    private ListView lvhotCity;
    private ListView lvRecentCity;
    private List<City> allCityList;
    private List<City> hotCityList;
    private List<String> recentCityList;

    private CityListAdapter cityListAdapter;
    private City dingweiCity;
    private RecentVisitCityAdapter recentCityAdapter;
    private List<City> historyCityList;
    private HotCityAdapter hotCityAdapter;
    private Handler handler;

    private TextView mTextview;
    public String getDepartureOrArrive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chufa);
        initViews();
        initData();
        setAdapter();
        initHotCityList();
        initRecentVisitCityList();
        initHeadView();
//
//        mCityName = findViewById(R.id.et_search);
//        mCityName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ChufaActivity.this, ZidongtishiActivity.class);
//                startActivity(intent);

//        });


    }

    private void initData() {
        cityOpenHelper = new CitySqliteOpenHelper(ChufaActivity.this);
        cityDb = cityOpenHelper.getWritableDatabase();
        allCityList = new ArrayList<City>();
        hotCityList = new ArrayList<City>();
        recentCityList = new ArrayList<String>();
        handler = new Handler();


    }


    private void initRecentVisitCityList() {

        InsertCity("Shanghai");
//        InsertCity("New York");
//        InsertCity("Los Angeles");
        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            String recentVisitCityName = cursor.getString(cursor.getColumnIndex("name"));

            recentCityList.add(recentVisitCityName.toString());
        }
        cursor.close();
        recentVisitDb.close();

    }

    private void initHotCityList() {
        City city = new City("Shanghai", "2");
        hotCityList.add(city);
        city = new City("Beijing", "2");
        hotCityList.add(city);

    }

    private void initViews() {

        lvhotCity = findViewById(R.id.lv_hotCity);
        lvRecentCity = findViewById(R.id.lv_recentCity);


    }

    private void setAdapter() {


        //dingweiCityAdapter = new DingweiCityAdapter(this,dingweiCity);
        recentCityAdapter = new RecentVisitCityAdapter(this, recentCityList);
        hotCityAdapter = new HotCityAdapter(this, hotCityList);

    //    cityListAdapter = new CityListAdapter(this, dingweiCity, hotCityList, recentCityList);
//TODO
    //    cityListAdapter = new CityAdapter;


        lvhotCity.setAdapter(hotCityAdapter);
        lvRecentCity.setAdapter(recentCityAdapter);

    }

    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);

        GridView mGvCity = (GridView) headView.findViewById(R.id.gv_hot_city);
        String[] data = getResources().getStringArray(R.array.city);
        ArrayList<String> cityList = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            cityList.add(data[i]);
        }

//        CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
//        mGvCity.setAdapter(adapter);
        return headView;
    }

    /**
     * 数据库中有个表是用户历史搜索，insertCity..
     *
     * @param name
     */

    public void InsertCity(String name) {
        SQLiteDatabase db = cityOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) { //
            db.delete("recentcity", "name = ?", new String[]{name});
        }
        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
                + System.currentTimeMillis() + ")");
        db.close();
    }

//    private void getResultCityList(String keyword) {
//        AllCitySqliteOpenHelper dbHelper = new AllCitySqliteOpenHelper(this);
//        try {
//            dbHelper.createDataBase();
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            Cursor cursor = db.rawQuery(
//                    "select * from city where name like \"%" + keyword
//                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
//            City city;
//            while (cursor.moveToNext()) {
//                String cityName=cursor.getString(cursor.getColumnIndex("name"));
//                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
//                city = new City(cityName,cityPinyin);
//                searchCityList.add(city);
//            }
//            cursor.close();
//            db.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //���õ��ļ��ϰ����Զ����comparator�Ĺ����������
//        Collections.sort(searchCityList, comparator);
//    }
//    }


}
