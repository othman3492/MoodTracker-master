package com.othman.moodtracker.controller;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.othman.moodtracker.R;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    private SQLiteDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_data_layout);

        db = new SQLiteDatabaseHelper(this);
        final ArrayList<Mood> recentMoods = db.showRecentMoods();


        RecyclerView recyclerView = findViewById(R.id.history_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = new SQLiteDatabaseHelper(this);

        ArrayList<Mood> moodList = db.showRecentMoods();

        MoodAdapter moodAdapter = new MoodAdapter(moodList, this);
        recyclerView.setAdapter(moodAdapter);


        }
    }