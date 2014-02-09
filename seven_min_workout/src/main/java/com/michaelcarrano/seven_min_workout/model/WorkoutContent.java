package com.michaelcarrano.seven_min_workout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * WorkoutContent Class provides data about individual Workouts.
 *
 * @author michaelcarrano
 */
public class WorkoutContent {

    // TODO: Populate the objects here...
    // have a method addRest() which will add to the WORKOUTS_WITH_REST

    /**
     * An array of workouts.
     */
    public static List<Workout> WORKOUTS = new ArrayList<Workout>();

    public static List<Workout> WORKOUTS_WITH_REST = new ArrayList<Workout>();

    public static void addWorkout(Workout workout) {
        WORKOUTS.add(workout);
        WORKOUTS_WITH_REST.add(workout);
    }

    /**
     * A Workout representing information related to the workout item.
     */
    public static class Workout {

        public String id;

        public String name;

        public String content;

        public String video;

        public String dark;

        public String light;

        public Workout(String id, String name, String content, String video, String dark,
                String light) {
            this.id = id;
            this.name = name;
            this.content = content;
            this.video = video;
            this.dark = dark;
            this.light = light;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
