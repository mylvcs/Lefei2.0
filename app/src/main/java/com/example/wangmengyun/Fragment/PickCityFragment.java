package com.example.wangmengyun.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Bean.CityLab;
import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightLab;
import com.example.wangmengyun.lefei.R;

import java.util.List;

/**
 * Created by wangmengyun on 2018/4/1.
 *
 * 总共四个组成部分：1 搜索框 2 定位城市 3 搜索历史城市 4 热门城市
 *
 */

public class PickCityFragment extends Fragment {

    private static final String ARG_CITY = "departureCity";
    public static final String EXTRA_DEPARTURE = "NULL";

    private static final int REQUEST_DEPARTURE = 1;

    private RecyclerView mCityRecyclerView;

    private CityAdapter mCityAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        mCityRecyclerView = view.findViewById(R.id.city_recycler_view);

        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI() {

        CityLab cityLab = CityLab.get(getActivity());
        List<City> cities = cityLab.getCity();

        mCityAdapter = new CityAdapter(cities);
        mCityRecyclerView.setAdapter(mCityAdapter);

    }


    private class CityAdapter extends RecyclerView.Adapter<CityHolder>{

        private List<City> mCities;

        public CityAdapter(List<City> cities){
            mCities = cities;
        }

        @Override
        public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CityHolder(layoutInflater,parent);

        }

        @Override
        public void onBindViewHolder(CityHolder holder, int position) {

            City city = mCities.get(position);

            holder.bind(city);

        }

        @Override
        public int getItemCount() {
            return mCities.size();
        }
    }


    private class CityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCityNameTextView;

        private City mCity;


        private TextView mDepartCityTextView;


        public CityHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_city, parent, false));

            mCityNameTextView = itemView.findViewById(R.id.tv_city_name);

            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
         //         Toast.makeText(getActivity(), mCity.getName()+ "到达",Toast.LENGTH_SHORT).show();


            //     sendResult(Activity.RESULT_OK, mFlight.getDeparture_City());
            //      returnResult();
            Intent intent = new Intent();
            intent.putExtra( "Departure_city", mCity.getName() );

     //       intent.putExtra("Arrive_city", mCity.getName());

            getActivity().setResult(Activity.RESULT_OK,intent);
            getActivity().finish();


//
//            Intent intent = SearchFlightActivity.newIntent(getActivity(), mFlight.getDeparture_City());
//            startActivityForResult(intent, REQUEST_DEPARTCITY);


        }

        private void sendResult(int resultOk, String DepartCity) {

            if (getTargetFragment() == null) {
                return;
            }
            Intent in = new Intent();
            in.putExtra(EXTRA_DEPARTURE, DepartCity);

            getTargetFragment().onActivityResult(getTargetRequestCode(), resultOk, in);
        }

        public void bind(City city) {
            mCity = city;

            mCityNameTextView.setText(mCity.getName());
        }
    }

}