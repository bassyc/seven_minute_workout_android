package com.michaelcarrano.seven_min_workout;

import com.michaelcarrano.seven_min_workout.model.WorkoutContent;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * An activity representing a single Workout detail screen. This activity is only used on handset
 * devices. On tablet-size devices, item details are presented side-by-side with a list of items in
 * a {@link WorkoutListActivity}. <p> This activity is mostly just a 'shell' activity containing
 * nothing more than a {@link WorkoutDetailFragment}.
 */
public class WorkoutDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        // Get the Workout that was selected
        int selected = getIntent().getExtras().getInt(WorkoutDetailFragment.ARG_WORKOUT_POS);
        WorkoutContent.Workout workout = WorkoutContent.WORKOUTS.get(selected);
        Log.i("7min", "Selected: " + selected + " " + workout.name);

        // Set the ActionBar title, icon, and up button values.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(android.R.color.transparent);
        getSupportActionBar().setTitle(workout.name);
        getSupportActionBar()
                .setBackgroundDrawable(new ColorDrawable(Color.parseColor(workout.dark)));

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(WorkoutDetailFragment.ARG_WORKOUT_POS, selected);

            WorkoutDetailFragment fragment = new WorkoutDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.workout_detail_container,
                    fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Finish this activity if rotating to two-pane layout
        if (getResources().getBoolean(R.bool.has_two_panes)) {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, WorkoutListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
