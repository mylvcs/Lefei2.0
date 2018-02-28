package com.wangmengyun.lefei2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wangmengyun.lefei2.data.UserContract.UserEntry;
import com.wangmengyun.lefei2.data.UserDBHelper;

/**
 * Created by wangmengyun on 2018/2/28.
 */
public class EditActivity extends AppCompatActivity {

//    /**
//     * EditText field to enter the pet's name
//     */
//    private EditText mNameEditText;
//
//    /**
//     * EditText field to enter the pet's breed
//     */
//    private EditText mBreedEditText;
//
//    /**
//     * EditText field to enter the pet's weight
//     */
//    private EditText mWeightEditText;
//
//    /**
//     * EditText field to enter the pet's gender
//     */
//    private Spinner mGenderSpinner;
//
//    public int mGender = UserEntry.GENDER_MALE;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_editor);
//
//        // Find all relevant views that we will need to read user input from
//        mNameEditText = (EditText) findViewById(R.id.edit_user_name);
//
//        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
//
//        //setupSpinner();
//    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
//    private void setupSpinner() {
//        // Create adapter for spinner. The list options are from the String array it will use
//        // the spinner will use the default layout
//        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
//                R.array.array_gender_options, android.R.layout.simple_spinner_item);
//
//        // Specify dropdown layout style - simple list view with 1 item per line
//        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//        // Apply the adapter to the spinner
//        mGenderSpinner.setAdapter(genderSpinnerAdapter);
//
//        // Set the integer mSelected to the constant values
//        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selection = (String) parent.getItemAtPosition(position);
//                if (!TextUtils.isEmpty(selection)) {
//                    if (selection.equals(getString(R.string.gender_male))) {
//                        mGender = PetEntry.GENDER_MALE;
//                    } else if (selection.equals(getString(R.string.gender_female))) {
//                        mGender = PetEntry.GENDER_FEMALE;
//                    } else {
//                        mGender = PetEntry.GENDER_UNKNOWN;
//                    }
//                }
//            }
//
//            // Because AdapterView is an abstract class, onNothingSelected must be defined
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                mGender = UserEntry.GENDER_MALE;
//            }
//        });
//    }

    /**
     * Get user input from editor and save new pet into database.
     */
//    private void insertUser() {
//        // Read from input fields
//        // Use trim to eliminate leading or trailing white space
//        String nameString = mNameEditText.getText().toString().trim();
//        String breedString = mBreedEditText.getText().toString().trim();
//        String weightString = mWeightEditText.getText().toString().trim();
//        int weight = Integer.parseInt(weightString);
//
//        // Create database helper
//        UserDBHelper mDbHelper = new UserDBHelper(this);
//
//        // Gets the database in write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a ContentValues object where column names are the keys,
//        // and pet attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//    //    values.put(UserEntry.COLUMN_PET_NAME, nameString);
//
//
//        // Insert a new row for pet in the database, returning the ID of that new row.
//        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//    //    getMenuInflater().inflate(R.menu.menu_editor, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Save" menu option
//            case R.id.action_save:
//                // Save pet to database
//                insertPet();
//                // Exit activity
//                finish();
//                return true;
//            // Respond to a click on the "Delete" menu option
//            case R.id.action_delete:
//                // Do nothing for now
//                return true;
//            // Respond to a click on the "Up" arrow button in the app bar
//            case android.R.id.home:
//                // Navigate back to parent activity (CatalogActivity)
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}