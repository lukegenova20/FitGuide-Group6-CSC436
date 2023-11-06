package com.example.fitguide.Workout_Creation;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitguide.R;
import com.example.fitguide.Workout_Classes.ExerciseList;
import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workout_Creation extends AppCompatActivity {

    
    // Keeps a list of muscle groups that the user considers on focusing.
    List<String> muscleGroups;

    // Used to determine which button was selected.
    Button dateSelected;

    static final int DAYS_IN_WEEK = 7;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    WorkoutRoutine currentRoutine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creation);

        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();

        muscleGroups = new ArrayList<String>();

        addButtonListeners();
        loadData();

        saveRoutine();
        addHeaderListeners();

        // TODO: Might add this to a new page called current progress or delete it if there is no time.
        CheckBox check = findViewById(R.id.checkBox);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentRoutine.setSelected(isChecked);
            }
        });
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
        muscleGroups.add("Glutes");
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
    private void loadMuscleGroups(String style){

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
        WorkoutRoutine loadRoutine = (WorkoutRoutine) getIntent().getSerializableExtra("loading");

        if (loadRoutine != null){
            currentRoutine = loadRoutine;

            // Change the body coverage text for each day in the UI based on the current routine.
            Button[] dateButtons = new Button[DAYS_IN_WEEK];
            dateButtons[0] = findViewById(R.id.sunday_button);
            dateButtons[1] = findViewById(R.id.monday_button);
            dateButtons[2] = findViewById(R.id.tuesday_button);
            dateButtons[3] = findViewById(R.id.wednesday_button);
            dateButtons[4] = findViewById(R.id.thursday_button);
            dateButtons[5] = findViewById(R.id.friday_button);
            dateButtons[6] = findViewById(R.id.saturday_button);

            for (int i = 0; i < DAYS_IN_WEEK; i++){
                FrameLayout layout = (FrameLayout) dateButtons[i].getParent();
                TextView result = (TextView) layout.getChildAt(3);
                result.setText(currentRoutine.getMuscleGroup(i));
            }

            loadMuscleGroups(currentRoutine.getStyle());

        } else {
            String style = getIntent().getStringExtra("workout_style");
            loadMuscleGroups(style);

            // Create a new Workout Routine
            Map<String, Integer> counter = new HashMap<String, Integer>();
            firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).
                    document("Workout_Routines").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            int result = documentSnapshot.getLong("Number of Workouts").intValue();
                            result++;
                            String name = "Workout" + result;
                            currentRoutine = new WorkoutRoutine(name, getIntent().getStringExtra("workout_style"));

                            // Update workout routine counter.
                            firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).
                                    document("Workout_Routines").update(
                                            "Number of Workouts", result
                                    );
                        }
                    });
        }
    }

    /*
     * Adds Event handlers for each button that represents the days in the week.
     */
    private void addButtonListeners(){
        Button[] dateButtons = new Button[DAYS_IN_WEEK];
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

                            TextView day = (TextView) layout.getChildAt(1);
                            if (!muscleGroup.equals("Break")){

                                Intent intent = new Intent(Workout_Creation.this,ExerciseListCreation.class);
                                intent.putExtra("day", day.getText());
                                intent.putExtra("routine", currentRoutine);
                                intent.putExtra("group", muscleGroup);
                                startActivity(intent);
                            } else {
                                currentRoutine.setMuscleGroupToDay((String)day.getText(), "Break");
                                ExerciseList temp = new ExerciseList("Break");
                                currentRoutine.addExerciseList((String)day.getText(), temp);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Workout_Creation.this);
        builder.setIcon(R.drawable.baseline_error_24);
        builder.setTitle(" ");
        builder.setCancelable(true);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the routine to the user database.

                // Checks if the routine has been completed.
                if (currentRoutine.workoutRoutineCompleted()) {

                    // Add it to the database.
                    DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Workout_Routines");
                    doc.update("Workout Routine", FieldValue.arrayUnion(currentRoutine));

                    // Go back to Workout Selection.
                    Intent intent = new Intent(Workout_Creation.this, Workout_Selection.class);
                    startActivity(intent);
                    muscleGroups.clear();
                } else {

                    // If the routine hasn't been completed, notify the user.
                    builder.setMessage("Workout Routine hasn't been completed yet.");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
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