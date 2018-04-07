package com.example.wangmengyun.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightLab;
import com.example.wangmengyun.activity.FlightListActivity;
import com.example.wangmengyun.activity.MainActivity;
import com.example.wangmengyun.activity.PickCityActivity1;
import com.example.wangmengyun.activity.PickCityActivity2;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;

import java.util.Date;
import java.util.UUID;


/**
 * Created by wangmengyun on 2018/3/30.
 */

public class SearchFlightFragment extends Fragment {
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DEPARTURE = 0;
    private static final int REQUEST_ARRIVE = 2;

    private static final int REQUEST_DATE = 1;

    private static final String arrive_city = "arriveCity";

    private static final String EXTRA_DEPART_CITY= "DepartCity";
    private static final String ARG_FLIGHT_ID = "flight_id";

    private static final String ARG_CITY = "null";
    /**
     *
     *声明组件
     */
    private Button mSearchFlightButton;
    private Button mDepartureButton;

    private Button mArriveButton;

    private Button mDateButton;

    private Flight mFlight;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UUID flightNumber = (UUID) getArguments().getSerializable(ARG_FLIGHT_ID);

        mFlight = new Flight("Shanghai");

//            mFlight= (Flight) FlightLab.get(getActivity()).getFlight();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_searchflight, container, false);

        mSearchFlightButton = v.findViewById(R.id.btn_search);

        mDepartureButton = v.findViewById(R.id.departure);

        mArriveButton = v.findViewById(R.id.arrive);

        mSearchFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });
//
        mDepartureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), PickCityActivity1.class);
                startActivityForResult(in, REQUEST_DEPARTURE);

            }
        });

        mDepartureButton.setText("出发城市");

  //     mDepartureButton.setText(mFlight.getDeparture_City());

        mArriveButton.setText("到达城市");

        mArriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               FragmentManager fragmentManager = getFragmentManager();
//               FlightListFragment flightListFragment = new FlightListFragment();

                Intent in = new Intent(getActivity(),PickCityActivity2.class);
                startActivityForResult(in, REQUEST_ARRIVE);

            }
        });


        mDateButton = v.findViewById(R.id.flight_date);
        mDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mFlight.getDate());

                dialog.setTargetFragment(SearchFlightFragment.this, REQUEST_DATE);

                dialog.show(manager,DIALOG_DATE);

            }
        });

        updateDate();

        return v;

    }
    public static Intent newIntent (Context context, String departCity){
        Intent in = new Intent(context, SearchFlightActivity.class);
        in.putExtra(EXTRA_DEPART_CITY, departCity);

        return in;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DEPARTURE) {
            String departureCity = (String) intent.getSerializableExtra(FlightListFragment.EXTRA_DEPARTURE);

            mFlight.setDeparture_City(departureCity);

            mDepartureButton.setText(intent.getStringExtra("Departure_city"));


        }
        else if(requestCode == REQUEST_DATE){

            Date date = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mFlight.setDate(date);

            updateDate();

        }else if (requestCode == REQUEST_ARRIVE){

                String arriveCity = (String) intent.getSerializableExtra(FlightListFragment.EXTRA_ARRIVE);

                mFlight.setArrive_City(arriveCity);

                mArriveButton.setText(intent.getStringExtra("Arrive_city"));

            }

    }

    private void updateDate() {

        mDateButton.setText(mFlight.getDate().toString());

    }

    public void onPause(){
        super.onPause();

        FlightLab.get(getActivity()).updateFlight(mFlight);
    }

    public static SearchFlightFragment newInstance(UUID mID) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_FLIGHT_ID,mID);

        SearchFlightFragment fragment = new SearchFlightFragment();
        fragment.setArguments(args);

        return fragment;
    }


}
