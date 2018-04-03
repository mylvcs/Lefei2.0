package com.example.wangmengyun.activity;

import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.PickCityFragment1;
import com.example.wangmengyun.Fragment.PickCityFragment2;
import com.example.wangmengyun.activity.SingleFragmentActivity;

public class PickCityActivity2 extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new PickCityFragment2();
    }
}