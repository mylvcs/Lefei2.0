package com.example.wangmengyun.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightLab;
import com.example.wangmengyun.activity.ChufaActivity;
import com.example.wangmengyun.activity.DaodaActivity;
import com.example.wangmengyun.activity.MainActivity;

import com.example.wangmengyun.lefei.R;

import java.util.UUID;

/**
 * Created by wangmengyun on 2018/4/3.
 */

public class DaodaFragment extends Fragment{

    private static final String DIALOG_DATE = "DialogDate";


    private static final int REQUEST_ARRIVE = 0;

    private static final String arrive_city = "arriveCity";

    private static final String ARG_FLIGHT_ID = "flight_id";

    private static final String ARG_CITY = "null";
    private static final String EXTRA_ARRIVE_CITY = "ArriveCity";
    /**
     * 声明组件
     */
    private Button mSearchFlightButton;
    private Button mDepartureButton;

    private Button mArriveButton;

    private Button mDateButton;

    private Flight mFlight;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFlight = new Flight("Shanghai");

//            mFlight= (Flight) FlightLab.get(getActivity()).getFlight();

    }

//    public void onPause() {
//        super.onPause();
//
//        FlightLab.get(getActivity()).updateFlight(mFlight);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_searchflight, container, false);

        mSearchFlightButton = v.findViewById(R.id.btn_search);

        mArriveButton = v.findViewById(R.id.arrive);

        mSearchFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });

        mArriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                FragmentManager fm = getFragmentManager();
//                FlightListFragment dialog= FlightListFragment.newInstance(mFlight.getDeparture_City());
//
//                dialog
                Intent in = new Intent(getActivity(), PickCityFragment2.class);
                startActivityForResult(in, REQUEST_ARRIVE);


            }
        });

        mArriveButton.setText("到达城市");

        //     mDepartureButton.setText(mFlight.getDeparture_City());

//        mDateButton = v.findViewById(R.id.flight_date);
//        mDateButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//
//                DatePickerFragment dialog = DatePickerFragment.newInstance(mFlight.getDate());
//
//                dialog.setTargetFragment(DaodaFragment.this, REQUEST_DATE);
//
//                dialog.show(manager, DIALOG_DATE);
//
//            }
//        });
//
        return v;

    }

    public static Intent newIntent(Context context, String arriveCity) {
        Intent in = new Intent(context, DaodaActivity.class);
        in.putExtra(EXTRA_ARRIVE_CITY, arriveCity);

        return in;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_ARRIVE) {
            String arriveCity = (String) intent.getSerializableExtra(FlightListFragment.EXTRA_ARRIVE);

            mFlight.setArrive_City(arriveCity);

            mArriveButton.setText(intent.getStringExtra("Arrive_city"));

        }
    }


    public static DaodaFragment newInstance(UUID mID) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_FLIGHT_ID, mID);

        DaodaFragment fragment = new DaodaFragment();
        fragment.setArguments(args);

        return fragment;
    }


}