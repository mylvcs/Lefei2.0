package com.example.wangmengyun.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.FlightListFragment;

//import com.example.wangmengyun.Fragment.FlightListFragment;

/**
 * Created by wangmengyun on 2018/3/28.
 */

public class FlightListActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new FlightListFragment();
    }

//
//    public static final String Departure_City = "到达城市";
//
//    public static Intent newIntent(Context packageContext, String departure_City) {
//
//        Intent intent = new Intent(packageContext, FlightListActivity.class);
//        intent.putExtra(Departure_City, departure_City);
//
//        return intent;
//    }

}
