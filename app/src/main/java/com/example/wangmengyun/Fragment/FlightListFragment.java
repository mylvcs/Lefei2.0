package com.example.wangmengyun.Fragment;

import android.content.Context;
import android.content.Intent;
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
import com.example.wangmengyun.activity.BoardingpassActivity;
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

        private TextView mAirlineDescription;

        private TextView parsedFlightData;

        private TextView mTicketPrice;


        public FlightHolder(View view ) {
            super(view);

           mAirlineDescription= view.findViewById(R.id.airline_description);

            parsedFlightData =  view.findViewById( R.id.flight_duration);

           mTicketPrice = view.findViewById(R.id.ticket_price);

        }

        public void bindItem(Flight flightitem){

            mAirlineDescription.setText(flightitem.getAirline());

            parsedFlightData.setText(flightitem.getParsedFlightData());

            mTicketPrice.setText(flightitem.getPrice());
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), BoardingpassActivity.class);

                    startActivity(intent);

                }
            });


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

          return   new FlightFetchr().fetchItems();
        }

        protected void onPostExecute(List<Flight> items){
            mFlights = items;
            setupAdapter();
        }
    }

}