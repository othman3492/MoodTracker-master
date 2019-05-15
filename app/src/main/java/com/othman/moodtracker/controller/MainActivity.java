package com.othman.moodtracker.controller;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.othman.moodtracker.R;


public class MainActivity extends AppCompatActivity {

    private GestureDetector mDetector;

    public int smileyNumber;
    public View constraintLayout;
    public ImageView smiley;


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smileyNumber = 1;
        constraintLayout = findViewById(R.id.constraint_layout);
        smiley = findViewById(R.id.happy_face);

        mDetector = new GestureDetector(this, new GestureListener());

        constraintLayout.setOnTouchListener(touchListener);

    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mDetector.onTouchEvent(event);

        }
    };











    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;



        public void setSmiley(int smileyNumber) {


            switch (smileyNumber) {
                case 0:
                    constraintLayout.setBackgroundColor(Color.parseColor("#fff9ec4f"));
                    smiley.setImageResource(R.mipmap.smiley_super_happy);
                    break;
                case 1:
                    constraintLayout.setBackgroundColor(Color.parseColor("#ffb8e986"));
                    smiley.setImageResource(R.mipmap.smiley_happy);
                    break;
                case 2:
                    constraintLayout.setBackgroundColor(Color.parseColor("#a5468ad9"));
                    smiley.setImageResource(R.mipmap.smiley_normal);
                    break;
                case 3:
                    constraintLayout.setBackgroundColor(Color.parseColor("#ff9b9b9b"));
                    smiley.setImageResource(R.mipmap.smiley_disappointed);
                    break;
                case 4:
                    constraintLayout.setBackgroundColor(Color.parseColor("#ffde3c50"));
                    smiley.setImageResource(R.mipmap.smiley_sad);
                    break;
            }

        }



        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {



            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("TAG", "fling up");
                smileyNumber++;
                if (smileyNumber >= 4) smileyNumber = 4;
                setSmiley(smileyNumber);


                return false; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("TAG", "fling down");
                smileyNumber--;
                if (smileyNumber <= 0) smileyNumber = 0;
                setSmiley(smileyNumber);

                return false; // Top to bottom
            }
            return false;
        }
    }
}