package com.zacck.androidsqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            //create a database supply name, Mode , error catching method
            SQLiteDatabase mDatabase = this.openOrCreateDatabase("EventsDatabase", MODE_PRIVATE,null);
            //execute sql in SQL we capitalize all syntax
            mDatabase.execSQL("CREATE TABlE IF NOT EXISTS tbl_Events(name VARCHAR, year INT(4))");

            //writing to the database
            mDatabase.execSQL("INSERT INTO tbl_Events(name, year) VALUES ('My Birth', 1990)");
            mDatabase.execSQL("INSERT INTO tbl_Events(name, year) VALUES ('Driving License', 2008)");

            //read from database
            Cursor mDataCursor = mDatabase.rawQuery("SELECT * FROM tbl_Events", null/* this function stops a query while in progress */);
            //get the column indexes
            int nameIndex = mDataCursor.getColumnIndex("name");
            int ageIndex = mDataCursor.getColumnIndex("year");

            //start reading data in cursor
            mDataCursor.moveToFirst();
            //loop while cursor has data
            while (mDataCursor != null && !mDataCursor.isAfterLast())
            {
                Log.i("name", mDataCursor.getString(nameIndex));
                Log.i("year", Integer.toString(mDataCursor.getInt(ageIndex)));
                mDataCursor.moveToNext();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
