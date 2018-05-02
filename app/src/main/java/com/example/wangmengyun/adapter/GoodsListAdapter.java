package com.example.wangmengyun.adapter;

/**
 * Created by wangmengyun on 2018/5/2.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.data.Goods;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.lefei.R;

import java.util.List;



public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder>
        implements View.OnClickListener {

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private Context mContext;

    private List<GoodsEntity> goods;

    private String[] mFlightData;

    public OnGoodsItemClickListener mGoodsItemListener;


    @Override
    public void onClick(View v) {

    }

    public interface FlightListAdapterClickHandler {
        void onClick(String Flightdate);
    }


    public GoodsListAdapter(@NonNull Context context, List<GoodsEntity> data) {
        mContext = context;
        this.goods = data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        private TextView goodsName;
        private TextView price;

        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.flight_icon);
            goodsName = (TextView) view.findViewById(R.id.zhida);

            price = view.findViewById(R.id.ticket_price);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mGoodsItemListener!= null){
                mGoodsItemListener.onClick(v, (GoodsEntity) v.getTag());
            }
        }


    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.forecast_list_item, viewGroup, false);

        view.setOnClickListener(this);

        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    public interface OnGoodsItemClickListener{
        void onClick(View view, GoodsEntity entity);
    }


    @Override
    public void onBindViewHolder(@NonNull GoodsListAdapter.ViewHolder holder, int position) {
        GoodsEntity good = goods.get(position);
    }


    @Override
    public int getItemCount() {

        return goods.size();
    }
}