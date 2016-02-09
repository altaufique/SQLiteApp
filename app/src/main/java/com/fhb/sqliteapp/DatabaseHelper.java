package com.fhb.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VAIO on 2/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pg.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "package";
    public static final String COL_1 = "type_ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "gold";
    public static final String COL_4 = "gold_qty";
    public static final String COL_5 = "fee";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //Constructor to create database. Using 'super' to call parent class and passing the current parameter DATABASE_NAME to create the database
    }

    // Creating database table
    @Override
    public void onCreate(SQLiteDatabase db) { // method to create table with one parameter of database instance.
        db.execSQL("create table " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, " + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, " + COL_5 + " FLOAT NOT NULL)");
        //    |
        //    |
        // use execSQL to execute SQL query
    }

    public boolean insertData(String name, String gold, String gold_qty, String fee){
        // Open database connection. Remember to close by calling close()
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, gold);
        contentValues.put(COL_4, gold_qty);
        contentValues.put(COL_5, fee);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // Close the database
        db.close();

        if (result == -1)
            return false; // failed
        else
            return true;
    }

    public Cursor getAllData () { // Cursor class data type, give access to read write to result
        // Open database connection. Remember to close by calling close()
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " +TABLE_NAME, null);
        return result;
    }

    public boolean updateDate(){
        // Open database connection. Remember to close by calling close()
        SQLiteDatabase db = this.getWritableDatabase();


        return true;
    }

    // This method is called when database is upgraded like modifying the table structure, adding constraints to database etc.,
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop database table if exist
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        // Create table again
        onCreate(db);
    }
}
