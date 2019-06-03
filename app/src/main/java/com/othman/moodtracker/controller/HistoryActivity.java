package com.othman.moodtracker.controller;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.othman.moodtracker.R;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    public MoodAdapter adapter;
    RecyclerView recyclerView;
    SQLiteDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_recyclerview);

        recyclerView = findViewById(R.id.history_recyclerview);
        db = new SQLiteDatabaseHelper(this);


        ArrayList<Mood> moodArrayList = new ArrayList<>();

        Mood mood = new Mood(2, "Comment1", "30/05/2019");
        Mood mood2 = new Mood(3, "Comment2", "30/05/2019");
        Mood mood3 = new Mood(0, "Comment3", "30/05/2019");
        Mood mood4 = new Mood(2, "Comment4", "30/05/2019");
        Mood mood5 = new Mood(4, "Comment5", "30/05/2019");
        Mood mood6 = new Mood(1, "Comment6", "30/05/2019");
        Mood mood7 = new Mood(2, "Comment7", "30/05/2019");
        moodArrayList.add(mood);
        moodArrayList.add(mood2);
        moodArrayList.add(mood3);
        moodArrayList.add(mood4);
        moodArrayList.add(mood5);
        moodArrayList.add(mood6);
        moodArrayList.add(mood7);



        adapter = new MoodAdapter(this, moodArrayList, recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        }
    }