package com.example.wangmengyun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.adapter.GoodsListAdapter;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.ProgressDialogSubscriber;
import com.example.wangmengyun.https.presenter.GoodsPresenter;
import com.example.wangmengyun.lefei.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/2.
 */

public class GoodsListActivity extends AppCompatActivity{

    private LinearLayoutManager mLayoutManager;
    
    private GoodsEntity entity;
    private GoodsListAdapter adapter;

    @BindView(R.id.goodslist_RecyclerView)
    RecyclerView goodslistRecyclerView;

    @BindView(R.id.favorite_index_btn)
    Button favor_btn;

    @OnClick({R.id.title_back, R.id.search_keyword, R.id.goodslist_orderby_sales, R.id.goodslist_orderby_grade, R.id.goodslist_price})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.title_back:
                break;
                
            case R.id.goodslist_orderby_sales:
                break;
                
            case R.id.goodslist_orderby_grade:
                break;
                
                
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goods_list);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        this.goodsId= intent.getIntExtra("goodsId", 0);

      initViews();
    }

    @OnClick({R.id.favorite_empty,R.id.favorite_index_btn})

//        public void onClick(View view){
//            switch (view.getId()){
//                case R.id.favorite_index_btn:
//                    addToFavorite(goodsId);
//                    break;
//
//            }
//    }


    private void addToFavorite(int goodsId){

        SharedPreferences sp = getSharedPreferences("user", 0);
        int memberId = sp.getInt("member_id", -1);
        if(memberId!=-1){
            GoodsPresenter.addToFavourite(new ProgressDialogSubscriber<HttpResult>(this) {
                @Override
                public void onNext(HttpResult httpResult) {
                    Intent intent = new Intent();
                    intent.setAction("com.goods.favoritelist");
                    intent.putExtra("goods_name" ,entity.getName());

//                    GoodsActivity.sentBroadcast(intent);

                }
            }, memberId, goodsId);
        }
    }

    private void initViews(){

        mLayoutManager = new LinearLayoutManager(this);

        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        goodslistRecyclerView.setLayoutManager(mLayoutManager);

        loadData();
//
//        adapter = new GoodsListAdapter(this);

        goodslistRecyclerView .setAdapter(adapter);

    }

    private void loadData(){

    }


//
//    private void loadGoodsListByCatId(int catId){
//
//        GoodsPresenter.list(new Subscriber<List<GoodsEntity>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(List<GoodsEntity> goodsEntities) {
//
//            }
//        })
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}
