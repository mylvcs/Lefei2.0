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
import com.example.wangmengyun.Fragment.SearchFlightFragment;
import com.example.wangmengyun.activity.MainActivity;
//import com.example.wangmengyun.activity.PickCityActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;


public class HotCityAdapter extends BaseAdapter implements OnClickListener {

    private List<City> mHotCityList;
    private LayoutInflater mInflater;
    private Context mContext;


    private OnClickMyTextView mOnClickMyTextView;


    public HotCityAdapter(Context context, List<City> hotCityList) {
        this.mHotCityList = hotCityList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public void onClick(View v) {

    }

    public interface OnClickMyTextView {//创建一个接口类

        void myTextViewClick(int id);

    }

    public void setOnClickMyTextView(OnClickMyTextView onClickMyTextView){
        this.mOnClickMyTextView = onClickMyTextView;
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

        if (mOnClickMyTextView != null) {
            viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mHotCityList.get(position).getName() + "", 0).show();
                    //TODO
//                String city = mHotCityList.get(position).getName();
//
//                Intent intent = new Intent(mContext,)

                    Intent intent = new Intent (mContext, SearchFlightActivity.class);

                    intent.putExtra(Intent.EXTRA_TEXT, mHotCityList.get(position).getName());

                    mContext.startActivity(intent);

                    mOnClickMyTextView.myTextViewClick(position);


                }

            });

        }
        viewHolder.tvCityName.setText(mHotCityList.get(position).getName());

        return convertView;
    }

    class ViewHolder {
        TextView tvCityName;
    }


//    public static abstract class MyClickListener implements OnClickListener {
//        /**
//         * 基类的onClick方法
//         */
//        @Override
//        public void onClick(View v) {
//            myOnClick((Integer) v.getTag(), v);
//        }
//
//        public abstract void myOnClick(int position, View v);
//    }


}


