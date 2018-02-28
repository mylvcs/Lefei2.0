package com.wangmengyun.lefei2;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wangmengyun on 2018/2/28.
 */

public class FlightFragment extends Fragment {

    private Flight mFlight;
    private EditText mDeparture;
    private EditText mArrive;
    private Button mSearchFlight;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFlight = new Flight();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         View v = inflater.inflate(R.layout.activity_flight, container,false);

         mDeparture = (EditText) v.findViewById(R.id.departure);

         mDeparture.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 //Todo
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 //Todo
             }

             @Override
             public void afterTextChanged(Editable editable) {
                //Todo
             }
         });

         mArrive = (EditText) v.findViewById(R.id.arrive);

         mSearchFlight = (Button) v.findViewById(R.id.search_flight);


         return v;

    }



}
