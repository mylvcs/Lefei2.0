package com.example.wangmengyun.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wangmengyun.adapter.CustomAdapter;
import com.example.wangmengyun.data.User;
import com.example.wangmengyun.lefei.R;
import com.firebase.ui.auth.ui.ProgressDialogHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmengyun on 2018/4/29.
 */

public class HttpMongo extends AppCompatActivity{

     ListView listView;
     Button btnAdd, btnEdit;

    EditText editUser;
    User userSelected = null;
    List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpdatat);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btn_add);


        new GetData().execute(Common.getAddressAPI());


    }

    class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog pd = new ProgressDialog(HttpMongo.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait");

            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String stream = null;
            String urlString = params[0];

            HttpDataHandler http = new HttpDataHandler();
            stream = http.GetHttpData(urlString);

            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<User>>(){}.getType();

            users = gson.fromJson(s,listType);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),users);

            listView.setAdapter(adapter);

            pd.dismiss();
        }


    }
}
