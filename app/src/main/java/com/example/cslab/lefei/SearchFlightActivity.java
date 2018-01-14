package com.example.cslab.lefei;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by CSLab on 2018/1/14.
 */

public class SearchFlightActivity  extends AppCompatActivity{
    private Button mButton;
    private EditText mOriginCity;
    private EditText mArrivalCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchflight);
        mOriginCity =(EditText)findViewById(R.id.origin_city);
        mArrivalCity= (EditText)findViewById(R.id.arrival_city);
        mButton = (Button)findViewById(R.id.search);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchFlightActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
