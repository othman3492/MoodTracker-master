package com.othman.moodtracker.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.othman.moodtracker.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    SQLiteDatabaseHelper db;
    private int[] moodQuantities = {0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChartView pieChartView = findViewById(R.id.pie_chart_test_view);
        List<SliceValue> pieData = new ArrayList<>();


        getData();

        pieData.add(new SliceValue(moodQuantities[0], Color.parseColor("#fff9ec4f")).setLabel("SUPER HAPPY"));
        pieData.add(new SliceValue(moodQuantities[1], Color.parseColor("#ffb8e986")).setLabel("HAPPY"));
        pieData.add(new SliceValue(moodQuantities[2], Color.parseColor("#a5468ad9")).setLabel("NORMAL"));
        pieData.add(new SliceValue(moodQuantities[3], Color.parseColor("#ff9b9b9b")).setLabel("DISAPPOINTED"));
        pieData.add(new SliceValue(moodQuantities[4], Color.parseColor("#ffde3c50")).setLabel("SAD"));

        /*pieData.add(new SliceValue(25, Color.YELLOW).setLabel("TEST1"));
        pieData.add(new SliceValue(35, Color.GREEN).setLabel("TEST2"));
        pieData.add(new SliceValue(12, Color.BLUE).setLabel("TEST3"));
        pieData.add(new SliceValue(8, Color.GRAY).setLabel("TEST4"));
        pieData.add(new SliceValue(22, Color.RED).setLabel("TEST5"));*/

        PieChartData pieChartData = new PieChartData(pieData);

        pieChartData.setHasLabels(true).setValueLabelTextSize(10);
        pieChartData.setHasCenterCircle(true).setCenterText1("YOUR MOODS").setCenterText1FontSize(20);

        pieChartView.setPieChartData(pieChartData);


    }



        private void getData() {

            //ArrayList<Mood> moodArrayList = db.getRecentMoods();
            ArrayList<Mood> moodArrayList = new ArrayList<>();

            Mood mood = new Mood(2, "Comment1", "30/05/2019");
            Mood mood2 = new Mood(3, "Comment2", "30/05/2019");
            Mood mood3 = new Mood(0, "Comment3", "30/05/2019");
            Mood mood4 = new Mood(2, "Comment4", "30/05/2019");
            Mood mood5 = new Mood(4, "Comment5", "30/05/2019");
            Mood mood6 = new Mood(1, "Comment6", "30/05/2019");
            Mood mood7 = new Mood(2, "Comment7", "30/05/2019");
            Mood mood8 = new Mood(3, "Comment8", "30/05/2019");
            Mood mood9 = new Mood(1, "Comment9", "30/05/2019");
            Mood mood10 = new Mood(1, "Comment10", "30/05/2019");
            moodArrayList.add(mood);
            moodArrayList.add(mood2);
            moodArrayList.add(mood3);
            moodArrayList.add(mood4);
            moodArrayList.add(mood5);
            moodArrayList.add(mood6);
            moodArrayList.add(mood7);
            moodArrayList.add(mood8);
            moodArrayList.add(mood9);
            moodArrayList.add(mood10);

            for (Mood m : moodArrayList) {

                int moodType = m.getMoodType();

                switch (moodType) {
                    case 0 :
                        moodQuantities[0]++;
                        break;
                    case 1 :
                        moodQuantities[1]++;
                        break;
                    case 2 :
                        moodQuantities[2]++;
                        break;
                    case 3 :
                        moodQuantities[3]++;
                        break;
                    case 4 :
                        moodQuantities[4]++;
                        break;

                }

            }
        }
}
