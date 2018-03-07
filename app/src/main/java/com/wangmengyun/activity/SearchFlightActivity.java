package com.wangmengyun.activity;

/**
 * Created by wangmengyun on 2018/3/7.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wangmengyun.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangmengyun on 2018/3/4.
 */


public class SearchFlightActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_dancheng, tv_wangfan, tv_chapter_intro;
    private ListView lv_flight_search;
    private Button mButton;

    //   private VideoListAdapter adapter;
//    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
//    private DBUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchflight);
        // 设置此界面为竖屏
        //      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 从课程界面传递过来的章节id
        //     chapterId = getIntent().getIntExtra("id", 0);
        // 从课程界面传递过来的章节简介
        //   tv_dancheng = getIntent().getStringExtra("intro");
        // 创建数据库工具类的对象
//        db = DBUtils.getInstance(SearchFlightActivity.this);
        //       initData();
        init();
    }
    /**
     * 初始化界面UI控件
     */
    private void init() {
        tv_dancheng= (TextView) findViewById(R.id.tv_dancheng);
        tv_wangfan = (TextView) findViewById(R.id.tv_wangfan);
        mButton = (Button) findViewById(R.id.departure);
        tv_wangfan.setOnClickListener(this);
        tv_dancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchFlightActivity.this,CityActivity.class);
                startActivity(intent);
            }


        });
//        adapter.setData(videoList);
//        tv_chapter_intro.setText(intro);
        tv_dancheng.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_wangfan.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_dancheng.setTextColor(Color.parseColor("#FFFFFF"));
        tv_wangfan.setTextColor(Color.parseColor("#000000"));
    }
    /**
     * 控件的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dancheng:// 单程
                //    lv_flight_search.setVisibility(View.GONE);
                //    sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_dancheng.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_wangfan.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_dancheng.setTextColor(Color.parseColor("#FFFFFF"));
                tv_wangfan.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_wangfan:// 往返
                //    lv_flight_search.setVisibility(View.VISIBLE);
                //     sv_chapter_intro.setVisibility(View.GONE);
                tv_dancheng.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_wangfan.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_dancheng.setTextColor(Color.parseColor("#000000"));
                tv_wangfan.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            default:
                break;
        }
    }
    /**
     * 读取数据流,参数in是数据流
     */
    private String read(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line=null;
        try {
            sb = new StringBuilder();//实例化一个StringBuilder对象
            //用InputStreamReader把in这个字节流转换成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine())!=null){//从reader中读取一行的内容判断是否为空
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (in != null)
                    in.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**
     * 从SharedPreferences中读取登录状态
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            int position=data.getIntExtra("position", 0);
//            adapter.setSelectedPosition(position);// 设置被选中的位置
            // 目录选项卡被选中时所有图标的颜色值
            lv_flight_search.setVisibility(View.VISIBLE);
//            sv_chapter_intro.setVisibility(View.GONE);
//            tv_intro.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
//            tv_intro.setTextColor(Color.parseColor("#000000"));
//            tv_video.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}