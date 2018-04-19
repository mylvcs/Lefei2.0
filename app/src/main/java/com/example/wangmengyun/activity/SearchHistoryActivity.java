package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.wangmengyun.Bean.CityPair;
import com.example.wangmengyun.lefei.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * Created by wangmengyun on 2018/4/19.
 */

public class SearchHistoryActivity extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/CityPair");
    private String TAG = "SearchHistory";


    private TextView  textViewOriginAirport;

    private TextView  textViewDestinationAirport;
    private static final String daodaCity = "daodaCity";
    private static final String chufaCity = "chufaCity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_boardingpass);


        textViewOriginAirport = findViewById(R.id.textViewOriginAirport);

        textViewDestinationAirport  = findViewById(R.id.textViewDestinationAirport);

        mDocRef.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {

                    CityPair myCitypair = documentSnapshot.toObject(CityPair.class);

                   String DestinationAirport = documentSnapshot.getString(daodaCity);

                   String OriginAirport = documentSnapshot.getString(chufaCity);

                    textViewDestinationAirport.setText(DestinationAirport);

                    textViewOriginAirport.setText(OriginAirport);



                }

                else if (e!=null){
                    Log.w(TAG,"Got an Error");
                }
            }
        });
    }


}