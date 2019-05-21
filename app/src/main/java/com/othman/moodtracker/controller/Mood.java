package com.othman.moodtracker.controller;

public class Mood {

    private int mood;
    private String day;
    private String comment;


    public Mood() {


    }


    public Mood(int id, String day, String comment) {

        this.mood = id;
        this.day = day;
        this.comment = comment;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int idMood) {
        this.mood = mood;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}


