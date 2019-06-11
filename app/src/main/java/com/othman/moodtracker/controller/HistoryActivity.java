package com.othman.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.othman.moodtracker.R;
import com.othman.moodtracker.model.Mood;
import com.othman.moodtracker.model.SQLiteDatabaseHelper;
import com.othman.moodtracker.view.MoodAdapter;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_recyclerview);

        MoodAdapter adapter;
        RecyclerView recyclerView;
        SQLiteDatabaseHelper db;

        recyclerView = findViewById(R.id.history_recyclerview);
        db = new SQLiteDatabaseHelper(this);

        // Create a ArrayList with the 7 last moods saved in the database and populate the recycler view with it
        ArrayList<Mood> moodArrayList = db.getRecentMoods();

        adapter = new MoodAdapter(moodArrayList, recyclerView);

        // Set linear layout manager to show history in descending order
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}