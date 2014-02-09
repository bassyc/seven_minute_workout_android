package com.michaelcarrano.seven_min_workout.adapter;

import com.michaelcarrano.seven_min_workout.R;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent.Workout;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Custom adapter for WorkoutListFragment to display the ID and Name of Workout in a ListView row.
 *
 * @author michaelcarrano
 */
public class WorkoutListAdapter extends BaseAdapter {

    private static LayoutInflater mLayoutInflater = null;
    private List<Workout> mWorkout;

    public WorkoutListAdapter(Activity ctx, List<Workout> workouts) {
        this.mLayoutInflater = ctx.getLayoutInflater();
        this.mWorkout = workouts;
    }

    @Override
    public int getCount() {
        return mWorkout.size();
    }

    @Override
    public Object getItem(int position) {
        return mWorkout.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Workout workout = (Workout) getItem(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_workout_row, parent, false);

            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.workout_id);
            holder.name = (TextView) convertView.findViewById(R.id.workout_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set the content for the ListView row
        holder.id.setText(workout.id);
        holder.name.setText(workout.name);

        // Set the color for the ListView row
        holder.id.setBackgroundColor(Color.parseColor(workout.dark));
        holder.name.setBackgroundColor(Color.parseColor(workout.light));

        return convertView;
    }

    public static class ViewHolder {

        public TextView id;

        public TextView name;
    }
}

