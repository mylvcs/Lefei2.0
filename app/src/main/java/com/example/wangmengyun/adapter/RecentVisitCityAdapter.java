package com.example.wangmengyun.adapter;

import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;

public class RecentVisitCityAdapter extends BaseAdapter {

    private List<String> mRecentVisitCityList;
    private LayoutInflater mInflater;
    private Context mContext;


//    private SubClickListener subClickListener;
//
//
//    public void setsubClickListener(HotCityAdapter.SubClickListener topicClickListener) {
//        this.subClickListener = (SubClickListener) topicClickListener;
//    }
//
//    public interface SubClickListener {
//        void OntopicClickListener(View v, City city, int position);
//    }


    public RecentVisitCityAdapter(Context context, List<String> recentVisitCityList, MyClickListener listener) {
        this.mRecentVisitCityList=recentVisitCityList;
        this.mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mRecentVisitCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecentVisitCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_city,null);
            viewHolder.tvCityName=(TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tvCityName.setText(mRecentVisitCityList.get(position));


        viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext,mRecentVisitCityList.get(position)+"",0).show();

                Intent intent = new Intent (mContext, SearchFlightActivity.class);

                intent.putExtra(Intent.EXTRA_TEXT, mRecentVisitCityList.get(position));

                mContext.startActivity(intent);

            }
        });
        return convertView;
    }

    class ViewHolder{
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
