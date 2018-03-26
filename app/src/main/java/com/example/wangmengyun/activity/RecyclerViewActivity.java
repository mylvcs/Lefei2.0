package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wangmengyun.lefei.R;

/**
 * Created by wangmengyun on 2018/3/24.
 */

public class RecyclerViewActivity extends AppCompatActivity implements CityAdapter.ListItemClickListener{

    private static final int NUM_LIST_ITEMS = 100;
    private CityAdapter mAdapter;
    private RecyclerView mCityList;

    private Toast mToast;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);


        mCityList =(RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCityList.setLayoutManager(layoutManager);

        mAdapter = new CityAdapter(NUM_LIST_ITEMS,this);

        mCityList.setAdapter(mAdapter);



    }

    public void onListItemClick(int clickedItemIndex) {

        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        mToast.show();
    }
}
