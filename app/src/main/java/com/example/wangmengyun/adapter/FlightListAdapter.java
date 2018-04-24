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


public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ForecastAdapterViewHolder>
implements View.OnClickListener {

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private final Context mContext;

    private List<Flight> flights;

    private String[] mFlightData;

    final private FlightListAdapterClickHandler mClickHandler;

    @Override
    public void onClick(View v) {

    }

    public interface FlightListAdapterClickHandler {
        void onClick(String Flightdate);
    }


    public FlightListAdapter(@NonNull Context context, FlightListAdapterClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }


    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView iconView;

        final TextView dateView;
        final TextView descriptionView;
        final TextView highTempView;
        final TextView lowTempView;

        ForecastAdapterViewHolder(View view) {
            super(view);

            iconView = (ImageView) view.findViewById(R.id.weather_icon);
            dateView = (TextView) view.findViewById(R.id.zhida);
            descriptionView = (TextView) view.findViewById(R.id.flight_duration);
            highTempView = (TextView) view.findViewById(R.id.flight_date);
            lowTempView = (TextView) view.findViewById(R.id.airline_description);

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            String weatherForDay = mFlightData[adapterPosition];

            mClickHandler.onClick(weatherForDay);
        }

    }


    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.forecast_list_item,viewGroup,false);

        view.setOnClickListener(this);

        ForecastAdapterViewHolder holder = new ForecastAdapterViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder holder, int position) {

        Flight flight  = flights.get(position);

        holder.dateView.setText(flight.getDate().toString());

        holder.descriptionView.setText(flight.getDeparture_City());

        holder.highTempView.setText(flight.getArrive_City());

        holder.lowTempView.setText(flight.getPrice());


    }

    @Override
    public int getItemCount() {
        if (null == mFlightData) return 0;

        return mFlightData.length;
    }
}