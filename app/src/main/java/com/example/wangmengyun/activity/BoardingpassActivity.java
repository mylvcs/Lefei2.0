package com.example.wangmengyun.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by wangmengyun on 2018/4/19.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangmengyun.Bean.CityPair;
import com.example.wangmengyun.lefei.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class BoardingpassActivity extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/CityPair");
    private String TAG = "SearchHistory";

    private ImageView clearBtn;
    private ImageView backBtn;
    private TextView textViewOriginAirport;

    private TextView  textViewDestinationAirport;
    private static final String daodaCity = "daodaCity";
    private static final String chufaCity = "chufaCity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardingpass);


        clearBtn =  findViewById(R.id.iv_search_clear);
        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });



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
