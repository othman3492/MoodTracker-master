package com.othman.moodtracker.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.othman.moodtracker.R;

import java.util.List;


public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private Context context;
    List<Mood> moodList;

    public MoodAdapter(List<Mood> moodList, Context context) {
        this.moodList = moodList;
        this.context = context;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.test_layout, viewGroup, false);
        return new MoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder viewHolder, int position) {


    }


    @Override
    public int getItemCount() {
        return 0;
    }




    public class MoodViewHolder extends RecyclerView.ViewHolder {

        private View historyView;
        private TextView days;
        private ImageView commentButton;
        private ConstraintLayout historyConstraintLayout;

        public MoodViewHolder(View itemView) {
            super(itemView);

            historyView = itemView.findViewById(R.id.history_view);
            days = itemView.findViewById(R.id.history_textview);
            commentButton = itemView.findViewById(R.id.history_imageview);
            historyConstraintLayout = itemView.findViewById(R.id.history_constraint_layout);
        }
    }
}
