package com.othman.moodtracker.controller;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

@SuppressWarnings("ALL")
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
