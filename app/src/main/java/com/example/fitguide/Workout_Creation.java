package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class Workout_Creation extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ArrayList<String> muscleGroups;

    Button dateSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creation);

        loadMuscleGroups();

        loadData();

        saveRoutine();
        addHeaderListeners();
    }

    /*
     * This function stores muscles groups into the array of muscle groups based on
     * what the user selected in the workout_selection page.
     */
    private void loadMuscleGroups(){
      String style = getIntent().getStringExtra("workout_style");

      if (style != null){
          if (style.equals(getString(R.string.routine_style_1))){
              // TODO: add muscle groups
          } else if (style.equals(getString(R.string.routine_style_2))){
              // TODO: add muscle groups
          } else if (style.equals(getString(R.string.routine_style_3))){
              // TODO: add muscle groups
          } else {
              // TODO: add muscle groups
          }
          muscleGroups.add("Break");
      }

    }

    /*
     * If the user selected a pre-existing workout routine saved in user data, this
     * function would load up all of that data and display it.
     */
    private void loadData(){
        String loadData = getIntent().getStringExtra("load");

        if (loadData != null){
            // TODO: Query into User database and fill in data.
        }
    }

    /*
     * Event handler for when a button has been pressed for one of the
     * days of the week.
     */
    private void OnDateClickListener(View v) {

        dateSelected = findViewById(v.getId());

        // Show popup menu that displays options.
        PopupMenu menu = new PopupMenu(this, v);
        for (int i = 0; i < muscleGroups.size(); i++){
            menu.getMenu().add(muscleGroups.get(i));
        }
        menu.setOnMenuItemClickListener(this);
        menu.show();

    }

    /*
     * Event Handler for when menu item has been clicked.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        String muscleGroup = (String) item.getTitle();

        if (!muscleGroup.equals("Break")){
            Intent intent = new Intent(Workout_Creation.this, DummyPage.class);
            startActivity(intent);
        }

        return false;
    }

    /*
     * This function sets up the event handler for when the
     * user wants to save the routine they created or updated.
     */
    private void saveRoutine(){
        Button saveButton = findViewById(R.id.save_workout);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Save the routine.
                Intent intent = new Intent(Workout_Creation.this, Workout_Selection.class);
                startActivity(intent);
            }
        });
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

                // Disable menu selection for workout routine creation.
                Toast.makeText(getApplicationContext(), "Disabled for Routine Creation", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disable menu selection for workout routine creation.
                Toast.makeText(getApplicationContext(), "Disabled for Routine Creation", Toast.LENGTH_SHORT).show();
            }
        });
    }
}