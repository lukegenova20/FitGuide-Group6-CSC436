package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class Workout_Selection extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selection);

        addMainButtonListeners();

        addHeaderListeners();

    }


    /*
     * This function adds event listeners to buttons in the header layout.
     */
    private void addHeaderListeners(){

        ImageButton drop = findViewById(R.id.Dropdown);
        ImageButton settings = findViewById(R.id.Settings);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load pop-up menu
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load Settings page
                Intent switchIntent = new Intent(v.getContext(), DummyPage.class);
                startActivity(switchIntent);
            }
        });
    }

    /*
     * This function adds even listeners to main buttons in the page.
     */
    private void addMainButtonListeners(){
        Button new_workout = findViewById(R.id.new_workout);
        Button load_workout = findViewById(R.id.load_workout);

        new_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        load_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load to the Workout Routine Calender Page
                Intent intent = new Intent(Workout_Selection.this, WorkoutRoutineActivity.class);
                startActivity(intent);

            }
        });

    }

    /*
     * This function displays a popup menu for the workout routine style selection.
     */
    private void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu_1);
        popup.show();
    }


    /*
     * Event Handler for when menu item has been clicked.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO: Pass Data to Workout Routine Creation Page
        if (item.getItemId() == R.id.routine_style_1){
            Intent switchIntent = new Intent(Workout_Selection.this, DummyPage.class);
            startActivity(switchIntent);
            return true;
        } else if (item.getItemId() == R.id.routine_style_2){
            Intent switchIntent = new Intent(Workout_Selection.this, DummyPage.class);
            startActivity(switchIntent);
            return true;
        } else if (item.getItemId() == R.id.routine_style_3){
            Intent switchIntent = new Intent(Workout_Selection.this, DummyPage.class);
            startActivity(switchIntent);
            return true;
        } else if (item.getItemId() == R.id.routine_style_4){
            Intent switchIntent = new Intent(Workout_Selection.this, DummyPage.class);
            startActivity(switchIntent);
            return true;
        } else {
            return false;
        }
    }
}