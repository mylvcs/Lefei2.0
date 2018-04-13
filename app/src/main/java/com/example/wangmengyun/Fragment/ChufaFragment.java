package com.example.wangmengyun.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Bean.LocateState;
import com.example.wangmengyun.Fragment.SearchFlightFragment;
import com.example.wangmengyun.Utils.StringUtils;
import com.example.wangmengyun.Utils.ToastUtils;
import com.example.wangmengyun.activity.ChufaActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.ResultListAdapter;
import com.example.wangmengyun.database.DBManager;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.view.SlideBar;

import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ChufaFragment extends Fragment implements View.OnClickListener {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener;

    private ListView mListview;
    private ListView mResultListView;
    private SlideBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;

    private int REQUEST_DEPARTURE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.activity_city, container,false);

            mListview = (ListView) v.findViewById(R.id.listview_all_city);
            mListview.setAdapter(mCityAdapter);

            TextView overlay = (TextView)v. findViewById(R.id.tv_letter_overlay);
            mLetterBar = (SlideBar)v. findViewById(R.id.side_letter_bar);
            mLetterBar.setOverlay(overlay);
            mLetterBar.setOnLetterChangedListener(new SlideBar.OnLetterChangedListener() {
                @Override
                public void onLetterChanged(String letter) {
                    int position = mCityAdapter.getLetterPosition(letter);
                    mListview.setSelection(position);
                }
            });
            emptyView = (ViewGroup)v. findViewById(R.id.empty_view);
            searchBox = (EditText) v.findViewById(R.id.et_search);
            searchBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String keyword = s.toString();
                    if (TextUtils.isEmpty(keyword)) {
                        clearBtn.setVisibility(View.GONE);
                        emptyView.setVisibility(View.GONE);
                        mResultListView.setVisibility(View.GONE);
                    } else {
                        clearBtn.setVisibility(View.VISIBLE);
                        mResultListView.setVisibility(View.VISIBLE);
                        List<City> result = dbManager.searchCity(keyword);
                        if (result == null || result.size() == 0) {
                            emptyView.setVisibility(View.VISIBLE);
                        } else {
                            emptyView.setVisibility(View.GONE);
                            mResultAdapter.changeData(result);
                        }
                    }
                }
            });


            mResultListView = (ListView)v. findViewById(R.id.listview_search_result);
            mResultListView.setAdapter(mResultAdapter);
            mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    back(mResultAdapter.getItem(position).getName());
                }
            });

            clearBtn = (ImageView)v. findViewById(R.id.iv_search_clear);
            backBtn = (ImageView)v. findViewById(R.id.back);

            clearBtn.setOnClickListener(this);
            backBtn.setOnClickListener(this);

        return v;
    }

//    private void initLocation() {
//        myListener = new MyLocationListener();
//        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);    //注册监听函数
//
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
//        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
////        int span = 1000;
////        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
//        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
//
//    }

    private void initData() {
        dbManager = new DBManager(getActivity());
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(getActivity(), mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {

            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                Log.e("onLocateClick", "重新定位...");
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.start();
            }
        });

        mResultAdapter = new ResultListAdapter(getActivity(), null);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_clear:
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.back:
                getActivity().finish();
                break;
        }
    }

    private void back(String cityName) {
 //       ToastUtils.showToast(getActivity(), "点击的城市：" + cityName);

        Intent in = SearchFlightActivity.newIntent(getActivity(),cityName);

//        in.putExtra("Departure_city", cityName);
//
//        getActivity().setResult(RESULT_OK, in);

        startActivityForResult(in, REQUEST_DEPARTURE);

    }

//    private void sendResult(int resultOk, String  city) {
//
//        Intent intent = new Intent();
//        intent.putExtra("Departure_city", city);
//
//        onActivityResult(REQUEST_DEPARTURE, resultOk, intent);
//    }

//    public class MyLocationListener implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //Receive Location
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// 单位：公里每小时
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// 单位：米
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());// 单位度
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ncity : ");
//                sb.append(location.getCity()+"  aa");
//                sb.append("\ndisc : ");
//                sb.append(location.getDistrict()+"  bb");
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//                String city = location.getCity();
//                String district = location.getDistrict();
//                Log.e("onLocationChanged", "city: " + city);
//                Log.e("onLocationChanged", "district: " + district);
//                String location1 = StringUtils.extractLocation(city, district);
//                mCityAdapter.updateLocateState(LocateState.SUCCESS, location1);
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //运营商信息
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                //定位失败
//                mCityAdapter.updateLocateState(LocateState.FAILED, null);
//            }
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            List<Poi> list = location.getPoiList();// POI数据
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//            Log.i("BaiduLocationApiDem", sb.toString());
//        }

}

