package com.othman.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
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

    private int[] imagesList;
    private int[] colorsList;
    private int[] soundsList;

    private ImageButton commentButton;
    private ImageButton historyButton;

    private Button okButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDatabaseHelper(this);

        // Default mood set to 1
        smileyNumber = 1;
        imagesList = new int[] {R.mipmap.smiley_super_happy, R.mipmap.smiley_happy, R.mipmap.smiley_normal, R.mipmap.smiley_disappointed, R.mipmap.smiley_sad};
        colorsList = new int[] {R.color.banana_yellow, R.color.light_sage, R.color.cornflower_blue_65, R.color.warm_grey, R.color.faded_red};
        soundsList = new int[] {R.raw.plucky, R.raw.quite_impressed, R.raw.unconvinced, R.raw.open_ended, R.raw.unsure};

        mainConstraintLayout = findViewById(R.id.constraint_layout);
        smiley = findViewById(R.id.happy_face);
        commentButton = findViewById(R.id.comment_button);
        historyButton = findViewById(R.id.history_button);

        mDetector = new GestureDetector(this, new GestureListener());

        mainConstraintLayout.setOnTouchListener(touchListener);

        // Set comment button to show an AlertDialog
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder commentDialog = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = getLayoutInflater();

                commentDialog.setView(inflater.inflate(R.layout.comment_dialog, null));

                commentDialog.setTitle("Commentary");

                commentDialog.setCancelable(true);

                commentDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText comment = ((AlertDialog) dialog).findViewById(R.id.comment_dialog);
                        if (comment != null) {
                            String userComment = comment.getText().toString();
                            db.insertData(smileyNumber, "'" + userComment + "'");
                            Log.d("DATABASE_TEST", "'" + userComment + "'");
                        } else {
                            dialog.cancel();
                        }
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
        public void setMoodSettings(int smileyNumber) {

            mainConstraintLayout.setBackgroundResource(colorsList[smileyNumber]);
            smiley.setImageResource(imagesList[smileyNumber]);
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), soundsList[smileyNumber]);
            mediaPlayer.start();
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
                if (smileyNumber > 0) {
                    smileyNumber--;
                    setMoodSettings(smileyNumber);
                    Log.d("TEST", Integer.toString(smileyNumber));
                }

                return false;

            // Top to bottom
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.d("TEST", "fling down");
                if (smileyNumber < colorsList.length - 1) {
                    smileyNumber++;
                    setMoodSettings(smileyNumber);
                    Log.d("TEST", Integer.toString(smileyNumber));
                }

                return false;
            }
            return false;
        }
    }
}