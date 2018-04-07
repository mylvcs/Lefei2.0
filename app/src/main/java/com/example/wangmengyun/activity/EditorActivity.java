//package com.example.wangmengyun.activity;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.wangmengyun.Bean.City;
//import com.example.wangmengyun.Bean.CityBaseHelper;
//import com.example.wangmengyun.Utils.BoardingPassInfo;
//import com.example.wangmengyun.Utils.FackBoardingDataUtils;
//import com.example.wangmengyun.lefei.R;
//import com.example.wangmengyun.Bean.CityContract.CityEntry;
///**
// * Created by wangmengyun on 2018/4/7.
// */
//
//public class EditorActivity extends AppCompatActivity {
//
//    private EditText mNameEditText;
//
//    private EditText mCountryEditText;
//    private EditText mJingduEditText;
//
//    private EditText mWeiduEditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_editor);
//
//
//    }
//
//    private void insertCity() {
//        // Read from input fields
//        // Use trim to eliminate leading or trailing white space
//        String cityName = mNameEditText.getText().toString().trim();
//        String country = mCountryEditText.getText().toString().trim();
//        String jingdu = mJingduEditText.getText().toString().trim();
//
//        String weidu = mWeiduEditText.getText().toString().trim();
//
//
//        int weight = Integer.parseInt(weightString);
//
//        // Create database helper
//        CityBaseHelper mDbHelper = new CityBaseHelper(this);
//
//        // Gets the database in write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a ContentValues object where column names are the keys,
//        // and pet attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//        values.put(CityEntry.COLUMN_CITY_NAME, cityName);
//        values.put(CityEntry.COLUMN_CITY_OF_COUNTRY, country);
//        values.put(CityEntry.COLUMN_JINGDU, jingdu);
//        values.put(CityEntry.COLUMN_WEIDU, weidu);
//
//        // Insert a new row for pet in the database, returning the ID of that new row.
//        long newRowId = db.insert(PetEntry.TABLE_NAME, null, values);
//
//        // Show a toast message depending on whether or not the insertion was successful
//        if (newRowId == -1) {
//            // If the row ID is -1, then there was an error with insertion.
//            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
//        } else {
//            // Otherwise, the insertion was successful and we can display a toast with the row ID.
//            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
//        }
//    }
//}