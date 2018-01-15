package com.example.cslab.lefei;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by CSLab on 2018/1/14.
 */

public class LoginActivity extends AppCompatActivity{
    private Button mRegisterButton;
    private Button mLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mRegisterButton= (Button)findViewById(R.id.register_button);
        mLoginButton= (Button)findViewById(R.id.login_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Toast.makeText(LoginActivity.this,R.string.Register,Toast.LENGTH_SHORT).show();
                                       }
                                   }
        );

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Loginintent =new Intent(LoginActivity.this, SearchFlightActivity.class);
                startActivity(Loginintent);
            }
        });
    }


}