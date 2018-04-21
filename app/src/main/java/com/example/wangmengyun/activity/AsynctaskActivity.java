package com.example.wangmengyun.activity;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.wangmengyun.Utils.NetworkUtils;
//import com.example.wangmengyun.lefei.R;
//
//import java.io.IOException;
//import java.net.URL;
//
///**城市对的输入为两个TextView里的string
// * 作为参数传入到URL里。
// 现在只传入了一个departCity参数
// */
//
//public class AsynctaskActivity extends AppCompatActivity {
//
//    private EditText et_departureCity;
//    private EditText et_arriveCity;
//
//    private Button button;
//    private TextView mUrlDisplayTextView;
//    private TextView mFlightResultsTextView ;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_asynctask);
//
//            et_departureCity = (EditText) findViewById(R.id.departure);
//            et_arriveCity = findViewById(R.id.arrive);
//
//
//        button = findViewById(R.id.search_button);
//        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
//        mFlightResultsTextView = (TextView) findViewById(R.id.tv_flight_search_results_json);
//
//
//           button.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   makeFlightSearchQuery();
//               }
//           });
//        }
//
//        Intent getIntent = getIntent();
//
//        Bundle bd = getIntent.getExtras();
//        String departCity = bd.getString("departCity");
//        String arriveCity = bd.getString("arriveCity");
//        //       String textEntered = departureCityintent.getStringExtra(Intent.EXTRA_TEXT);
//
//
//
//
//    public void makeFlightSearchQuery() {
//        String departCityQuery = et_departureCity.getText().toString();
//        String arriveCityQuery = et_arriveCity.getText().toString();
////TODO
//        /**
//         * 这里的问题是如何传入两个参数呢？
//         * our {@link FlightQueryTask}
//         */
//
//        URL flightSearchUrl = NetworkUtils.buildUrl(departCityQuery);
//        // mUrlDisplayTextView.setText(flightSearchUrl.toString());
//        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
//        new FlightQueryTask().execute(flightSearchUrl);
//        Log.i("the FlightSearchQuery","departureCity");
//    }
//
//    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
//    public class FlightQueryTask extends AsyncTask<URL, Void, String> {
//
//        @Override
//        protected String doInBackground(URL... params) {
//            URL searchUrl = params[0];
//            String flightSearchResults = null;
//            try {
//                flightSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return flightSearchResults;
//        }
//
//        @Override
//        protected void onPostExecute(String flightSearchResults) {
//            if (flightSearchResults != null && !flightSearchResults.equals("")) {
//                mFlightResultsTextView.setText(flightSearchResults);
//            }
//        }
//    }
//
//}
//
/*
 * 这个还没做完。
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


import com.example.wangmengyun.Utils.NetworkUtils;
import com.example.wangmengyun.lefei.R;

import java.io.IOException;
import java.net.URL;

public class AsynctaskActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
    }


    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link FlightQueryTask}
     */
    private void makeFlightSearchQuery() {

        String flightQuery = mSearchBoxEditText.getText().toString();

        URL flightSearchUrl = NetworkUtils.buildUrl(flightQuery);

        mUrlDisplayTextView.setText(flightSearchUrl.toString());

        new FlightQueryTask().execute(flightSearchUrl);
    }

    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    public class FlightQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];


            String flightSearchResults = null;
            try {
                flightSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return flightSearchResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String flightSearchResults) {

            if (flightSearchResults != null && !flightSearchResults.equals("")) {

                mSearchResultsTextView.setText(flightSearchResults);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_search) {
            makeFlightSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

