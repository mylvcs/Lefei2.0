package com.example.wangmengyun.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Utils.SunshineDateUtils;
import com.example.wangmengyun.Utils.SunshineWeatherUtils;
import com.example.wangmengyun.activity.FlightActivity;

import com.example.wangmengyun.lefei.R;

import java.util.List;


public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ViewHolder>
implements View.OnClickListener {

    private  Context mContext;

    private List<Flight> ListData;

    private String[] mFlightData;

    // final private FlightListAdapterClickHandler mClickHandler;

    // @Override
    // public void onClick(View v) {

    // }

    public interface OnFlightItemClickListener {
        void onClick(View view, Flight flight);
    }

    public void setOnFlightItemClickListener(OnFlightItemClickListener listener){
        this.mFlightItemListener = listener;
    }

    public OnFlightItemClickListener mFlightItemListener;

    public FlightListAdapter(@NonNull Context context, List<Flight> data) {
        mContext = context;
        ListData = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView iconView;

        private TextView zhida;
        private TextView flight_duration;
        private TextView price;
        private TextView airlineCompany;

        public ViewHolder(View view) {
            super(view);

            iconView = (ImageView) view.findViewById(R.id.flight_icon);
            zhida = (TextView) view.findViewById(R.id.zhida);

            flight_duration = (TextView) view.findViewById(R.id.flight_duration);

            price = view.findViewById(R.id.ticket_price);
            airlineCompany = (TextView) view.findViewById(R.id.airline_description);

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mFlightItemListener!= null){
                mFlightItemListener.onClick(v, v.getTag());
            }
            // int adapterPosition = getAdapterPosition();

// String weatherForDay = mFlightData[adapterPosition];

            // mClickHandler.onClick(weatherForDay);
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.forecast_list_item,viewGroup,false);

        view.setOnClickListener(this);

        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Flight flight  = flights.get(position);

       holder.flight_duration.setText(flight.getDeparture_City());

       holder.zhida.setText(flight.getArrive_Date());

       holder.price.setText(flight.getPrice());


    }

    @Override
    public int getItemCount() {
        if (null == mFlightData) return 0;

        return ListData.size();
    }
}