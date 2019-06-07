package com.othman.moodtracker.controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.othman.moodtracker.R;
import com.othman.moodtracker.model.SQLiteDatabaseHelper;

import org.threeten.bp.LocalDate;


public class MainActivity extends AppCompatActivity {

    private SQLiteDatabaseHelper db;

    private GestureDetector mDetector;

    private int smileyNumber;
    private View mainConstraintLayout;
    private ImageView smiley;

    private int[] imagesList;
    private int[] colorsList;
    private int[] soundsList;

    private LocalDate currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton commentButton;
        ImageButton historyButton;
        ImageButton pieChartButton;
        ImageButton shareButton;

        db = new SQLiteDatabaseHelper(this);
        currentDate = LocalDate.now();

        imagesList = new int[]{R.mipmap.smiley_super_happy, R.mipmap.smiley_happy, R.mipmap.smiley_normal, R.mipmap.smiley_disappointed, R.mipmap.smiley_sad};
        colorsList = new int[]{R.color.banana_yellow, R.color.light_sage, R.color.cornflower_blue_65, R.color.warm_grey, R.color.faded_red};
        soundsList = new int[]{R.raw.plucky, R.raw.quite_impressed, R.raw.unconvinced, R.raw.open_ended, R.raw.unsure};

        // Bind views
        mainConstraintLayout = findViewById(R.id.constraint_layout);
        smiley = findViewById(R.id.happy_face);
        commentButton = findViewById(R.id.comment_button);
        historyButton = findViewById(R.id.history_button);
        pieChartButton = findViewById(R.id.pie_chart_button);
        shareButton = findViewById(R.id.share_button);

        // Set listeners to use OnFling method to swipe between moods
        mDetector = new GestureDetector(this, new GestureListener());
        mainConstraintLayout.setOnTouchListener(touchListener);

        // Set default mood to 1 (happy) and display it
        smileyNumber = 1;
        setMoodSettings(smileyNumber);
        db.insertData(smileyNumber, null, LocalDate.now().toString());

        // Set comment button to show an AlertDialog with two buttons
        commentButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InflateParams")
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder commentDialog = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = getLayoutInflater();
                commentDialog.setView(inflater.inflate(R.layout.comment_dialog, null));
                commentDialog.setTitle("Commentary");
                commentDialog.setCancelable(true);

                // Set positive button to save comment, get comment and confirm the saved state with a Toast message
                commentDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText comment = ((AlertDialog) dialog).findViewById(R.id.comment_dialog);
                        if (comment != null) {
                            String userComment = comment.getText().toString();

                            boolean dataInserted = db.insertData(smileyNumber, userComment, LocalDate.now().toString());
                            if (dataInserted)
                                Toast.makeText(MainActivity.this, "Comment saved", Toast.LENGTH_LONG).show();
                            Log.d("DATABASE_TEST", "'" + userComment + "'");
                        } else {
                            dialog.cancel();
                        }
                    }
                });

                // Set negative button to cancel
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


        // Set button to start HistoryActivity
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });


        // Set button to start PieChartActivity
        pieChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });


        // Set button to share mood
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                String shareText = "Here is my mood of the day : " + db.getCurrentMood().toString() + " ! " + db.getCurrentMood().getComment();
                intent.putExtra(Intent.EXTRA_SUBJECT, shareText);
                startActivity(Intent.createChooser(intent, "Share your mood using"));
            }
        });

    }

    private final View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            v.performClick();
            return mDetector.onTouchEvent(event);

        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        LocalDate dateOnResume = LocalDate.now();

        // Compare date on resume and display default mood if on the next day
        if (dateOnResume.isAfter(currentDate)) {
            smileyNumber = 1;
            setMoodSettings(smileyNumber);
            db.insertData(smileyNumber, null, LocalDate.now().toString());
        } else {
            setMoodSettings(smileyNumber);
        }
    }


        class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_MIN_DISTANCE = 120;
            private static final int SWIPE_THRESHOLD_VELOCITY = 200;


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
                        setSound();
                        db.insertData(smileyNumber, null, LocalDate.now().toString());
                        Log.d("TEST", Integer.toString(smileyNumber));
                    }

                    return false;

                    // Top to bottom
                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.d("TEST", "fling down");
                    if (smileyNumber < colorsList.length - 1) {
                        smileyNumber++;
                        setMoodSettings(smileyNumber);
                        setSound();
                        db.insertData(smileyNumber, null, LocalDate.now().toString());
                        Log.d("TEST", Integer.toString(smileyNumber));
                    }

                    return false;
                }
                return false;
            }

        }

        // Set background color and smiley image depending on chosen mood
        private void setMoodSettings ( int smileyNumber){

            mainConstraintLayout.setBackgroundResource(colorsList[smileyNumber]);
            smiley.setImageResource(imagesList[smileyNumber]);
        }

        // Play sound depending on chosen mood
        private void setSound () {

            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), soundsList[smileyNumber]);
            mediaPlayer.start();
        }
    }