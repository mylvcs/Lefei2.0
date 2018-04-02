package com.example.wangmengyun.activity;


import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.FlightListFragment;
import com.example.wangmengyun.Fragment.PickCityFragment;

/**
 * 总共四个组成部分：1 搜索框 2 定位城市 3 搜索历史城市 4 热门城市
 *
 */

public class PickCityActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new PickCityFragment();
    }
}