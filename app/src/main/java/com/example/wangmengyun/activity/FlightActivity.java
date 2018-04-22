package com.example.wangmengyun.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightContract;

import com.example.wangmengyun.adapter.ForecastAdapter;
import com.example.wangmengyun.data.SunshinePreferences;

import com.example.wangmengyun.data.WeatherContract;
import com.example.wangmengyun.lefei.R;


public class FlightActivity extends AppCompatActivity implements

        LoaderManager.LoaderCallbacks<Cursor>,
        ForecastAdapter.ForecastAdapterOnClickHandler {

    private final String TAG = FlightActivity.class.getSimpleName();

    //  COMPLETED (16) Create a String array containing the names of the desired data columns from our ContentProvider
    /*
     * The columns of data that we are interested in displaying within our MainActivity's list of
     * weather data.
     */
    public static final String[] MAIN_FORECAST_PROJECTION = {
            FlightContract.FlightEntry.COLUMN_DEGREES,
            FlightContract.FlightEntry.COLUMN_HUMIDITY,
            FlightContract.FlightEntry.COLUMN_WEATHER_ID,
            FlightContract.FlightEntry.COLUMN_PRESSURE,
    };

    //  COMPLETED (17) Create constant int values representing each column name's position above
    /*
     * We store the indices of the values in the array of Strings above to more quickly be able to
     * access the data from our query. If the order of the Strings above changes, these indices
     * must be adjusted to match the order of the Strings.
     */
    public static final int INDEX_FLIGHT_ID = 0;
    public static final int INDEX_FLIGHT_DEPARTURE = 1;
    public static final int INDEX_FLIGHT_ARRIVAL = 2;
    public static final int INDEX_FLIGHT_TICKETPRICE = 3;

//  COMPLETED (37) Remove the error TextView

    /*
     * This ID will be used to identify the Loader responsible for loading our weather forecast. In
     * some cases, one Activity can deal with many Loaders. However, in our case, there is only one.
     * We will still use this ID to initialize the loader and create the loader for best practice.
     * Please note that 44 was chosen arbitrarily. You can use whatever number you like, so long as
     * it is unique and consistent.
     */
    private static final int ID_FORECAST_LOADER = 44;

    private ForecastAdapter mForecastAdapter;

    private RecyclerView mRecyclerView;

    private int mPosition = RecyclerView.NO_POSITION;

    private ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forecast);

        getSupportActionBar().setElevation(0f);



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_forecast);


        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. In our case, we want a vertical list, so we pass in the constant from the
         * LinearLayoutManager class for vertical lists, LinearLayoutManager.VERTICAL.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         *
         * The third parameter (shouldReverseLayout) should be true if you want to reverse your
         * layout. Generally, this is only true with horizontal lists that need to support a
         * right-to-left layout.
         */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mForecastAdapter = new ForecastAdapter(this, this);

        mRecyclerView.setAdapter(mForecastAdapter);

        showLoading();



        getSupportLoaderManager().initLoader(ID_FORECAST_LOADER, null, this);


    }

//
//    private void openPreferredLocationInMap() {
//        double[] coords = SunshinePreferences.getLocationCoordinates(this);
//        String posLat = Double.toString(coords[0]);
//        String posLong = Double.toString(coords[1]);
//        Uri geoLocation = Uri.parse("geo:" + posLat + "," + posLong);
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(geoLocation);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        } else {
//            Log.d(TAG, "Couldn't call " + geoLocation.toString() + ", no receiving apps installed!");
//        }
//    }

//  COMPLETED (21) Refactor onCreateLoader to return a Loader<Cursor>, not Loader<String[]>
    /**
     * Called by the {@link android.support.v4.app.LoaderManagerImpl} when a new Loader needs to be
     * created. This Activity only uses one loader, so we don't necessarily NEED to check the
     * loaderId, but this is certainly best practice.
     *
     * @param loaderId The loader ID for which we need to create a loader
     * @param bundle   Any arguments supplied by the caller
     * @return A new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
        switch (loaderId) {

            case ID_FORECAST_LOADER:

                Uri flightsQueryUri = FlightContract.FlightEntry.CONTENT_URI;

                String sortOrder = FlightContract.FlightEntry.COLUMN_WEATHER_ID+ " ASC";

                String selection = FlightContract.FlightEntry.getSqlSelectForTodayOnwards();

                return new CursorLoader(this,
                        flightsQueryUri,
                        MAIN_FORECAST_PROJECTION,
                        selection,
                        null,
                        sortOrder);

            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mForecastAdapter.swapCursor(data);

        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);

       if (data.getCount() != 0) showWeatherDataView();
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mForecastAdapter.swapCursor(null);
    }


    @Override
    public void onClick(String weatherForDay) {
        Context context = this;
        Class destinationClass = DetailActivity.class;

        Intent intentToStartDetailActivity = new Intent(context, destinationClass);

        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, weatherForDay);
        startActivity(intentToStartDetailActivity);
    }

    private void showWeatherDataView() {

        mLoadingIndicator.setVisibility(View.INVISIBLE);

        mRecyclerView.setVisibility(View.VISIBLE);
    }


    private void showLoading() {

        mRecyclerView.setVisibility(View.INVISIBLE);

        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

//    /**
//     * This is where we inflate and set up the menu for this Activity.
//     *
//     * @param menu The options menu in which you place your items.
//     *
//     * @return You must return true for the menu to be displayed;
//     *         if you return false it will not be shown.
//     *
//     * @see #onPrepareOptionsMenu
//     * @see #onOptionsItemSelected
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
//        MenuInflater inflater = getMenuInflater();
//        /* Use the inflater's inflate method to inflate our menu layout to this menu */
//        inflater.inflate(R.menu.forecast, menu);
//        /* Return true so that the menu is displayed in the Toolbar */
//        return true;
//    }
//
//    /**
//     * Callback invoked when a menu item was selected from this Activity's menu.
//     *
//     * @param item The menu item that was selected by the user
//     *
//     * @return true if you handle the menu click here, false otherwise
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, DetailActivity.class));
//            return true;
//        }
//        if (id == R.id.action_map) {
//            openPreferredLocationInMap();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
