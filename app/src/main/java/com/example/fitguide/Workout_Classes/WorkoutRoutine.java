package com.example.fitguide.Workout_Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class creates an object that represents a Workout Routine. This object will be
 * stored in a database.
 */
public class WorkoutRoutine implements Serializable {

    // Name of the workout routine
    private String name;

    // Workout Routine Style
    private String style;

    // The Exercise List for each Day
    private Map<String, ExerciseList> daysToExerciseList;

    // Which muscle group is covered on which day.
    private List<String> muscleGroupToDays;

    // Has the user selected this routine?
    private boolean selected;

    public WorkoutRoutine(){}

    public WorkoutRoutine(String givenName, String givenStyle){
        name = givenName;
        style = givenStyle;

        daysToExerciseList = new HashMap<String, ExerciseList>();
        daysToExerciseList.put("Sunday", null);
        daysToExerciseList.put("Monday", null);
        daysToExerciseList.put("Tuesday", null);
        daysToExerciseList.put("Wednesday", null);
        daysToExerciseList.put("Thursday", null);
        daysToExerciseList.put("Friday", null);
        daysToExerciseList.put("Saturday", null);

        muscleGroupToDays = new ArrayList<String>();
        for (int i = 0; i < 7; i++){
            muscleGroupToDays.add("");
        }

        selected = false;

    }

    /*
     * This function adds an exercise list to one of the days of the week.
     */
    public boolean addExerciseList(String day, ExerciseList list){
        if ((daysToExerciseList.containsKey(day)) && (list != null)){
            if (list.size() != 0) {
                daysToExerciseList.replace(day, list);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /*
     * This function assigns a muscle group to a day of the week.
     */
    public void setMuscleGroupToDay(String day, String muscleGroup){
        if (day.equals("Sunday")){
            muscleGroupToDays.set(0, muscleGroup);
        } else if (day.equals("Monday")){
            muscleGroupToDays.set(1, muscleGroup);
        } else if (day.equals("Tuesday")){
            muscleGroupToDays.set(2, muscleGroup);
        } else if (day.equals("Wednesday")){
            muscleGroupToDays.set(3, muscleGroup);
        } else if (day.equals("Thursday")){
            muscleGroupToDays.set(4, muscleGroup);
        } else if (day.equals("Friday")){
            muscleGroupToDays.set(5, muscleGroup);
        } else if (day.equals("Saturday")){
            muscleGroupToDays.set(6, muscleGroup);
        }
    }

    /*
     * Gets the specific muscle group on the specific day..
     */
    public String getMuscleGroup(int index){
        return muscleGroupToDays.get(index);
    }

    /*
     * Determines if the user has entered an exercise list for each day.
     */
    public boolean workoutRoutineCompleted(){
        for (Map.Entry<String, ExerciseList> day : daysToExerciseList.entrySet()){
            if (day.getValue() == null){
                return false;
            }
        }
        return true;
    }

    /*
     * Selects or unselects the current routine.
     */
    public void setSelected(boolean newBool){
        selected = newBool;
    }

    /*
     * Getter for Workout Name
     */
    public String getName() {
        return name;
    }

    /*
     * Setter for Workout Name
     */
    public void setName(String newName) {
        name = newName;
    }

    /*
     * Getter for Workout Style
     */
    public String getStyle() {
        return style;
    }

    /*
     * Getter for Days To Exercise List Mapping.
     */
    public Map<String, ExerciseList> getDaysToExerciseList(){
        return daysToExerciseList;
    }

    /*
     * Getter for the array that has what muscle group is covered for each day.
     */
    public List<String> getMuscleGroupToDays() {
        return muscleGroupToDays;
    }

    /*
     * Determines if the user is using this workout routine.
     */
    public boolean getSelected(){
        return selected;
    }
}
