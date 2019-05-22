package com.othman.moodtracker.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Mood.db";
    public static final String TABLE_NAME = "mood_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "MOOD";
    public static final String COL_4 = "COMMENT";


    public SQLiteDatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                                   DATE INTEGER UNIQUE NOT NULL, " +
                "                                   MOOD INTEGER NOT NULL, " +
                "                                   COMMENT TEXT)");
        Log.d("DATABASE", "onCreate called");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public Mood getCurrentMood() {

        Cursor myCursor = null;

        if (myCursor.moveToFirst()) {
            Mood mood = new Mood();
            mood.setMood(myCursor.getInt(myCursor.getColumnIndex(COL_3)));
            mood.setComment(myCursor.getString(myCursor.getColumnIndex(COL_4)));

            return mood;
        }

        return null;

    }

    public void insertData(int mood, String comment) {

        String instruction = "INSERT INTO mood_table (mood, comment, date) VALUES ('" + mood + "', " + comment + ", " + new Date().getTime() + ")";
        this.getWritableDatabase().execSQL(instruction);
        Log.d("DATABASE", "insertData called");
    }

    public void dbTest() {
        Log.d("DATABASE", "dbTest called");
    }


    public List<Mood> showRecentMoods() {

        List<Mood> recentMoods = new ArrayList<>();

        String instruction = "SELECT * FROM " + TABLE_NAME + " ORDER BY DATE DESC LIMIT 7";
        Cursor cursor = this.getReadableDatabase().rawQuery(instruction, null);
        cursor.close();

        return recentMoods;
    }
};



