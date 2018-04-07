package com.example.wangmengyun.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.example.wangmengyun.Bean.CityDBSchema.CityTable;
import com.example.wangmengyun.Bean.CityContract.CityEntry;

/**
 * Created by wangmengyun on 2018/4/4.
 */

public class CityBaseHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME ="City.db";
    private static final int VERSION =1;

    public CityBaseHelper(Context context) {
        super(context,DATABASE_NAME,null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table "+ CityTable.NAME +"("+
//        //        " _id integer primary key autoincrement,"+
//                CityTable.Cols.UUID+"," +
//                CityTable.Cols.NAME +","+
//                CityTable.Cols.PINYIN + ");"
//        );

        String SQL_CREATE_TABLE =  "CREATE TABLE " +CityEntry.TABLE_NAME + " ("
                + CityEntry.COLUMN_UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CityEntry.COLUMN_CITY_NAME + "TEXT"
                + CityEntry.COLUMN_CITY_OF_COUNTRY + "TEXT"
                + CityEntry.COLUMN_JINGDU+ "STRING"
                + CityEntry.COLUMN_WEIDU + "STRING )";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
