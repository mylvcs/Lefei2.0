package com.wangmengyun.lefei2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wangmengyun on 2018/2/28.
 */

public class FlightActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new FlightFragment();
    }
//
//    @Override
//    protected void onCreate(Bundle saveInstanceState){
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm =getSupportFragmentManager();
//
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if(fragment ==null){
//            fragment = new FlightFragment();
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
//
//    }

}
