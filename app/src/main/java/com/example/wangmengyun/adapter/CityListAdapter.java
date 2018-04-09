package com.example.wangmengyun.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Fragment.PickCityFragment;
import com.example.wangmengyun.activity.ChufaActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.view.MyGridView;


public class CityListAdapter extends BaseAdapter {

    private Context mContext;
    private List<City> mAllCityList;
    private List<City> mHotCityList;
    private List<String> mRecentCityList;
    public HashMap<String, Integer> alphaIndexer;
    private String[] sections;
    private LocationClient myLocationClient;
    private String currentCity;
    private MyLocationListener myLocationListener;
    private boolean isNeedRefresh;
    private TextView tvCurrentLocateCity;
    private ProgressBar pbLocate;
    private TextView tvLocate;
    private OnClickListener mListener;
    private final int VIEW_TYPE = 5;

    public CityListAdapter(Context context, List<City> allCityList,
                           List<City> hotCityList, List<String> recentCityList) {
        this.mContext = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
        this.mRecentCityList=recentCityList;
    }

    @Override
    public int getViewTypeCount() {

        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return mAllCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)  {

        ViewHolder viewHolder = null;
        int viewType = getItemViewType(position);

        if (viewType == 0) {
            convertView = View.inflate(mContext, R.layout.item_location_city,
                    null);
            tvLocate=(TextView) convertView.findViewById(R.id.tv_locate);
            tvCurrentLocateCity=(TextView) convertView.findViewById(R.id.tv_current_locate_city);
            pbLocate = (ProgressBar) convertView.findViewById(R.id.pb_loacte);

            if(!isNeedRefresh){
                tvLocate.setText("正在定位");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText(currentCity);
                pbLocate.setVisibility(View.GONE);
            }else{
                myLocationClient.start();
            }

            tvCurrentLocateCity.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    pbLocate.setVisibility(View.VISIBLE);
                    tvLocate.setText("定位城市");
                    tvCurrentLocateCity.setVisibility(View.GONE);
                    myLocationClient.start();
                }
            });

        } else if (viewType == 1) {
            convertView = View.inflate(mContext,R.layout.item_recent_visit_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.tv_recent_visit_city);
            tvRecentVisitCity.setText("最近浏览城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.gv_recent_visit_city);
            gvRecentVisitCity.setAdapter(new RecentVisitCityAdapter(mContext,mRecentCityList));

        } else if (viewType == 2) {
            convertView = View.inflate(mContext,R.layout.item_recent_visit_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.tv_recent_visit_city);
            tvRecentVisitCity.setText("hot城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.gv_recent_visit_city);
            gvRecentVisitCity.setAdapter(new HotCityAdapter(mContext,mHotCityList, (HotCityAdapter.MyClickListener) mListener));
        } else if (viewType == 3) {
            convertView = View.inflate(mContext,R.layout.item_all_city_textview, null);
        } else {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_city_list,null);
                viewHolder.tvAlpha = (TextView) convertView.findViewById(R.id.tv_alpha);
                viewHolder.tvCityName = (TextView) convertView.findViewById(R.id.tv_city_name);
                viewHolder.llMain=(LinearLayout) convertView.findViewById(R.id.ll_main);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position >= 1) {
                viewHolder.tvCityName.setText(mAllCityList.get(position).getName());

                viewHolder.llMain.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //TODO
//                        String City = (String) getItem(position);
//
//                        Intent intent = ChufaActivity.newIntent(,City);
//                        startActivity(intent);

                        Toast.makeText(mContext,mAllCityList.get(position).getName(),0).show();

                    }
                });

            }

        }

        return convertView;
    }


    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);

        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "";
        } else if (str.equals("1")) {
            return "";
        } else if (str.equals("2")) {
            return "";
        } else if (str.equals("3")) {
            return "";
        } else {
            return "#";
        }
    }

    class ViewHolder {
        TextView tvAlpha;
        TextView tvCityName;
        LinearLayout llMain;
    }

    public void initLocation() {
        myLocationClient = new LocationClient(mContext);
        myLocationListener=new MyLocationListener();
        myLocationClient.registerLocationListener(myLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setScanSpan(10000);

        option.setAddrType("all");

        option.setPoiExtraInfo(true);

        option.setProdName("ͨ��GPS��λ�ҵ�ǰ��λ��");

        option.disableCache(true);

        option.setPoiNumber(3);

        option.setPriority(LocationClientOption.GpsFirst);
        myLocationClient.setLocOption(option);
        myLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation arg0) {

            isNeedRefresh=false;
            if(arg0.getCity()==null){
                //��λʧ��
                tvLocate.setText("定位城市");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText("附近城市");
                pbLocate.setVisibility(View.GONE);
                return;
            }else{

                currentCity=arg0.getCity().substring(0,arg0.getCity().length()-1);
                tvLocate.setText("定位城市");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText(currentCity);
                myLocationClient.stop();
                pbLocate.setVisibility(View.GONE);
            }
        }

        @Override
        public void onReceivePoi(BDLocation arg0) {

        }

    }

}
