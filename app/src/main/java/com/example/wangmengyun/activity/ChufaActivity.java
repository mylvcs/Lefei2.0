package com.example.wangmengyun.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.SearchFlightFragment;

import java.util.UUID;

/**
 *出发界面
 *选择好城市后返回城市 到SearchActivity的出发EditText
 */
public class ChufaActivity extends SingleFragmentActivity{

    public static final String ExtraData = "com.example.wangmengyun.FlightNumber";

    public static Intent newIntent (Context packageContext, String DepartCity) {

        Intent intent = new Intent(packageContext, SearchFlightActivity.class);
        intent.putExtra(ExtraData, DepartCity);

        return intent;

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chufa);
//        initViews();
//        initData();
//        setAdapter();
//        initHotCityList();
//        initRecentVisitCityList();
//        initHeadView();
////
////        mCityName = findViewById(R.id.et_search);
////        mCityName.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(ChufaActivity.this, ZidongtishiActivity.class);
////                startActivity(intent);
//
////        });
//
//
//    }
//
//    private void initData() {
//        cityOpenHelper = new CitySqliteOpenHelper(ChufaActivity.this);
//        cityDb = cityOpenHelper.getWritableDatabase();
//        allCityList = new ArrayList<City>();
//        hotCityList = new ArrayList<City>();
//        recentCityList = new ArrayList<String>();
//        handler = new Handler();
//
//
//    }
//
//
//    private void initRecentVisitCityList() {
//
//        InsertCity("Shanghai");
////        InsertCity("New York");
////        InsertCity("Los Angeles");
//        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
//        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
//        while (cursor.moveToNext()) {
//            String recentVisitCityName = cursor.getString(cursor.getColumnIndex("name"));
//
//            recentCityList.add(recentVisitCityName.toString());
//        }
//        cursor.close();
//        recentVisitDb.close();
//
//    }
//
//    private void initHotCityList() {
//        City city = new City("Shanghai", "2");
//        hotCityList.add(city);
//        city = new City("Beijing", "2");
//        hotCityList.add(city);
//
//    }
//
//    private void initViews() {
//
//        lvhotCity = findViewById(R.id.lv_hotCity);
//        lvRecentCity = findViewById(R.id.lv_recentCity);
//
//
//    }
//
//    private void setAdapter() {
//
//
//        //dingweiCityAdapter = new DingweiCityAdapter(this,dingweiCity);
//        recentCityAdapter = new RecentVisitCityAdapter(this, recentCityList);
//        hotCityAdapter = new HotCityAdapter(this, hotCityList);
//
//        //    cityListAdapter = new CityListAdapter(this, dingweiCity, hotCityList, recentCityList);
////TODO
//        //    cityListAdapter = new CityAdapter;
//
//
//        lvhotCity.setAdapter((ListAdapter) hotCityAdapter);
//        lvRecentCity.setAdapter(recentCityAdapter);
//
//    }
//
//    private View initHeadView() {
//        View headView = getLayoutInflater().inflate(R.layout.headview, null);
//
//        GridView mGvCity = (GridView) headView.findViewById(R.id.gv_hot_city);
//        String[] data = getResources().getStringArray(R.array.city);
//        ArrayList<String> cityList = new ArrayList<>();
//
//        for (int i = 0; i < data.length; i++) {
//            cityList.add(data[i]);
//        }
//
////        CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
////        mGvCity.setAdapter(adapter);
//        return headView;
//    }
//
//    /**
//     * 数据库中有个表是用户历史搜索，insertCity..
//     *
//     * @param name
//     */
//
//    public void InsertCity(String name) {
//        SQLiteDatabase db = cityOpenHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
//                + name + "'", null);
//        if (cursor.getCount() > 0) { //
//            db.delete("recentcity", "name = ?", new String[]{name});
//        }
//        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
//                + System.currentTimeMillis() + ")");
//        db.close();
//    }
//
////    private void getResultCityList(String keyword) {
////        AllCitySqliteOpenHelper dbHelper = new AllCitySqliteOpenHelper(this);
////        try {
////            dbHelper.createDataBase();
////            SQLiteDatabase db = dbHelper.getWritableDatabase();
////            Cursor cursor = db.rawQuery(
////                    "select * from city where name like \"%" + keyword
////                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
////            City city;
////            while (cursor.moveToNext()) {
////                String cityName=cursor.getString(cursor.getColumnIndex("name"));
////                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
////                city = new City(cityName,cityPinyin);
////                searchCityList.add(city);
////            }
////            cursor.close();
////            db.close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        //���õ��ļ��ϰ����Զ����comparator�Ĺ����������
////        Collections.sort(searchCityList, comparator);
////    }
////    }
//
//    public void onListItemClick(int clickedItemIndex) {
//        // COMPLETED (11) In the beginning of the method, cancel the Toast if it isn't null
//        /*
//         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
//         * ensures that our new Toast will show immediately, rather than
//         * being delayed while other pending Toasts are shown.
//         *
//         * Comment out these three lines, run the app, and click on a bunch of
//         * different items if you're not sure what I'm talking about.
//         */
//        if (mToast != null) {
//            mToast.cancel();
//        }
//
//        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
//        /*
//         * Create a Toast and store it in our Toast field.
//         * The Toast that shows up will have a message similar to the following:
//         *
//         *                     Item #42 clicked.
//         */
//        String toastMessage = "出发城市" + clickedItemIndex;
//        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//
//        mToast.show();
//    }

    @Override
    protected Fragment createFragment() {
        UUID flightNumber = (UUID) getIntent().getSerializableExtra(ExtraData);

        return SearchFlightFragment.newInstance(flightNumber);
    }

}
