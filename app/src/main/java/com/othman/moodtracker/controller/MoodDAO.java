package com.othman.moodtracker.controller;

import java.util.List;

public interface MoodDAO {

    List<Mood> findAll();
    List<Mood> findById();
    List<Mood> findByDate();

    boolean createMood(Mood mood);
    boolean retrieveMood(Mood mood);
    boolean updateMood(Mood mood);
    boolean deleteMood(Mood mood);
}
