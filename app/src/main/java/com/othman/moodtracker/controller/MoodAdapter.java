package com.othman.moodtracker.controller;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    Context context;
    List<Mood> moodList;
    RecyclerView recyclerView;
    private String[] daysList = {"Today", "Yesterday", "2 days ago", "3 days ago", "4 days ago", "5 days ago", "6 days ago"};


    public MoodAdapter(Context context, ArrayList<Mood> moodList, RecyclerView recyclerView) {
        this.moodList = moodList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int height = recyclerView.getHeight() / 7;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        ConstraintLayout constraintLayout = v.findViewById(R.id.history_constraint_layout);

        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();

        layoutParams.height = height;
        constraintLayout.setLayoutParams(layoutParams);

        return new MoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder viewHolder, int position) {

        final Mood mood = moodList.get(position);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(viewHolder.historyConstraintLayout);

        switch (mood.getMoodType()) {
            case 0 :
                viewHolder.historyView.setBackgroundResource(R.color.banana_yellow);
                constraintSet.constrainPercentWidth(R.id.history_view, 1);
                break;
            case 1 :
                viewHolder.historyView.setBackgroundResource(R.color.light_sage);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.8f);
                break;
            case 2 :
                viewHolder.historyView.setBackgroundResource(R.color.cornflower_blue_65);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.6f);
                break;
            case 3 :
                viewHolder.historyView.setBackgroundResource(R.color.warm_grey);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.4f);
                break;
            case 4 :
                viewHolder.historyView.setBackgroundResource(R.color.faded_red);
                constraintSet.constrainPercentWidth(R.id.history_view, 0.2f);
                break;
            default :
                constraintSet.constrainPercentHeight(R.id.history_view, 0f);
        }

        viewHolder.historyConstraintLayout.setConstraintSet(constraintSet);





        viewHolder.days.setText(daysList[position]);

        if (moodList.get(position).getComment() == null)
            viewHolder.commentButton.setVisibility(View.INVISIBLE);

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




    public class MoodViewHolder extends RecyclerView.ViewHolder {

        View historyView;
        TextView days;
        ImageView commentButton;
        ConstraintLayout historyConstraintLayout;

        public MoodViewHolder(View view) {
            super(view);

            historyView = view;
            days = itemView.findViewById(R.id.history_textview);
            commentButton = itemView.findViewById(R.id.history_imageview);
            historyConstraintLayout = itemView.findViewById(R.id.history_constraint_layout);
        }
    }
}
