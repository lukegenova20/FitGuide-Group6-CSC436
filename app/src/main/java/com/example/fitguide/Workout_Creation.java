package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Workout_Creation extends AppCompatActivity {

    
    // Keeps a list of muscle groups that the user considers on focusing.
    ArrayList<String> muscleGroups;

    // Used to determine which button was selected.
    Button dateSelected;

    static final int DAYS_IN_WEEK = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creation);

        muscleGroups = new ArrayList<String>();

        if (muscleGroups.isEmpty()){
            loadMuscleGroups();
        }
        addButtonListeners();

        loadData();
        saveRoutine();
        addHeaderListeners();
    }

    /*
     * Adds specific muscle groups to list of muscle groups
     */
    private void addSpecificMuscleGroup(){
        muscleGroups.add("Arms");
        muscleGroups.add("Biceps");
        muscleGroups.add("Triceps");
        muscleGroups.add("Forearms");
        muscleGroups.add("Chest");
        muscleGroups.add("Shoulders");
        muscleGroups.add("Abs");
        muscleGroups.add("Legs");
        muscleGroups.add("Hamstrings");
        muscleGroups.add("Quadriceps");
        muscleGroups.add("Calves");
        muscleGroups.add("Glutes")
        muscleGroups.add("Hips");
    }

    /*
     * Adds specific muscle groups to list of muscle groups
     */
    private void addPushPullLegsGroup(){
        muscleGroups.add("Push");
        muscleGroups.add("Pull");
        muscleGroups.add("Legs");
    }

    /*
     * This function stores muscles groups into the array of muscle groups based on
     * what the user selected in the workout_selection page.
     */
    private void loadMuscleGroups(){
      String style = getIntent().getStringExtra("workout_style");

      if (style != null){
          if (style.equals(getString(R.string.routine_style_1))){
              addSpecificMuscleGroup();
          } else if (style.equals(getString(R.string.routine_style_2))){
              addPushPullLegsGroup();
          } else if (style.equals(getString(R.string.routine_style_3))){
              muscleGroups.add("Cardio");
          } else {
              addSpecificMuscleGroup();
              addPushPullLegsGroup();
              muscleGroups.add("Cardio");
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
     * Adds Event handlers for each button that represents the days in the week.
     */
    private void addButtonListeners(){
        Button dateButtons[] = new Button[DAYS_IN_WEEK];
        dateButtons[0] = findViewById(R.id.sunday_button);
        dateButtons[1] = findViewById(R.id.monday_button);
        dateButtons[2] = findViewById(R.id.tuesday_button);
        dateButtons[3] = findViewById(R.id.wednesday_button);
        dateButtons[4] = findViewById(R.id.thursday_button);
        dateButtons[5] = findViewById(R.id.friday_button);
        dateButtons[6] = findViewById(R.id.saturday_button);

        for (int i = 0; i < DAYS_IN_WEEK; i++){
            dateButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dateSelected = findViewById(v.getId());

                    // Show popup menu that displays options.
                    PopupMenu menu = new PopupMenu(getApplicationContext(), v);
                    for (int i = 0; i < muscleGroups.size(); i++){
                        menu.getMenu().add(muscleGroups.get(i));
                    }
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            String muscleGroup = (String) item.getTitle();

                            FrameLayout layout = (FrameLayout) dateSelected.getParent();

                            TextView result = (TextView) layout.getChildAt(3);
                            result.setText(muscleGroup);

                            if (!muscleGroup.equals("Break")){

                                // TODO: Create workout list page
                                Intent intent = new Intent(Workout_Creation.this, DummyPage.class);
                                startActivity(intent);
                            }

                            return true;
                        }
                    });
                    menu.show();
                }
            });
        }
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
                // TODO: Save the routine through the database.
                Intent intent = new Intent(Workout_Creation.this, Workout_Selection.class);
                startActivity(intent);
                muscleGroups.clear();
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