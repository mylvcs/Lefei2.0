//package com.example.wangmengyun.activity;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
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
//
//import com.example.wangmengyun.Bean.City;
//import com.example.wangmengyun.Utils.PingYinUtil;
//import com.example.wangmengyun.adapter.CityListAdapter;
//import com.example.wangmengyun.adapter.SearchResultAdapter;
//import com.example.wangmengyun.lefei.R;
//import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
//import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;
//import com.example.wangmengyun.view.MyLetterView;
//
//
//public class PickCityActivity2 extends Activity {
//
//    protected static final String TAG = "MainActivity";
//    private MyLetterView myLetterView;//�Զ����View
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
//    public CitySqliteOpenHelper cityOpenHelper;//�Ա�����������ʳ��е����ݿ�����İ�����
//    public SQLiteDatabase cityDb;//����������ʳ��е����ݿ�
//    public CityListAdapter cityListAdapter;
//    public SearchResultAdapter searchResultAdapter;
//    private boolean isScroll=false;
//    private boolean mReady=false;
//    private Handler handler;
//    private OverlayThread overlayThread; //��ʾ����ĸ�Ի���
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_city2);
//
//        initView();
//        initData();
//        setListener();
//
//        //��ʼ�����г����б�
//        initAllCityData();
//        initRecentVisitCityData();//��ʼ��������ʵĳ�������
//        initHotCityData();//��ʼ�����ų���
//        setAdapter();//����������
//        mReady=true;
//    }
//
//    private void setAdapter() {
//        cityListAdapter = new CityListAdapter(this,allCityList,hotCityList,recentCityList);
//        searchResultAdapter=new SearchResultAdapter(this,searchCityList);
//        lvCity.setAdapter(cityListAdapter);
//        lvResult.setAdapter(searchResultAdapter);
//    }
//
//    private void initView() {
//        myLetterView = (MyLetterView) findViewById(R.id.my_letterview);
//        tvDialog = (TextView) findViewById(R.id.tv_dialog);
//        myLetterView.setTextView(tvDialog);
//        lvCity=(ListView) findViewById(R.id.lv_city);
//        etSearch=(EditText) findViewById(R.id.et_search);
//        lvResult=(ListView) findViewById(R.id.lv_result);
//        tvNoResult=(TextView) findViewById(R.id.tv_noresult);
//    }
//
//    private void setListener() {
//        //�Զ���myLetterView��һ������
//        myLetterView.setOnSlidingListener(new MyLetterView.OnSlidingListener() {
//
//            @Override
//            public void sliding(String s) {
//                isScroll=false;
//                if(cityListAdapter.alphaIndexer.get(s)!=null){
//                    //����MyLetterView�����������ݻ��ListViewӦ��չʾ��λ��
//                    int position = cityListAdapter.alphaIndexer.get(s);
//                    //��listViewչʾ����Ӧ��λ��
//                    lvCity.setSelection(position);
//                }
//            }
//        });
//
//        etSearch.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString()==null||"".equals(s.toString())){
//                    myLetterView.setVisibility(View.VISIBLE);
//                    lvCity.setVisibility(View.VISIBLE);
//                    lvResult.setVisibility(View.GONE);
//                    tvNoResult.setVisibility(View.GONE);
//                }else{
//                    searchCityList.clear();
//                    myLetterView.setVisibility(View.GONE);
//                    lvCity.setVisibility(View.GONE);
//                    getResultCityList(s.toString());
//                    if (searchCityList.size() <= 0) {
//                        lvResult.setVisibility(View.GONE);
//                        tvNoResult.setVisibility(View.VISIBLE);
//                    } else {
//                        lvResult.setVisibility(View.VISIBLE);
//                        tvNoResult.setVisibility(View.GONE);
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        lvCity.setOnScrollListener(new OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == SCROLL_STATE_TOUCH_SCROLL
//                        || scrollState == SCROLL_STATE_FLING) {
//                    isScroll = true;
//                }
//
//            }
//
//            @SuppressLint("DefaultLocale") @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                if (!isScroll) {
//                    return;
//                }
//                if (mReady) {
//                    String text;
//                    String name = allCityList.get(firstVisibleItem).getName();
//                    String pinyin = allCityList.get(firstVisibleItem).getPinyin();
//                    if (firstVisibleItem < 4) {
//                        text = name;
//                    } else {
//                        text = PingYinUtil.converterToFirstSpell(pinyin)
//                                .substring(0, 1).toUpperCase();
//                    }
//                    tvDialog.setText(text);
//                    tvDialog.setVisibility(View.VISIBLE);
//                    handler.removeCallbacks(overlayThread);
////					Toast.makeText(MainActivity.this,"����",0).show();
////					 �ӳ�һ���ִ�У����м���ʾ��TextViewΪ���ɼ�
//                    handler.postDelayed(overlayThread,1000);
//                }
//            }
//        });
//    }
//
//    private void initData() {
//        cityOpenHelper=new CitySqliteOpenHelper(PickCityActivity2.this);
//        cityDb=cityOpenHelper.getWritableDatabase();
//        allCityList=new ArrayList<City>();
//        hotCityList=new ArrayList<City>();
//        searchCityList=new ArrayList<City>();
//        recentCityList=new ArrayList<String>();
//        handler = new Handler();
//        overlayThread = new OverlayThread();
//    }
//
//    private void initAllCityData() {
//
//        City city = new City("��λ", "0"); // ��ǰ��λ����
//        allCityList.add(city);
//        city=new City("���","1");
//        allCityList.add(city);
//        city=new City("����","2");
//        allCityList.add(city);
//        city=new City("ȫ��","3");
//        allCityList.add(city);
//        allCityList.addAll(getCityList());
//    }
//
//    @SuppressWarnings("unchecked")
//    private ArrayList<City> getCityList() {
//        SQLiteDatabase db;
//        Cursor cursor = null;
//        //��ȡassetsĿ¼�µ����ݿ��е����г��е�openHelper
//        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(PickCityActivity2.this);
//        ArrayList<City> cityList=new ArrayList<City>();
//        try {
//            op.createDataBase();
//            db = op.getWritableDatabase();
//            cursor= db.rawQuery("select * from city",null);
//
//            while (cursor.moveToNext()) {
//                String cityName=cursor.getString(cursor.getColumnIndex("name"));
//                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
//                City city=new City(cityName,cityPinyin);
//                cityList.add(city);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(cursor!=null)
//            cursor.close();
//        }
//        Collections.sort(cityList, comparator);
//        return cityList;
//
//    }
//
//    private void initRecentVisitCityData() {
//        InsertCity("����");
//        InsertCity("�Ϻ�");
//        InsertCity("����");
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
//        City city=new City("����","2");
//        hotCityList.add(city);
//        city=new City("�Ϻ�","2");
//        hotCityList.add(city);
//        city=new City("����","2");
//        hotCityList.add(city);
//        city=new City("�Ͼ�","2");
//        hotCityList.add(city);
//        city=new City("�Ϸ�","2");
//        hotCityList.add(city);
//        city=new City("����","2");
//        hotCityList.add(city);
//        city=new City("�ɽ","2");
//        hotCityList.add(city);
//        city=new City("�ձ�","2");
//        hotCityList.add(city);
//    }
//
//    /**
//     * �Զ����������򣬰���A-Z��������
//     */
//    @SuppressWarnings("rawtypes")
//    Comparator comparator = new Comparator<City>() {
//        @Override
//        public int compare(City lhs, City rhs) {
//            String a = lhs.getPinyin().substring(0, 1);
//            String b = rhs.getPinyin().substring(0, 1);
//            int flag = a.compareTo(b);
//            if (flag == 0) {
//                return a.compareTo(b);
//            } else {
//                return flag;
//            }
//        }
//    };
//
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
//        //���õ��ļ��ϰ����Զ����comparator�Ĺ����������
//        Collections.sort(searchCityList, comparator);
//    }
//
//    // ������ʾ��ĸ��TextViewΪ���ɼ�
//    private class OverlayThread implements Runnable {
//        @Override
//        public void run() {
//            tvDialog.setVisibility(View.INVISIBLE);
//        }
//    }
//    //�������ݵ�������ʵĳ���
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
