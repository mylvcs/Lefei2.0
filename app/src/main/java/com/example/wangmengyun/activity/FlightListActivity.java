package com.example.wangmengyun.activity;

import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.FlightListFragment;

/**
 * Created by wangmengyun on 2018/3/28.
 */

public class FlightListActivity extends SingleFragmentActivity {

    protected Fragment createFragment(){
        return new FlightListFragment();
    }

}
