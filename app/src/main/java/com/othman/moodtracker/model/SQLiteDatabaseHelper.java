package com.othman.moodtracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "Mood.db";
    private static final String TABLE_NAME = "mood_table";
    private static final String COL_DATE = "DATE";
    private static final String COL_MOOD = "MOOD";
    private static final String COL_COMMENT = "COMMENT";


    public SQLiteDatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_DATE + " TEXT PRIMARY KEY NOT NULL, " +
                COL_MOOD + " INTEGER NOT NULL, " +
                COL_COMMENT + " TEXT)");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    // Get last mood saved in database
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

    // Get the 7 last moods saved in the database to populate the recycler view
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


    // Insert new mood in database, replacing the previous one if on the same day
    public boolean insertData(int mood, String comment, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_COMMENT, comment);
        contentValues.put(COL_MOOD, mood);

        long result = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        return result != -1;
    }


}



