package com.example.wangmengyun.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.wangmengyun.adapter.ContentAdapter;
import com.example.wangmengyun.lefei.R;

public class ChufaActivity extends Activity implements OnItemClickListener {

    // 模拟listview中加载的数据
    private static final String[] CONTENTS = { "北京", "上海", "广州", "深圳", "苏州",
            "南京", "武汉", "长沙", "杭州" };
    public static String EXTRA_TEXT = "Shanghai";
    private List<String> contentList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickcity);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        contentList = new ArrayList<String>();
        for (int i = 0; i < CONTENTS.length; i++) {
            contentList.add(CONTENTS[i]);
        }
        //实例化ContentAdapter类，并传入实现类
        mListView.setAdapter(new ContentAdapter(this, contentList, mListener));

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

        String city = contentList.get(position);

        Log.i("City",city);

        Intent in = new Intent();

        in.putExtra(Intent.EXTRA_TEXT,city);

        setResult(Activity.RESULT_OK,in);

        finish();


    }

    /**
     * 实现类，响应按钮点击事件
     */
    private ContentAdapter.MyClickListener mListener = new ContentAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {

            String city = contentList.get(position);

            Intent in = new Intent();

            in.putExtra("Departure_city",city);

            setResult(Activity.RESULT_OK,in);

            finish();
        }
    };
}