package com.example.wangmengyun.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by wangmengyun on 2018/3/28.
 */

public class FlightListFragment extends Fragment {


    private RecyclerView mFlightRecyclerView;

    private FlightAdapter mFlightAdapter;
//        private com.example.wangmengyun.activity.FlightActivityFragment.FlightAdapter mFlightAdapter;

//    Flight[] flight = {
//            new Flight("Shanghai", "Beijing", "2017/12/21", "2017/12/22"),
//            new Flight("Shanghai", "Seattle", "2018/03/23", "20180324"),
//            new Flight("NewYork", "Shanghai", "2018/03/03", "2018/03/04"),
//
//    };

    public FlightListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);

        mFlightRecyclerView = (RecyclerView)view.findViewById(R.id.flight_recycler_view);

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

        FlightLab flightLab= FlightLab.get(getActivity());
        List<Flight> flights = flightLab.getFlights();
        
        mFlightAdapter = new FlightAdapter(flights);
        mFlightRecyclerView.setAdapter(mFlightAdapter);
        
        
    }

    private class FlightAdapter extends RecyclerView.Adapter<FlightHolder>{

            private List<Flight> mFlights;
            
            public FlightAdapter(List<Flight> flights){
                mFlights = flights;
            }


        @Override
        public FlightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            
            return new FlightHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(FlightHolder holder, int position) {

            Flight flight =mFlights.get(position);
            holder.bind(flight);

        }

        @Override
        public int getItemCount() {
            return mFlights.size();
        }
    }


    private class FlightHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mDepartCityTextView;

        private Flight mFlight;



        public FlightHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_flight, parent,false));

            mDepartCityTextView = itemView.findViewById(R.id.departure_City);

            mDepartCityTextView.setOnClickListener(this);

        }


        public void bind(Flight flight) {
            mFlight = flight;
            mDepartCityTextView.setText(mFlight.getDeparture_City());

        }

        @Override
        public void onClick(View v) {
        //    Toast.makeText(getActivity(), mFlight.getDeparture_City()+ "到达",Toast.LENGTH_SHORT).show();


//            Intent intent = FlightListActivity.newIntent(getActivity(), mFlight.getArrive_City());
//
//            startActivity(intent);


            Intent intent = new Intent(getActivity(),SearchFlightActivity.class);

            intent.putExtra(Intent.EXTRA_TEXT, mFlight.getDeparture_City() );

            startActivity(intent);
        }
    }
}
