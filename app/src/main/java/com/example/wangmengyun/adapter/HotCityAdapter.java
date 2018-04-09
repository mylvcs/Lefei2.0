package com.example.wangmengyun.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.activity.MainActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;

public class HotCityAdapter extends BaseAdapter implements OnClickListener {

    private List<City> mHotCityList;
    private LayoutInflater mInflater;
    private Context mContext;


    private SubClickListener subClickListener;


    public HotCityAdapter(Context context, List<City> hotCityList, HotCityAdapter.MyClickListener listener) {
        this.mHotCityList = hotCityList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setsubClickListener(SubClickListener topicClickListener) {
        this.subClickListener = topicClickListener;
    }

    @Override
    public void onClick(View v) {

    }

    public interface SubClickListener {
        void OntopicClickListener(View v, City city, int position);
    }


    @Override
    public int getCount() {
        return mHotCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_city, null);
            viewHolder.tvCityName = (TextView) convertView
                    .findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvCityName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (subClickListener != null) {
                    subClickListener.OntopicClickListener(v, mHotCityList.get(position), position);
                }

            }
        });

        viewHolder.tvCityName.setText(mHotCityList.get(position).getName());

        return convertView;
    }

    class ViewHolder {
        TextView tvCityName;
    }


    public static abstract class MyClickListener implements OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }

}


