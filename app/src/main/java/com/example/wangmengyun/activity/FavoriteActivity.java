//package com.example.wangmengyun.activity;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.wangmengyun.adapter.FavoriteGoodsAdapter;
//import com.example.wangmengyun.database.FavoriteGoodsEntity;
//import com.example.wangmengyun.lefei.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by wangmengyun on 2018/4/28.
// */
//
//public class FavoriteActivity extends AppCompatActivity{
//
//    private FavoriteGoodsAdapter adapter;
//
//    @BindView(R.id.title_back)
//    ImageView title_back;
//
//    @BindView(R.id.product_list)
//    RecyclerView productList;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favorite);
//
//        ButterKnife.bind(this);
//
//        SharedPreferences sp = getPreferences("user",0);
//
//        int memberId = sp.getInt("member_id", -1);
//
//        requireList(memberId);
//
//        adapter = new FavoriteGoodsAdapter(this, data);
//
//        productList.setAdapter(adapter);
//
//
//    }
//}
