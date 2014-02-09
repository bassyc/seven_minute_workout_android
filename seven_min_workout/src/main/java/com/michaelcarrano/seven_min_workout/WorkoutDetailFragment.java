package com.michaelcarrano.seven_min_workout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import com.michaelcarrano.seven_min_workout.model.WorkoutContent;
import com.michaelcarrano.seven_min_workout.model.WorkoutContent.Workout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a single Workout detail screen. This fragment is either contained in a
 * {@link WorkoutListActivity} in two-pane mode (on tablets) or a {@link WorkoutDetailActivity} on
 * handsets.
 */
public class WorkoutDetailFragment extends Fragment {

    /**
     * The fragment argument representing the Workout ID that this fragment represents.
     */
    public static final String ARG_WORKOUT_POS = "workout_pos";

    /**
     * The Workout content this fragment is presenting.
     */
    private Workout mWorkout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
     * screen orientation changes).
     */
    public WorkoutDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_WORKOUT_POS)) {
            mWorkout = WorkoutContent.WORKOUTS.get(getArguments().getInt(ARG_WORKOUT_POS));
        }

        video();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        // Show the workout content as text in a TextView.
        if (mWorkout != null) {
            TextView content = (TextView) rootView.findViewById(R.id.workout_detail);
            content.setText(mWorkout.content);
            content.setTextColor(Color.WHITE);
        }

        // Set background color of view
        rootView.setBackgroundColor(Color.parseColor(mWorkout.light));

        return rootView;
    }

    // TODO: Handle rotation so video does not start from beginning.
    public void video() {
        YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment
                .newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.youtube_fragment, youTubePlayerSupportFragment).commit();

        youTubePlayerSupportFragment
                .initialize("AIzaSyAeh3-9IX47bjKm5hQtZPEZl6gYzzzX588", new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b) {
                        if (!b) {
                            youTubePlayer.cueVideo(mWorkout.video);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                            YouTubeInitializationResult youTubeInitializationResult) {
                        if (youTubeInitializationResult.isUserRecoverableError()) {
                            youTubeInitializationResult.getErrorDialog(getActivity(), 1).show();
                        } else {
                            String errorMessage = String.format(getString(R.string.error_player),
                                    youTubeInitializationResult.toString());
                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
