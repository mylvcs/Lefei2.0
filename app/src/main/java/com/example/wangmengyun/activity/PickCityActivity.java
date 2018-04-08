package com.example.wangmengyun.activity;

import android.support.v4.app.Fragment;

import com.example.wangmengyun.Fragment.PickCityFragment;
import com.example.wangmengyun.Fragment.PickCityFragment1;
import com.example.wangmengyun.Fragment.SearchFlightFragment;
import com.example.wangmengyun.activity.SingleFragmentActivity;

import java.util.UUID;

public class PickCityActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        return new PickCityFragment();

    }
}