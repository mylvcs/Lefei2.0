//package com.example.wangmengyun.activity;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.UUID;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.Window;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.wangmengyun.Bean.City;
//
//import com.example.wangmengyun.adapter.CityListAdapter;
//import com.example.wangmengyun.adapter.HotCityAdapter;
//import com.example.wangmengyun.adapter.SearchResultAdapter;
//import com.example.wangmengyun.lefei.R;
//import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
//import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;
//import com.example.wangmengyun.view.MyLetterView;
//
//public class PickCityActivity extends Activity {
//
//    private TextView tvDialog;//��������ʾ��ĸ��TextView
//    private ListView lvCity;//���г����б�չʾ
//    private EditText etSearch;
//    private ListView lvResult;//��������б�չʾ
//    private TextView tvNoResult;//�����޽��ʱ����չʾ
//
//    private List<City> allCityList;//���еĳ���
//    private List<City> hotCityList;//���ų����б�
//    private List<City> searchCityList;//���������б�
//    private List<String> recentCityList;//������ʳ����б�
//
//    public CitySqliteOpenHelper cityOpenHelper;
//    public SQLiteDatabase cityDb;
//    public CityListAdapter cityListAdapter;
//    public HotCityAdapter hotCityAdapter;
//
//    public SearchResultAdapter searchResultAdapter;
//    private boolean isScroll=false;
//    private boolean mReady=false;
//    private Handler handler;
//    private View.OnClickListener mListener;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_city);
//
//        initView();
//        initData();
//
//        initAllCityData();
//        initRecentVisitCityData();
//        initHotCityData();
//        initAdapter();
//
//    }
//
//    private void initAdapter() {
//
//
//        cityListAdapter = new CityListAdapter(this, allCityList, hotCityList, recentCityList);
//        searchResultAdapter = new SearchResultAdapter(this, searchCityList, (SearchResultAdapter.MyClickListener) mListener);
//
//        hotCityAdapter = new HotCityAdapter(this, hotCityList);
//        lvCity.setAdapter(cityListAdapter);
//
////        hotCityAdapter= new HotCityAdapter(PickCityActivity.this,hotCityList, (HotCityAdapter.MyClickListener) mListener);
////        hotCityAdapter.setsubClickListener(new HotCityAdapter.SubClickListener() {
////            @Override
////            public void OntopicClickListener(View v, City city, int position) {
////
////                Toast.makeText(PickCityActivity.this,city.getName(),Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
//        lvResult.setAdapter(searchResultAdapter);
//
////        cityListAdapter.setsubClickListener(new CityListAdapter.SubClickListener() {
////
////            @Override
////            public void OntopicClickListener(View v, City city, int position) {
////
////                //TODO
//////                Intent intent= new Intent(PickCityActivity.this,SearchFlightActivity.class);
//////
//////                intent.putExtra("Departure_city", String.valueOf(city));
//////
//////                startActivity(intent);
////                Toast.makeText(v.getContext(), city.toString(),Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
//        cityListAdapter.setOnClickMyTextView(new CityListAdapter.onClickMyTextView() {
//            @Override
//            public void myTextViewClick(int id) {
//                String string = hotCityList.get(id).getName();
//
//                Toast.makeText(PickCityActivity.this, hotCityList.get(id).getName(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//
////        hotCityAdapter.setOnClickMyTextView(new CityListAdapter.onClickMyTextView() {
////            @Override
////            public void myTextViewClick(int id) {
////
////
////            }
////        });
//
//    }
//
//
//    private void initView() {
//
//        lvCity=(ListView) findViewById(R.id.lv_city);
//        etSearch=(EditText) findViewById(R.id.et_search);
//        lvResult=(ListView) findViewById(R.id.lv_result);
//        tvNoResult=(TextView) findViewById(R.id.tv_noresult);
//    }
//
//
//    private void initData() {
//        cityOpenHelper=new CitySqliteOpenHelper(PickCityActivity.this);
//        cityDb=cityOpenHelper.getWritableDatabase();
//        allCityList=new ArrayList<City>();
//        hotCityList=new ArrayList<City>();
//        searchCityList=new ArrayList<City>();
//        recentCityList=new ArrayList<String>();
//        handler = new Handler();
//
//    }
//
//    private void initAllCityData() {
//
//        City city = new City(UUID.randomUUID(), "Shanghai"); // ��ǰ��λ����
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(),"New York");
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(),"Beijing");
//        allCityList.add(city);
//        city=new City(UUID.randomUUID(),"Nanjing");
//        allCityList.add(city);
//        allCityList.addAll(getCityList());
//    }
//
//    @SuppressWarnings("unchecked")
//    private ArrayList<City> getCityList() {
//        SQLiteDatabase db;
//        Cursor cursor = null;
//
//        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(PickCityActivity.this);
//        ArrayList<City> cityList=new ArrayList<City>();
//        try {
//            op.createDataBase();
//            db = op.getWritableDatabase();
//            cursor= db.rawQuery("select * from city",null);
//
//            while (cursor.moveToNext()) {
//                String cityName=cursor.getString(cursor.getColumnIndex("name"));
//                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
//                City city=new City(UUID.randomUUID(),"Nanjing");
//                cityList.add(city);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            cursor.close();
//        }
//
//        return cityList;
//
//    }
//
//    private void initRecentVisitCityData() {
////        InsertCity("Beijing");
////        InsertCity("Shanghai");
////        InsertCity("Nanjing");
//        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
//        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
//        while (cursor.moveToNext()) {
//            String recentVisitCityName=cursor.getString(cursor.getColumnIndex("name"));
//            recentCityList.add(recentVisitCityName);
//        }
//        cursor.close();
//        recentVisitDb.close();
//    }
//
//    private void initHotCityData() {
//        City city = new City(UUID.randomUUID(), "Shanghai"); // ��ǰ��λ����
//        hotCityList.add(city);
//        city=new City(UUID.randomUUID(),"New York");
//        hotCityList.add(city);
//        city=new City(UUID.randomUUID(),"Beijing");
//        hotCityList.add(city);
//        city=new City(UUID.randomUUID(),"Nanjing");
//        hotCityList.add(city);
//        hotCityList.addAll(getCityList());
//    }
//
////    @Override
////    public void clickListener(View v) {
////
////    }
//
//
//}
