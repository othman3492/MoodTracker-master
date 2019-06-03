package com.othman.moodtracker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Mood.db";
    public static final String TABLE_NAME = "mood_table";
    public static final String COL_DATE = "DATE";
    public static final String COL_MOOD = "MOOD";
    public static final String COL_COMMENT = "COMMENT";


    public SQLiteDatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        disableWal(db);

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_DATE + " TEXT PRIMARY KEY NOT NULL, " +
                COL_MOOD + " INTEGER NOT NULL, " +
                COL_COMMENT + " TEXT)");
        Log.d("DATABASE", "onCreate called");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        disableWal(db);

        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Mood getCurrentMood() {

        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, COL_DATE + " == '" + LocalDate.now() + "'",
                                                null, null, null, COL_DATE + " DESC ", "1");

        if (cursor.moveToFirst()) {

            Mood mood = new Mood();
            mood.setMoodType(cursor.getInt(cursor.getColumnIndex(COL_MOOD)));
            mood.setComment(cursor.getString(cursor.getColumnIndex(COL_COMMENT)));
            mood.setDate((cursor.getString(cursor.getColumnIndex(COL_DATE))));

            cursor.close();
            return mood;
        }

        cursor.close();
        return null;

    }

    public void insertData(int moodType, String comment, String date) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MOOD, moodType);
        contentValues.put(COL_COMMENT, comment);
        contentValues.put(COL_DATE, date);

        this.getWritableDatabase().insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        Log.d("DATABASE", "insertData called");
    }


    public ArrayList<Mood> getRecentMoods() {

        ArrayList<Mood> recentMoods = new ArrayList<>();

        String instruction = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_DATE + " DESC LIMIT 7";
        Cursor cursor = this.getWritableDatabase().rawQuery(instruction, null);

        while (cursor.moveToNext()) {
            Mood mood = new Mood();
            mood.setMoodType(cursor.getInt(cursor.getColumnIndex(COL_MOOD)));
            mood.setComment(cursor.getString(cursor.getColumnIndex(COL_COMMENT)));
            mood.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));

            recentMoods.add(mood);
        }

        cursor.close();

        return recentMoods;
    }



    private void disableWal(SQLiteDatabase db) {
        // Disables WAL. We don't need such a dev-unfriendly feature on a simple project.
        // With this, .wal and .smh files are no longer generated, and the db is easy to extract & open
        //
        // Source : https://www.sqlite.org/wal.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }

    public boolean insertTest(int mood, String comment, String date) {

        SQLiteDatabase dbTest = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_COMMENT, comment);
        contentValues.put(COL_MOOD, mood);

        Log.d("TAGINSERT", "insertTest : Add " + date + " " + comment + " " + mood + "into " + TABLE_NAME);
        long result = dbTest.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

};



