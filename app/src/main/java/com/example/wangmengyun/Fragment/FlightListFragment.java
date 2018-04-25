package com.example.wangmengyun.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.database.FlightFetchr;
import com.example.wangmengyun.lefei.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private List<Flight> mFlights = new ArrayList<>();

    private static final String TAG = "FlightListFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        new FetchFlights().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_flight, container, false);

        mRecyclerView = v.findViewById(R.id.flight_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter();

        return v;

    }

    private void setupAdapter() {

        if (isAdded()){
            mRecyclerView.setAdapter(new FlightAdapter(mFlights));
        }
    }

    private class FlightHolder extends RecyclerView.ViewHolder{

        private TextView mFlightDescriptionTextView;

        private TextView mDepartureTextView;

        private TextView mArrivalTextView;


        public FlightHolder(View view ) {
            super(view);

           mFlightDescriptionTextView= view.findViewById(R.id.zhida);

           mDepartureTextView = view.findViewById( R.id.flight_duration);

           mArrivalTextView = view.findViewById(R.id.airline_description);

        }

        public void bindItem(Flight flightitem){

            mFlightDescriptionTextView.setText(flightitem.getDeparture_Date());

            mDepartureTextView.setText(flightitem.getDeparture_City());

            mArrivalTextView.setText(flightitem.getArrive_City());
        }
    }

    private class FlightAdapter extends RecyclerView.Adapter<FlightHolder>{

        private List<Flight> mFlightItems;

        private Context mContext;

        public FlightAdapter(List<Flight> flightItems){

            mFlightItems = flightItems;
        }

        @NonNull
        @Override
        public FlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.forecast_list_item, parent, false);

            return new FlightHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FlightHolder holder, int position) {

            Flight flightItem = mFlightItems.get(position);

            holder.bindItem(flightItem);

        }

        @Override
        public int getItemCount() {
            return mFlightItems.size();
        }
    }

    private class FetchFlights extends AsyncTask<Void, Void, List<Flight>>{

        @Override
        protected List<Flight> doInBackground(Void... voids) {

//            try {
//                String result = new FlightFetchr().getUrlString("https://www.bignerdranch.com");
//
//                Log.i(TAG,result);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

          return   new FlightFetchr().fetchItems();
        }

        protected void onPostExecute(List<Flight> items){
            mFlights = items;
            setupAdapter();
        }
    }

}