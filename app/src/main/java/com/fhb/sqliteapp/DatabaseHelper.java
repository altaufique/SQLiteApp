package com.fhb.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VAIO on 2/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pg.db";
    public static final String TABLE_NAME = "package";
    public static final String COL_1 = "type_ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "gold";
    public static final String COL_4 = "gold_qty";
    public static final String COL_5 = "fee";

    public DatabaseHelper(Context context) { //Constructor to create database
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // method to create table
        db.execSQL("create table " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, " + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, " + COL_5 + " FLOAT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String gold, String gold_qty, String fee){
        SQLiteDatabase db = this.getWritableDatabase();     // Open database connection. Remember to close by calling close()

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, gold);
        contentValues.put(COL_4, gold_qty);
        contentValues.put(COL_5, fee);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false; // failed
        else
            return true;
    }
}
