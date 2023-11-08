package com.example.fitguide.Workout_Classes;


import java.util.ArrayList;
import java.util.List;

/*
 * This class creates an object that wraps all the information about a
 * user's workout routines. This object will be stored in a database.
 */
public class WorkoutRoutineList {

    // List of WorkoutRoutines.
    private List<WorkoutRoutine> routineList;

    // Selected Workout Routine
    private WorkoutRoutine selected;

    private int NumberOfRoutines;

    public WorkoutRoutineList(){}

    public WorkoutRoutineList(WorkoutRoutine selectedRoutine){
        routineList = new ArrayList<WorkoutRoutine>();
        selected = selectedRoutine;
        NumberOfRoutines = 0;
    }

    /*
     * Adds a routine to the list. Returns true if its success and it
     * returns false if the routine is already in the list.
     */
    public boolean addWorkoutRoutine(WorkoutRoutine routine){
        if (!routineList.contains(routine)) {
            NumberOfRoutines += 1;
            routineList.add(routine);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Removes the routine from the list. Returns true if the routine is
     * in the list and it returns false if the routine was not in the list.
     */
    public boolean removeWorkoutRoutine(WorkoutRoutine routine){
        for (int i = 0; i < routineList.size(); i++){
            if (routine.getName().equals(routineList.get(i).getName())){
                NumberOfRoutines -= 1;
                routineList.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * Gets the specific routine from the list.
     */
    public WorkoutRoutine getRoutine(int index){
        if (index >= routineList.size()){
            return null;
        } else if (index < 0){
            return null;
        } else {
            return routineList.get(index);
        }
    }

    /*
     * Set the selected workout routine.
     */
    public void setSelected(WorkoutRoutine routine){
        selected = routine;
    }

    /*
     * Getter that returns the selected workout routine.
     */
    public WorkoutRoutine getSelected(){
        return selected;
    }

    /*
     * Getter that returns the list of workout routines.
     */
    public List<WorkoutRoutine> getRoutineList(){
        return routineList;
    }

    /*
     * Sets the number of workout routines.
     */
    public void setNumberOfRoutines(int newCount){ NumberOfRoutines = newCount;}

    /*
     * Getter that returns an integer that represents the number of workout routines.
     */
    public int getNumberOfRoutines(){
        return NumberOfRoutines;
    }
}
