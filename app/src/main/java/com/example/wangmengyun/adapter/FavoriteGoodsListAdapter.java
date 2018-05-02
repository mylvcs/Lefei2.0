package com.example.wangmengyun.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;
import com.example.wangmengyun.lefei.R;

import java.util.List;


/**
 * Created by wangmengyun on 2018/4/27.
 */

public class FavoriteGoodsListAdapter extends RecyclerView.Adapter<FavoriteGoodsListAdapter.ViewHolder> implements View.OnClickListener {


    private Context mContext;
    private List<FavoriteGoodsEntity> listData;


    public OnGoodsItemClickListener mGoodsItemListener;

    public FavoriteGoodsListAdapter(Context context, List<FavoriteGoodsEntity> data){
        mContext = context;
        this.listData  = data;

    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_list, parent, false);

        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteGoodsListAdapter.ViewHolder holder, int position) {

        FavoriteGoodsEntity entity = listData.get(position);

        holder.title.setText(entity.getClass().getName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public void onClick(View v) {

        if(mGoodsItemListener!=null){
            mGoodsItemListener.onClick(v, (FavoriteGoodsEntity) v.getTag());
        }
    }

    public interface OnGoodsItemClickListener {
        void onClick(View view, FavoriteGoodsEntity entity);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView price;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.goodslist_image);
            title = itemView.findViewById(R.id.goodslist_name);
            price = itemView.findViewById(R.id.goodslist_price);
        }
    }
}

