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
            //mDatabase.execSQL("CREATE TABlE IF NOT EXISTS tbl_Users(name VARCHAR, age INT(4))");
            /*writing to the database
            mDatabase.execSQL("INSERT INTO tbl_Users(name, age) VALUES ('Samuel', 54)");
            mDatabase.execSQL("INSERT INTO tbl_Users(name, age) VALUES ('Alice', 1)");
            mDatabase.execSQL("INSERT INTO tbl_Users(name, age) VALUES ('Janet', 43)");
            mDatabase.execSQL("INSERT INTO tbl_Users(name, age) VALUES ('Isaac', 25)");
            */

            //make a table with primary Keys
            mDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_Family (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");
            /*writing to the database
            mDatabase.execSQL("INSERT INTO tbl_Family(name, age) VALUES ('Samuel', 54)");
            mDatabase.execSQL("INSERT INTO tbl_Family(name, age) VALUES ('Alice', 1)");
            mDatabase.execSQL("INSERT INTO tbl_Family(name, age) VALUES ('Janet', 43)");
            mDatabase.execSQL("INSERT INTO tbl_Family(name, age) VALUES ('Isaac', 25)");
            */


            //read from database
            // Cursor mDataCursor = mDatabase.rawQuery("SELECT * FROM tbl_Users", null/* this function stops a query while in progress */);
            //where
            //Cursor mDataCursor = mDatabase.rawQuery("SELECT * FROM tbl_Users WHERE name = 'Isaac' AND age < 50", null/* this function stops a query while in progress */);
            //deleting Items
            //mDatabase.execSQL("DELETE FROM tbl_Users WHERE name = 'Isaac'");

            //update Values
            //mDatabase.execSQL("UPDATE tbl_Users SET age = 25, name = 'Zacck' WHERE name = 'Samuel'");


            //like search
            Cursor mDataCursor = mDatabase.rawQuery("SELECT * FROM tbl_Family WHERE name Like '%a%'", null/* this function stops a query while in progress */);


            //get the column indexes
            int nameIndex = mDataCursor.getColumnIndex("name");
            int ageIndex = mDataCursor.getColumnIndex("age");
            int primaryKeyIndex = mDataCursor.getColumnIndex("id");

            //start reading data in cursor
            mDataCursor.moveToFirst();
            //loop while cursor has data
            while (mDataCursor != null && !mDataCursor.isAfterLast())
            {
                Log.i("name", mDataCursor.getString(nameIndex));
                Log.i("age", Integer.toString(mDataCursor.getInt(ageIndex)));
                Log.i("id", Integer.toString(mDataCursor.getInt(primaryKeyIndex)));
                mDataCursor.moveToNext();
            }
        }
        catch(Exception e)
        {
            Log.i("SQLITEERROR", e.toString());
        }


    }
}
