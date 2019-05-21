package com.othman.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.othman.moodtracker.R;


public class MainActivity extends AppCompatActivity {

    SQLiteDatabaseHelper db;

    private GestureDetector mDetector;

    private int smileyNumber;
    private View mainConstraintLayout;
    private ImageView smiley;

    private ImageButton commentButton;
    private ImageButton historyButton;

    private EditText comment;
    private Button okButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDatabaseHelper(this);

        // Default mood set to 1
        smileyNumber = 1;

        mainConstraintLayout = findViewById(R.id.constraint_layout);
        smiley = findViewById(R.id.happy_face);
        commentButton = findViewById(R.id.comment_button);
        historyButton = findViewById(R.id.history_button);
        comment = findViewById(R.id.comment_dialog);

        mDetector = new GestureDetector(this, new GestureListener());

        mainConstraintLayout.setOnTouchListener(touchListener);

        // Set comment button to show an AlertDialog
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder commentDialog = new AlertDialog.Builder(v.getContext());

                commentDialog.setView(R.layout.comment_dialog);

                commentDialog.setTitle("Commentary");

                commentDialog.setCancelable(true);

                commentDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertData("happy", comment.getText().toString());
                    }
                });

                commentDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                commentDialog.create();
                commentDialog.show();
            }
        });


        // Set history button to start HistoryActivity
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

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


        // Change mood depending on swipe direction
        public void setSmileyAndColor(int smileyNumber) {


            switch (smileyNumber) {
                case 0:
                    mainConstraintLayout.setBackgroundResource(R.color.banana_yellow);
                    smiley.setImageResource(R.mipmap.smiley_super_happy);
                    break;
                case 1:
                    mainConstraintLayout.setBackgroundResource(R.color.light_sage);
                    smiley.setImageResource(R.mipmap.smiley_happy);
                    break;
                case 2:
                    mainConstraintLayout.setBackgroundResource(R.color.cornflower_blue_65);
                    smiley.setImageResource(R.mipmap.smiley_normal);
                    break;
                case 3:
                    mainConstraintLayout.setBackgroundResource(R.color.warm_grey);
                    smiley.setImageResource(R.mipmap.smiley_disappointed);
                    break;
                case 4:
                    mainConstraintLayout.setBackgroundResource(R.color.faded_red);;
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

            // Bottom to top
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("TEST", "fling up");
                smileyNumber--;
                if (smileyNumber > 4)
                    smileyNumber = 4;
                setSmileyAndColor(smileyNumber);


                return false;

            // Top to bottom
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("TEST", "fling down");
                smileyNumber++;
                if (smileyNumber < 0)
                    smileyNumber = 0;
                setSmileyAndColor(smileyNumber);

                return false;
            }
            return false;
        }
    }
}