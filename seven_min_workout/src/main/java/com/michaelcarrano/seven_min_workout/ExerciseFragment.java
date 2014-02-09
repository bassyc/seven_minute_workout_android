package com.michaelcarrano.seven_min_workout;

import com.michaelcarrano.seven_min_workout.adapter.WorkoutListAdapter;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent.Workout;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
     * screen orientation changes).
     */
    public ExerciseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain instance on device rotation
        setRetainInstance(true);

        // Insert rests into the workout content
        List<Workout> workouts = WorkoutContent.WORKOUTS_WITH_REST;
        for (int i = 1; i < WorkoutContent.WORKOUTS.size() * 2 - 1; i = i + 2) {
            workouts.add(i,
                    new Workout("0", getString(R.string.rest), "", "",
                            getString(R.color.rest_dark),
                            getString(R.color.rest_light)));
        }
        // Set the ListView adapter
        setListAdapter(new WorkoutListAdapter(getActivity(), workouts));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Remove the ListView divider
        getListView().setDividerHeight(0);
    }

}
