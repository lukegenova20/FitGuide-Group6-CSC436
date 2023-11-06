package com.example.fitguide.Workout_Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * This class creates an object that represents a List of Exercises. This object will be
 * stored in a database.
 */
public class ExerciseList implements Serializable {

    private List<Exercise> exerciseList;

    private String muscleGroup;

    public ExerciseList(){}

    public ExerciseList(String group){
        muscleGroup = group;
        exerciseList = new ArrayList<Exercise>();

        if (muscleGroup.equals("Break")){
            exerciseList.add(new Exercise("Break"));
        }
    }

    /*
     * Adds an exercise to the list. Returns true if its success and it
     * returns false if the exercise is already in the list.
     */
    public boolean addExercise(Exercise exercise){
        if (!exerciseList.contains(exercise)) {
            exerciseList.add(exercise);
            return true;
        } else {
            return false;
        }
    }


    /*
     * Removes the exercise from the list. Returns true if the exercise is
     * in the list and it returns false if the exercise was not in the list.
     */
    public boolean removeExercise(Exercise exercise){
        return exerciseList.remove(exercise);
    }

    /*
     * Returns the size of the list.
     */
    public int size(){
        return exerciseList.size();
    }

    /*
     * Gets the specific exercise from the list.
     */
    public Exercise getExercise(int index){
        if (index >= size()){
            return null;
        } else if (index < 0){
            return null;
        } else {
            return exerciseList.get(index);
        }
    }

    /*
     * Getter for the list of exercises.
     */
    public List<Exercise> getExerciseList(){
        return exerciseList;
    }

    /*
     * Getter for the muscle group the list covers.
     */
    public String getMuscleGroup(){ return muscleGroup;}

}
