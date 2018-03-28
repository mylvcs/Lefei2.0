//package com.example.wangmengyun.adapter;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.wangmengyun.Bean.Flight;
//import com.example.wangmengyun.Bean.FlightLab;
//import com.example.wangmengyun.lefei.R;
//
//import java.util.List;
//
//public class CityListAdapter extends Fragment{
//
//
//    private RecyclerView mFlightRecyclerView;
//
//    private FlightAdapter mFlightAdapter;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_flight_list,container,false);
//
//        mFlightRecyclerView = view.findViewById(R.id.flight_recycler_view);
//
//        mFlightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//
//        return view;
//
//        updateUI();
//
//
//
//    }
//
//    private void updateUI() {
//        FlightLab flightLab = FlightLab.get(getActivity());
//
//        List<Flight> flights = flightLab.getFlights();
//
//        mFlightAdapter = new FlightAdapter(flights);
//
//        mFlightRecyclerView.setAdapter(mFlightAdapter);
//
//    }
//}