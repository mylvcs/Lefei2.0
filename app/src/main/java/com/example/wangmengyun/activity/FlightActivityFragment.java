package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightContract;

import com.example.wangmengyun.lefei.R;

import java.util.Arrays;
import java.util.List;

/**
 * learned from Android Development book
   Crime List Fragment
 */
public class FlightActivityFragment extends Fragment {

    private RecyclerView mFlightRecyclerView;

    private FlightAdapter mFlightAdapter;

//    Flight[] flight = {
//            new Flight("Shanghai", "Beijing", "2017/12/21", "2017/12/22"),
//            new Flight("Shanghai", "Seattle", "2018/03/23", "20180324"),
//            new Flight("NewYork", "Shanghai", "2018/03/03", "2018/03/04"),
//
//    };

    public FlightActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight, container, false);

        mFlightRecyclerView = view.findViewById(R.id.flight_recycler_view);

        mFlightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }



    private class FlightHolder extends RecyclerView.ViewHolder {

        public FlightHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_flight, parent, false));
        }
    }


    private class FlightAdapter extends RecyclerView.Adapter<FlightHolder> {

        private List<Flight> mFlights;

        private FlightAdapter(List<Flight> flights) {
            mFlights = flights;
        }

        @Override
        public FlightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new FlightHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(FlightHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mFlights.size();
        }
    }
}

