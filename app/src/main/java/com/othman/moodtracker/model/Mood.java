package com.othman.moodtracker.model;




public class Mood {

    private int moodType;
    private String comment;
    private String date;


    Mood() {

    }


    public int getMoodType() {
        return moodType;
    }

    void setMoodType(int moodType) {
        this.moodType = moodType;
    }

    public String getComment() {
        return comment;
    }

    void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }


    // Return a string to share current mood in messages
    @Override
    public String toString() {

        String mood = "";

        switch (moodType) {
            case 0:
                mood = "super happy";
            break;
            case 1:
                mood = "happy";
            break;
            case 2:
                mood = "normal";
            break;
            case 3:
                mood = "disappointed";
            break;
            case 4:
                mood = "sad";
            break;
        }
        return mood;
    }
}

