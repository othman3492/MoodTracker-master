package com.othman.moodtracker.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.othman.moodtracker.R;
import com.othman.moodtracker.model.Mood;
import com.othman.moodtracker.model.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;



public class PieChartActivity extends AppCompatActivity {

    private SQLiteDatabaseHelper db;
    private final int[] moodQuantities = {0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChartView pieChartView = findViewById(R.id.pie_chart_view);
        db = new SQLiteDatabaseHelper(this);


        getData();


        // Create pie chart
        List<SliceValue> pieData = new ArrayList<>();

        pieData.add(new SliceValue(moodQuantities[0], Color.parseColor("#fff9ec4f")));
        pieData.add(new SliceValue(moodQuantities[1], Color.parseColor("#ffb8e986")));
        pieData.add(new SliceValue(moodQuantities[2], Color.parseColor("#a5468ad9")));
        pieData.add(new SliceValue(moodQuantities[3], Color.parseColor("#ff9b9b9b")));
        pieData.add(new SliceValue(moodQuantities[4], Color.parseColor("#ffde3c50")));

        PieChartData pieChartData = new PieChartData(pieData);

        pieChartData.setHasLabels(true).setValueLabelTextSize(10);
        pieChartData.setHasCenterCircle(true).setCenterText1("YOUR RECENT MOODS").setCenterText1FontSize(16);

        pieChartView.setPieChartData(pieChartData);


    }


        // Increment different mood quantities to fill pie chart
        private void getData() {


            ArrayList<Mood> moodArrayList = db.getRecentMoods();

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
