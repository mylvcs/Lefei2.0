package com.example.wangmengyun.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class AllCitySqliteOpenHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.wangmengyun.lefei/databases/";
    private static String DB_NAME = "cities.db";
    private Context mContext;
    private static String ASSETS_NAME = "cities.db";
    private static final int DB_VERSION = 3;

    public AllCitySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                                   int version) {
        super(context, name, null, version);
        this.mContext = context;
    }

    public AllCitySqliteOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public AllCitySqliteOpenHelper(Context context, String name) {
        this(context, name, DB_VERSION);
    }

    public AllCitySqliteOpenHelper(Context context) {
        this(context, DB_PATH + DB_NAME);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {

        } else {
            try{
                File dir = new File(DB_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dbf = new File(DB_PATH + DB_NAME);
                if (dbf.exists()) {
                    dbf.delete();
                }

                SQLiteDatabase.openOrCreateDatabase(dbf, null);

                copyDataBase();
            } catch (IOException e) {
                throw new Error("数据库创建失败");
            }
        }
    }


    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        String myPath = DB_PATH + DB_NAME;
        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    private void copyDataBase() throws IOException {

        InputStream myInput = mContext.getAssets().open(ASSETS_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }


        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
