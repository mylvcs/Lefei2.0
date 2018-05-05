package com.example.wangmengyun.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.https.Entity.GoodsDetailEntity;
import com.example.wangmengyun.https.presenter.GoodsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/5.
 * 商品详情页 添加到购物车
 */

public class GoodsSpecFragment extends Fragment{

    //图片
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    //价格
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    //减少
    @BindView(R.id.btn_release)
    ImageButton btnRelease;
    //数量
    @BindView(R.id.goods_count)
    EditText goodsCount;

    @BindView(R.id.btn_add)
    ImageButton btnAdd;

    @BindView(R.id.add_to_cart)
    TextView addToCart;

    private Context mContext;

    private GoodsDetailEntity entity;

    public GoodsSpecFragment (Context context,GoodsDetailEntity entity){

        this.mContext = context;
        this.entity = entity;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(android.R.layout.fragment_goods_spec, container,false);
        ButterKnife.bind(this, view);
        goodsCount.setText(""+1);

        goodsPrice.setText(""+entity.getPrice());


        return view;

    }
    @OnClick({R.id.btn_release, R.id.btn_add, R.id.add_to_cart})
    public void onClick(View view){
        int count = Integer.parseInt(goodsCount.getText().toString().trim());

        switch (view.getId()){
            case R.id.btn_release:
                if(count >1) {
                    goodsCount.setText("" + (count - 1));
                    double totalPrice = entity.getPrice() * (count - 1);

                    goodsPrice.setText(String.format("%2.f", totalPrice));
                }else {
                    goodsCount.setText(""+1);
                    goodsPrice.setText(""+ entity.getPrice());

                }
                break;
            case R.id.btn_add:
                goodsCount.setText(""+1);
                double totalPrice2 = entity.getPrice()*(count+1);

                goodsPrice.setText(String.format("%2.f",totalPrice2));
                break;
            case R.id.add_to_cart:

                break;
        }

    }
    private void addToCart(int goodsId, int goodsNum){
        SharedPreferences sp = mContext.getSharedPreferences("user", 0);
        int memberId = sp.getInt("member_id", -1);

        if(memberId!= -1){
            GoodsPresenter.addToCart(new Subscriber<HttpResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(HttpResult httpResult) {
                    Toast.makeText(mContext, httpResult.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }, memberId,goodsId);
        }
    }

}
