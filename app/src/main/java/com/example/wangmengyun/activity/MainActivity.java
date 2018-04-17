package com.example.wangmengyun.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
import com.example.wangmengyun.view.FlightView;
import com.google.firebase.FirebaseApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String TAG = "MainActivity";

    /**
     * 视图
     */
    private FlightView mFlightView;
 /**
     * 中间内容栏
     */
    private FrameLayout mBodyLayout;
    /**
     * 底部按钮栏
     */
    public LinearLayout mBottomLayout;
    /**
     * 底部按钮
     */
    private View mFlightBtn;
    private View mMyInfoBtn;

    private View mTicketBtn;

    private TextView tv_flight;

    private ImageView iv_flight;

    private TextView tv_back;
    private RelativeLayout rl_title_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initBottomBar();
        setListener();
        setInitStatus();


    }

    private void initBottomBar() {

        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);

        tv_flight = (TextView) findViewById(R.id.bottom_bar_text_Flight);

        iv_flight = (ImageView) findViewById(R.id.bottom_bar_image_Flight);


    }

    private void setListener() {

        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    private void setInitStatus() {



    }

    private void init() {

        mFlightBtn = findViewById(R.id.bottom_bar_Flight_btn);

        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);

        mTicketBtn = findViewById(R.id.bottom_bar_Ticket_btn);


        initBodyLayout();



    }

    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);

    }

    @Override
    public void onClick(View v) {

        mFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchFlightActivity.class);

                startActivity(intent);
            }
        });

        mMyInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, UserInfoActivity.class);

                startActivity(in);
            }
        });

        mTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FireStoreActivity.class);

                startActivity(in);
            }
        });

    }


//
//    private ArrayList<City> getCityList() {
//        SQLiteDatabase db;
//        Cursor cursor = null;
//        AllCitySqliteOpenHelper op = new AllCitySqliteOpenHelper(MainActivity.this);
//        ArrayList<City> cityList = new ArrayList<City>();
//        try {
//            op.createDataBase();
//            db = op.getWritableDatabase();
//            cursor = db.rawQuery("select * from city", null);
//
//            while (cursor.moveToNext()) {
//                String cityName = cursor.getString(cursor.getColumnIndex("name"));
//                String cityPinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
//                City city = new City(cityName, cityPinyin);
//                cityList.add(city);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor == null) throw new AssertionError();
//            cursor.close();
//        }
//        Collections.sort(cityList, comparator);
//        return cityList;
//
//    }
//
//    private void initRecentVisitCityData() {
//        InsertCity("±±¾©");
//        InsertCity("ÉÏº£");
//        InsertCity("¹ãÖÝ");
//        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
//        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
//        while (cursor.moveToNext()) {
//            String recentVisitCityName = cursor.getString(cursor.getColumnIndex("name"));
//            recentCityList.add(recentVisitCityName);
//        }
//        cursor.close();
//        recentVisitDb.close();
//    }

}

//    @SuppressWarnings("unchecked")
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
//        //½«µÃµ½µÄ¼¯ºÏ°´ÕÕ×Ô¶¨ÒåµÄcomparatorµÄ¹æÔò½øÐÐÅÅÐò
//        Collections.sort(searchCityList, comparator);
//    }
//
//    // ÉèÖÃÏÔÊ¾×ÖÄ¸µÄTextViewÎª²»¿É¼û
//    private class OverlayThread implements Runnable {
//        @Override
//        public void run() {
//            tvDialog.setVisibility(View.INVISIBLE);
//        }
//    }
//    //²åÈëÊý¾Ýµ½×î½ü·ÃÎÊµÄ³ÇÊÐ
//    public void InsertCity(String name) {
//        SQLiteDatabase db = cityOpenHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
//                + name + "'", null);
//        if (cursor.getCount() > 0) { //
//            db.delete("recentcity", "name = ?", new String[] { name });
//        }
//        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
//                + System.currentTimeMillis() + ")");
//        db.close();
//    }
//}