package com.othman.moodtracker.controller;



public class Mood {

    private int moodType;
    private String comment;
    private String date;


    public Mood () {

    }

    public Mood (int moodType, String comment, String date) {

        this.moodType = moodType;
        this.comment = comment;
        this.date = date;

    }

    public int getMoodType() {
        return moodType;
    }

    public void setMoodType(int moodType) {
        this.moodType = moodType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() { return "" + this.moodType + "";
    }
}
