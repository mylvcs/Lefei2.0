package com.example.wangmengyun.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.adapter.HotCityAdapter;
import com.example.wangmengyun.lefei.R;

public class PickCityActivity extends Activity implements OnItemClickListener {

    public static String EXTRA_TEXT = "Shanghai";

    private ListView mListView;

    private List<City> mHotCityList;

    private View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickcity);

        init();
    }

    private void init() {
        mListView = findViewById(R.id.listview);

        mHotCityList = new ArrayList<City>();

        City city = new City(UUID.randomUUID(),"Shanghai");
        mHotCityList.add(city);
        city=new City(UUID.randomUUID(), "Beijing");
        mHotCityList.add(city);
        city=new City(UUID.randomUUID(), "New York");
        mHotCityList.add(city);
        city=new City(UUID.randomUUID(), "Paris");
        mHotCityList.add(city);


        mListView.setAdapter(new HotCityAdapter(this, mHotCityList , (HotCityAdapter.MyClickListener) mListener));

        mListView.setOnItemClickListener(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    //响应item点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
//

        String city = mHotCityList.get(position).getName();

            Intent in = new Intent();

            in.putExtra("Departure_city",city );

            setResult(Activity.RESULT_OK,in);

            finish();
    }


}