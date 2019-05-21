import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.othman.moodtracker.R;

import java.util.List;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {


    class MoodViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout historyConstraintLayout;


        public MoodViewHolder (View v) {

            super(v);
            historyConstraintLayout = v.findViewById(R.id.history_constraint_layout);
        }

    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.mood_history, parent, false);
        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder moodViewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
