package com.example.wangmengyun.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightLab;
import com.example.wangmengyun.activity.FlightActivityFragment;

import com.example.wangmengyun.activity.FlightListActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wangmengyun on 2018/3/28.
 */

public class FlightListFragment extends Fragment {


    private static final String ARG_CITY = "departureCity";

    public static final String EXTRA_DEPARTURE = "NULL";
    private static final int REQUEST_DEPARTURE = 1;
    private RecyclerView mFlightRecyclerView;

    private FlightAdapter mFlightAdapter;
//        private com.example.wangmengyun.activity.FlightActivityFragment.FlightAdapter mFlightAdapter;

//    Flight[] flight = {
//            new Flight("Shanghai", "Beijing", "2017/12/21", "2017/12/22"),
//            new Flight("Shanghai", "Seattle", "2018/03/23", "20180324"),
//            new Flight("NewYork", "Shanghai", "2018/03/03", "2018/03/04"),
//
//    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);


        mFlightRecyclerView = (RecyclerView) view.findViewById(R.id.flight_recycler_view);

        mFlightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }
//
//        private class FlightHolder extends RecyclerView.ViewHolder {j
//
//            public FlightHolder(LayoutInflater inflater, ViewGroup parent) {
//
//                super(inflater.inflate(R.layout.list_flight, parent, false));
//            }
//        }
//
//
//        private class FlightAdapter extends RecyclerView.Adapter<com.example.wangmengyun.activity.FlightActivityFragment.FlightHolder> {
//
//            private List<Flight> mFlights;
//
//            private FlightAdapter(List<Flight> flights) {
//                mFlights = flights;
//            }
//
//            @Override
//            public com.example.wangmengyun.activity.FlightActivityFragment.FlightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//
//                return new com.example.wangmengyun.activity.FlightActivityFragment.FlightHolder(layoutInflater, parent);
//            }
//
//            @Override
//            public void onBindViewHolder(com.example.wangmengyun.activity.FlightActivityFragment.FlightHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return mFlights.size();


    private void updateUI() {

        FlightLab flightLab = FlightLab.get(getActivity());
        List<Flight> flights = flightLab.getFlight();

        mFlightAdapter = new FlightAdapter(flights);
        mFlightRecyclerView.setAdapter(mFlightAdapter);


    }

    private class FlightAdapter extends RecyclerView.Adapter<FlightHolder> {

        private List<Flight> mFlights;

        public FlightAdapter(List<Flight> flights) {
            mFlights = flights;
        }


        @Override
        public FlightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new FlightHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(FlightHolder holder, int position) {

            Flight flight = mFlights.get(position);
            holder.bind(flight);

        }

        @Override
        public int getItemCount() {
            return mFlights.size();
        }
    }

    private class FlightHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mDepartCityTextView;
        private TextView mArriveCityTextView;

        private Flight mFlight;


        public FlightHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_flight, parent, false));

            mDepartCityTextView = itemView.findViewById(R.id.departure_City);

            mDepartCityTextView.setOnClickListener(this);

            mArriveCityTextView = itemView.findViewById(R.id.arrive_City);

            mArriveCityTextView.setOnClickListener(this);


        }


        public void bind(Flight flight) {
            mFlight = flight;
            mDepartCityTextView.setText(mFlight.getDeparture_City());

            mArriveCityTextView.setText(mFlight.getArrive_City());

        }

        @Override
        public void onClick(View v) {
  //          Toast.makeText(getActivity(), mFlight.getDeparture_City()+ "到达",Toast.LENGTH_SHORT).show();

//           FragmentManager fm = getFragmentManager();
//            FlightListFragment dialog= FlightListFragment.newInstance(mFlight.getDeparture_City());
//
//
            Intent intent = new Intent();
            intent.putExtra( "Departure_city", mFlight.getDeparture_City() );

            intent.putExtra("Arrive_city", mFlight.getArrive_City());

            getActivity().setResult(Activity.RESULT_OK,intent);
            getActivity().finish();



      //     sendResult(Activity.RESULT_OK, mFlight.getDeparture_City());
      //      returnResult();


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

    }

    public static FlightListFragment newInstance(String departure_city) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, departure_city);

        FlightListFragment fragment = new FlightListFragment();
        fragment.setArguments(args);

        return fragment;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode== REQUEST_DEPARTCITY){
//            //Handle result;
//        }
//    }

    public void returnResult(){

        getActivity().setResult(Activity.RESULT_OK,null);
    }
}