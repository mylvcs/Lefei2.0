package com.example.wangmengyun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.Fragment.GoodsSpecFragment;
import com.example.wangmengyun.adapter.CartGoodsAdapter;
import com.example.wangmengyun.data.Goods;
import com.example.wangmengyun.https.Entity.GoodsDetailEntity;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.ProgressDialogSubscriber;
import com.example.wangmengyun.https.presenter.GoodsPresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/2.
 */

public class GoodsActivity extends AppCompatActivity{
    @BindView(R.id.title_back)
    ImageView titleBack;

    @BindView(R.id.favourite_layout)
    LinearLayout favouriteLayout;

    @BindView(R.id.cat_layout)
    LinearLayout catLayout;

    @BindView(R.id.add_to_cart)
    TextView addToCart;

    @BindView(R.id.image_pager)
    ViewPager imagePager;

    @BindView(R.id.goods_name)
    TextView goodsName;

    @BindView(R.id.goods_brand)
    TextView goodsBrand;

    @BindView(R.id.goods_view_count)
    TextView goodsViewCount;

    @BindView(R.id.choose_count)
    ImageView chooseCount;

    private int goodsId;

    List<String> images= new ArrayList<>();

    GoodsViewAdapter adapter;
    private android.app.FragmentManager fragmentManager;

    private GoodsSpecFragment goodsSpecFragment;

    private GoodsDetailEntity Entity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(android.R.layout.activity_goods);

        ButterKnife.bind(this);

        Intent intent= getIntent();

        goodsId = intent.getIntExtra("goodsId", 0);


        fragmentManager = getFragmentManager();

        loadGoodsDetail(32);
    }

    private void loadGoodsDetail(int goodsId) {

        GoodsPresenter.goodsDetail(new Subscriber<GoodsDetailEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext( GoodsDetailEntity entity) {

                goodsName.setText(entity.toString());

                goodsPrice.setText("$"+ entity.getPrice());

                goodsViewCount.setText(""+ entity.getView_count());


                GoodsActivity.this.entity= entity;

            }
        }, goodsId);
    }

    @OnClick({R.id.title_back, R.id.favourite_layout, R.id.cat_layout, R.id.add_to_cart,R.id.choose_count })
            public void onClick(View view)
    {
        switch (view.getId()) {

            case (R.id.title_back):
                finish();
                break;

            case R.id.cat_layout:
                Intent intent = new Intent(this, CartActivity.class);

                startActivity(intent);

                break;


            case R.id.add_to_cart:
                addToCart(goodsId);

                break;

            case R.id.choose_count:


        }


    }

    /**
     * 添加到购物车
     * @param goodsId
     */

    private void addToCart(int goodsId) {

        SharedPreferences sp = getPreferences("user", 0);

        int memberId= sp.getInt("member_id", -1);

        if(memberId != -1){
            GoodsPresenter.addToCart(new Subscriber<HttpResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(HttpResult httpResult) {
                    Intent intent = new Intent();

                    GoodsActivity("com.goods.shoppingcart");

                    GoodsActivity.this.sendBroadcast(intent);
                    Toast.makeText(GoodsActivity.this, "添加成功",Toast.LENGTH_SHORT).show();
                }
            }, memberId, goodsId, 1);
        }
    }


    /**
     * @param goodsId
     */

    private void addToFavourite(int goodsId){
        SharedPreferences sp = getPreferences("user" , 0);

        int memberId = sp.getInt("member_id", -1);

        if(memberId != -1){
            GoodsPresenter.addToFavourite(new ProgressDialogSubscriber<HttpResult>(this) {
                @Override
                public void onNext(HttpResult httpResult) {
                    //同步到本地购物车列表
                    Intent intent = new Intent();
                    intent.setAction("com.goods.favouritelist");

                    intent.putExtra("goods_price", entity.getPrice());

                    intent.putExtra("goods_totalPrice",entity.getTotalPrice());

                    GoodsActivity.this.sendBroadcast(intent);
                }
            }, memberId, goodsId);
        }
    }

    private Map<String,String> stringToMap(String string){
        Map<String, String> params = new HashMap<>();
        JSONObject obj = null;

        try{
            obj = new JSONObject(string);
            Iterator<String> iterator = obj.keys();

            String key, value;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;

    }



}
