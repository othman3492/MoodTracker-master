package com.othman.moodtracker.controller;

import java.util.Date;

public class Mood {

    private int mood;
    private String day;
    private String comment;
    private Date date;


    public Mood() {


    }


    public Mood(int mood, String day, String comment, Date date) {

        this.mood = mood;
        this.day = day;
        this.comment = comment;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) { this.date = date; }
}



