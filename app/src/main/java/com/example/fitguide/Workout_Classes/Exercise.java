package com.example.fitguide.Workout_Classes;

import java.io.Serializable;

/*
 * This class creates an object that represents an Exercise. This object will be
 * stored in a database.
 */
public class Exercise implements Serializable {

    private String name;

    //TODO: Add more fields to Exercise.

    public Exercise(){}

    public Exercise(String givenName){
        name = givenName;
    }

    public String getName(){
        return name;
    }
}
