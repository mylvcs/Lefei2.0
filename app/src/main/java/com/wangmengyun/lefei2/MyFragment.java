package com.wangmengyun.lefei2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangmengyun on 2018/3/1.
 */

public class MyFragment extends Fragment{
    private String context;
    private TextView mTextView;

//    public static MyFragment  newInstance (Context context){
//        MyFragment myFragment = new MyFragment();
//        Bundle bundle = new Bundle();
//        bundle.p
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        mTextView = (TextView)view.findViewById(R.id.textView);
        mTextView.setText(context);
        return view;
    }
}