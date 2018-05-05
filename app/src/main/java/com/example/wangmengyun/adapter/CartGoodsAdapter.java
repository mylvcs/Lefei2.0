package com.example.wangmengyun.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.https.Entity.CartGoodsEntity;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.HttpMethods;
import com.example.wangmengyun.https.ProgressDialogSubscriber;
import com.example.wangmengyun.https.presenter.GoodsPresenter;

import java.util.List;

/**
 * Created by wangmengyun on 2018/5/5.
 * P267
 */

public class CartGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CartGoodsEntity> mDatas;
    private Context mContext;

    public CartGoodsAdapter (Context context, List<CartGoodsEntity> data){
        super();
        this.mContext = context;
        this.mDatas= data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(android.R.layout.item_cart_goods_list, parent, false);

        MyHolder holder = new MyHolder(view );

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CartGoodsEntity entity = mDatas.get(position);
        if(holder instanceof MyHolder) {
            MyHolder newholder = (MyHolder) holder;

            newholder.tv_title.setText(entity.getName());
            newholder.tv_price.setText(entity.getAmount());
            newholder.tv_num.setText("" + entity.getGoods_num());

            newholder.ib_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateNumber(entity,1);
                }
            });
            newholder.ib_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateNumber(entity, -1);
                }
            });



        }
    }


    private void updateNumber(CartGoodsEntity entity, int number) {
        int cartId = entity.getCart_id();
        int num = entity.getGoods_num();

        if(num<= 1&& number == -1){
            Toast.makeText(mContext, "已经最小了",Toast.LENGTH_SHORT).show();
            return;
        }else {
            num+= number;
        }

        int finalNum = num;
        GoodsPresenter.cartNumUpdate(new ProgressDialogSubscriber<HttpResult>(mContext)
        {

            @Override
            public void onNext(HttpResult httpResult) {
                Log.e("CartGoodsAdapter", httpResult.toString());

                int status= httpResult.getStatus();
                switch (status){
                    case 0:
                        Intent intent = new Intent();
                        intent.setAction("");
                        mContext.sendBroadcast(intent);
                        break;
                    case 1:
                        Toast.makeText(mContext,"修改失败",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }, cartId, num);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private static class MyHolder extends RecyclerView.ViewHolder{

        private TextView tv_title, tv_price, tv_num;
        private ImageView iv_image;
        private ImageButton ib_add, ib_reduce;


        public MyHolder(View itemView) {
            super(itemView);


           tv_title=  itemView.findViewById(R.id.cart_name);
            tv_price=  itemView.findViewById(R.id.cart_price);
            tv_num  =  itemView.findViewById(R.id.cart_list_number);

            ib_add =itemView.findViewById(R.id.cart_add);
            ib_reduce =itemView.findViewById(R.id.cart_reduce);
        }



        private void updateNumber( CartGoodsEntity  entity, int number){
            int cartId= entity.getCart_id();
            int num = entity.getGoods_num();

        }
    }
}