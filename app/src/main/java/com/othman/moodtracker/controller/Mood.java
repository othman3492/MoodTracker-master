package com.othman.moodtracker.controller;


import org.threeten.bp.LocalDate;


public class Mood {

    private int moodType;
    private String comment;
    private String date;


    public Mood () {

    }

    public Mood (int moodType, String comment) {

        moodType = this.moodType;
        date = LocalDate.now().toString();
        comment = this.comment;

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
    public String toString() {
        return "" + this.moodType + "";
    }
}
