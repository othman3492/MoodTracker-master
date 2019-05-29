package com.othman.moodtracker.controller;

import com.othman.moodtracker.R;

import java.util.HashMap;
import java.util.Map;

public enum MoodType {


    SUPERHAPPY(0, R.color.banana_yellow),
    HAPPY(1, R.color.light_sage),
    NORMAL(2, R.color.cornflower_blue_65),
    DISAPPOINTED(3, R.color.warm_grey),
    SAD(4, R.color.faded_red);


    private int id;
    private int color;
    public static final Map<Integer, MoodType> moodMap = new HashMap<>();



    MoodType (int id, int color) {

        this.id = id;
        this.color = color;
    }


    static public MoodType findMoodType(int id) {

        return moodMap.get(id);
    }


    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "" + id + "";
    }
}