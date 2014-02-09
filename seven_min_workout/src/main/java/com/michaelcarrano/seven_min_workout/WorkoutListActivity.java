package com.michaelcarrano.seven_min_workout;

import com.crashlytics.android.Crashlytics;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * An activity representing a list of Workouts. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link WorkoutDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes. <p>
 * The activity makes heavy use of fragments. The list of items is a {@link WorkoutListFragment} and
 * the item details (if present) is a {@link WorkoutDetailFragment}. <p> This activity also
 * implements the required {@link WorkoutListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class WorkoutListActivity extends ActionBarActivity
        implements WorkoutListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);

        // Add workouts to display
        if (WorkoutContent.WORKOUTS.isEmpty()) {
            WorkoutContent.addWorkout(new Workout("1", getString(R.string.jumping_jacks),
                    getString(R.string.jumping_jacks_desc), getString(R.string.jumping_jacks_video),
                    getString(R.color.jumping_jacks_dark), getString(R.color.jumping_jacks_light)));
            WorkoutContent.addWorkout(new Workout("2", getString(R.string.wall_sits),
                    getString(R.string.wall_sits_desc), getString(R.string.wall_sits_video),
                    getString(R.color.wall_sits_dark), getString(R.color.wall_sits_light)));
            WorkoutContent.addWorkout(new Workout("3", getString(R.string.push_ups),
                    getString(R.string.push_ups_desc), getString(R.string.push_ups_video),
                    getString(R.color.push_ups_dark), getString(R.color.push_ups_light)));
            WorkoutContent.addWorkout(new Workout("4", getString(R.string.abdominal_crunches),
                    getString(R.string.abdominal_crunches_desc),
                    getString(R.string.abdominal_crunches_video),
                    getString(R.color.abdominal_crunches_dark),
                    getString(R.color.abdominal_crunches_light)));
            WorkoutContent.addWorkout(new Workout("5", getString(R.string.step_ups_onto_a_chair),
                    getString(R.string.step_ups_onto_a_chair_desc),
                    getString(R.string.step_ups_onto_a_chair_video),
                    getString(R.color.step_ups_onto_a_chair_dark),
                    getString(R.color.step_ups_onto_a_chair_light)));
            WorkoutContent.addWorkout(
                    new Workout("6", getString(R.string.squats), getString(R.string.squats_desc),
                            getString(R.string.squats_video), getString(R.color.squats_dark),
                            getString(R.color.squats_light)));
            WorkoutContent.addWorkout(new Workout("7", getString(R.string.triceps_dips_on_a_chair),
                    getString(R.string.triceps_dips_on_a_chair_desc),
                    getString(R.string.triceps_dips_on_a_chair_video),
                    getString(R.color.triceps_dips_on_a_chair_dark),
                    getString(R.color.triceps_dips_on_a_chair_light)));
            WorkoutContent.addWorkout(
                    new Workout("8", getString(R.string.planks), getString(R.string.planks_desc),
                            getString(R.string.planks_video), getString(R.color.planks_dark),
                            getString(R.color.planks_light)));
            WorkoutContent.addWorkout(
                    new Workout("9", getString(R.string.high_knees_running_in_place),
                            getString(R.string.high_knees_running_in_place_desc),
                            getString(R.string.high_knees_running_in_place_video),
                            getString(R.color.high_knees_running_in_place_dark),
                            getString(R.color.high_knees_running_in_place_light)));
            WorkoutContent.addWorkout(
                    new Workout("10", getString(R.string.lunges), getString(R.string.lunges_desc),
                            getString(R.string.lunges_video), getString(R.color.lunges_dark),
                            getString(R.color.lunges_light)));
            WorkoutContent.addWorkout(new Workout("11", getString(R.string.push_ups_and_rotations),
                    getString(R.string.push_ups_and_rotations_desc),
                    getString(R.string.push_ups_and_rotations_video),
                    getString(R.color.push_ups_and_rotations_dark),
                    getString(R.color.push_ups_and_rotations_light)));
            WorkoutContent.addWorkout(new Workout("12", getString(R.string.side_planks),
                    getString(R.string.side_planks_desc), getString(R.string.side_planks_video),
                    getString(R.color.side_planks_dark), getString(R.color.side_planks_light)));
        }

        setContentView(R.layout.activity_workout_list);

        if (findViewById(R.id.workout_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((WorkoutListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.workout_list))
                    .setActivateOnItemClick(true);
        }

        getSupportActionBar().setTitle(getString(R.string.app_label));
    }

    @Override
    public void onResume() {
        super.onResume();
        mTwoPane = getResources().getBoolean(R.bool.has_two_panes);
    }

    /**
     * Callback method from {@link WorkoutListFragment.Callbacks} indicating that the item with the
     * given ID was selected.
     */
    @Override
    public void onItemSelected(int position) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(WorkoutDetailFragment.ARG_WORKOUT_POS, position);
            WorkoutDetailFragment fragment = new WorkoutDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.workout_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, WorkoutDetailActivity.class);
            detailIntent.putExtra(WorkoutDetailFragment.ARG_WORKOUT_POS, position);
            startActivity(detailIntent);
        }
    }

    /**
     * Add menu items to ActionBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Handle menu item clicks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_workout:
                Intent exercise = new Intent(this, ExerciseActivity.class);
                startActivity(exercise);
                break;
            case R.id.menu_about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.menu_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
        }
        return true;
    }
}
