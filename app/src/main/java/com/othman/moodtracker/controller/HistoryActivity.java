package com.othman.moodtracker.controller;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othman.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    private View view1;
    private View view2;
    private TextView textView1;
    private TextView textView2;
    private ImageView imageView1;
    private ImageView imageView2;
    private ConstraintLayout historyConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_history);

        view1 = findViewById(R.id.history_view);
        view2 = findViewById(R.id.history_view2);
        textView1 = findViewById(R.id.history_textview);
        textView2 = findViewById(R.id.history_textview2);
        imageView1 = findViewById(R.id.history_imageview);
        imageView2 = findViewById(R.id.history_imageview2);
        historyConstraintLayout = findViewById(R.id.history_constraint_layout);

        ConstraintSet mySet1 = new ConstraintSet();
        mySet1.clone(historyConstraintLayout);
        mySet1.constrainPercentWidth(view1.getId(), 0.2f);
        mySet1.setVisibility(imageView1.getId(), View.GONE);
        mySet1.applyTo(historyConstraintLayout);

        view1.setBackgroundResource(R.color.faded_red);





    }
}
