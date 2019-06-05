package com.othman.moodtracker.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.othman.moodtracker.R;
import com.othman.moodtracker.model.Mood;

import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;


public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private final ArrayList<Mood> moodList;
    private final RecyclerView recyclerView;


    public MoodAdapter(ArrayList<Mood> moodList, RecyclerView recyclerView) {
        this.moodList = moodList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Find view holder's height by dividing parent by the number of view holders
        int height = recyclerView.getHeight() / 7;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        ConstraintLayout constraintLayout = v.findViewById(R.id.history_constraint_layout);

        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();

        // Apply view holder's height
        layoutParams.height = height;
        constraintLayout.setLayoutParams(layoutParams);

        return new MoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder viewHolder, int position) {

        // Display history list
        final Mood mood = moodList.get(position);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(viewHolder.historyConstraintLayout);

        // Apply color and size settings depending on moods displayed
        switch (mood.getMoodType()) {
            case 0:
                viewHolder.historyView.setBackgroundResource(R.color.banana_yellow);
                constraintSet.constrainPercentWidth(R.id.history_view, 1);
                break;
            case 1:
                viewHolder.historyView.setBackgroundResource(R.color.light_sage);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.8f);
                break;
            case 2:
                viewHolder.historyView.setBackgroundResource(R.color.cornflower_blue_65);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.6f);
                break;
            case 3:
                viewHolder.historyView.setBackgroundResource(R.color.warm_grey);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.4f);
                break;
            case 4:
                viewHolder.historyView.setBackgroundResource(R.color.faded_red);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.2f);
                break;
            default:
                constraintSet.constrainPercentHeight(R.id.history_view, 0f);
        }

        viewHolder.historyConstraintLayout.setConstraintSet(constraintSet);


        // Get the exact number of days for each mood displayed
        for (int i = 0; i < 7; i++) {
            long nbDays = ChronoUnit.DAYS.between(org.threeten.bp.LocalDate.now(), org.threeten.bp.LocalDate.parse(mood.getDate()));

            switch ((int) -nbDays) {
                case 0:
                    viewHolder.days.setText("Today");
                    break;
                case 1:
                    viewHolder.days.setText("Yesterday");
                    break;
                default:
                    viewHolder.days.setText(-nbDays + " days ago");
                    break;
            }
        }

        // Set the comment button visible if there's a comment to show, and display it in a Toast message
        if (mood.getComment() == null)
            viewHolder.commentButton.setVisibility(View.GONE);

        viewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), mood.getComment(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return moodList.size();
    }



    class MoodViewHolder extends RecyclerView.ViewHolder {

        final View historyView;
        final TextView days;
        final ImageView commentButton;
        final ConstraintLayout historyConstraintLayout;

        MoodViewHolder(View view) {
            super(view);

            historyView = view;
            days = itemView.findViewById(R.id.history_textview);
            commentButton = itemView.findViewById(R.id.history_imageview);
            historyConstraintLayout = itemView.findViewById(R.id.history_constraint_layout);
        }
    }
}
