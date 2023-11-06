package com.example.fitguide.Workout_Creation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitguide.DummyPage;
import com.example.fitguide.R;
import com.example.fitguide.Workout_Classes.Exercise;
import com.example.fitguide.Workout_Classes.ExerciseList;
import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;
import java.util.Map;

public class ExerciseListCreation extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    String day;

    ExerciseList list;

    WorkoutRoutine routine;

    String muscleGroup;

    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list_creation);

        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();
        mainLayout = findViewById(R.id.scroll_layout);

        displayExerciseList();
        showExercises();
        backButton();
        addHeaderListeners();

    }

    /*
     * This function displays the text for the specific exercise programmatically.
     */
    private TextView createText(Exercise exercise){
        TextView text = new TextView(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        text.setLayoutParams(params);

        text.setText(exercise.getName());
        text.setTextSize(30);
        text.setTextColor(getResources().getColor(R.color.black, getTheme()));

        text.setGravity(Gravity.CENTER_HORIZONTAL);

        // TODO: This might not work. Tries to set font to custom google font
        Typeface font = Typeface.createFromAsset(getAssets(), "jockey_one.tff");
        text.setTypeface(font);

        return text;
    }

    /*
     * This creates a line programmatically
     */
    private View createLine(int width){
        View view = new View(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) width * density);
        int newHeight = Math.round( (float) 4 * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                newWidth,
                newHeight
        );

        int margin = Math.round( (float) 40 * density);
        params.setMargins(0, margin, 0, 0);
        view.setLayoutParams(params);

        // TODO: This might not work.
        view.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.border_color, getTheme()));

        return view;
    }

    /*
     * This function creates the remove button for the specific exercise programmatically.
     */
    private Button createRemoveButton(Exercise exercise){
        Button newButton = new Button(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int marginTop = Math.round( (float) 55 * density);
        int marginStart = Math.round( (float) 43 * density);
        params.setMargins(marginStart, marginTop, 0, 0);

        newButton.setLayoutParams(params);

        newButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newButton.setText("Remove");
        newButton.setTextSize(20);
        newButton.setBackgroundTintMode(null); // TODO: Might not work. Disable app background tint

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Remove the exercise from the exercise list.
                list.removeExercise(exercise);

                // Remove the currently displayed list and redisplay the whole list.
                mainLayout.removeAllViews();
                for (int i = 0; i < list.size(); i++){
                    displayExerciseInList(list.getExercise(i));
                }

            }
        });

        return newButton;
    }

    /*
     * Display the specific exercise in the list on the UI
     */
    private void displayExerciseInList(Exercise exercise){
        FrameLayout newFrame = new FrameLayout(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 200 * density);
        int newHeight = Math.round( (float) 130 * density);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                newWidth,
                newHeight
        );

        int margin = Math.round( (float) 25 * density);
        params.setMargins(0, margin, 0, 0);
        newFrame.setLayoutParams(params);
        newFrame.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newFrame.setBackgroundTintMode(null); // TODO: Might not work. Disable app background tint

        newFrame.addView(createText(exercise));

        newFrame.addView(createLine(195));

        newFrame.addView(createRemoveButton(exercise));

        mainLayout.addView(newFrame);

    }

    /*
     * Displays what exercises are list under the selected day.
     */
    private void displayExerciseList(){
        day = getIntent().getStringExtra("day");
        muscleGroup = getIntent().getStringExtra("group");

        TextView text = findViewById(R.id.exercise_text);
        String getText = (String) text.getText();
        text.setText( getText + day);

        // Get the exercise list for the specific day.
        routine = (WorkoutRoutine) getIntent().getSerializableExtra("routine");
        Map<String, ExerciseList> mapping = routine.getDaysToExerciseList();

        // Create new exercise if the user didn't have one for a specific day.
        if (mapping.get(day) == null){
            list = new ExerciseList(muscleGroup);
        } else {
            list = mapping.get(day);

            // If the user selects a new muscle group, create a new Exercise.
            if (!(list.getMuscleGroup().equals(muscleGroup))){
                list = new ExerciseList(muscleGroup);
            }
        }

        routine.setMuscleGroupToDay(day, muscleGroup);

        // Display each exercise in the UI.
        for (int i = 0; i < list.size(); i++){
            displayExerciseInList(list.getExercise(i));
        }

    }

    /*
     * This function creates the add button for the specific exercise programmatically.
     */
    private Button createAddButton(Exercise exercise){
        Button newButton = new Button(getApplicationContext());

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 100 * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                newWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int marginTop = Math.round( (float) 65 * density);
        int marginStart = Math.round( (float) 20 * density);
        params.setMargins(marginStart, marginTop, 0, 0);

        newButton.setLayoutParams(params);

        newButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newButton.setText("Add");
        newButton.setTextSize(20);
        newButton.setBackgroundTintMode(null); // TODO: Might not work. Disable app background tint

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add the exercise to the list.
                boolean result = list.addExercise(exercise);
                if (result){

                    // Remove the currently displayed list and redisplay the whole list.
                    LinearLayout exerciseLayout = findViewById(R.id.scroll_layout_2);
                    exerciseLayout.removeAllViews();
                    for (int i = 0; i < list.size(); i++){
                        displayExerciseInList(list.getExercise(i));
                    }
                }
            }
        });

        return newButton;
    }

    /*
     * This function creates the detail button for the specific exercise programmatically.
     */
    private Button createDetailButton(Exercise exercise){
        Button newButton = new Button(getApplicationContext());

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 100 * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                newWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int marginTop = Math.round( (float) 65 * density);
        int marginStart = Math.round( (float) 180 * density);
        params.setMargins(marginStart, marginTop, 0, 0);

        newButton.setLayoutParams(params);

        newButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newButton.setText("Detail");
        newButton.setTextSize(20);
        newButton.setBackgroundTintMode(null); // TODO: Might not work. Disable app background tint

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Link to exercise encyclopedia
                Intent switchIntent = new Intent(v.getContext(), DummyPage.class);
                startActivity(switchIntent);
            }
        });

        return newButton;
    }

    /*
     * This function displays an exercise to potentially select on the UI.
     */
    private void displayExercise(Exercise exercise){
        FrameLayout newFrame = new FrameLayout(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 300 * density);
        int newHeight = Math.round( (float) 150 * density);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                newWidth,
                newHeight
        );

        int margin = Math.round( (float) 25 * density);
        params.setMargins(0, margin, 0, 0);
        newFrame.setLayoutParams(params);
        newFrame.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newFrame.setBackgroundTintMode(null); // TODO: Might not work. Disable app background tint

        newFrame.addView(createText(exercise));

        newFrame.addView(createLine(295));

        newFrame.addView(createAddButton(exercise));

        newFrame.addView(createDetailButton(exercise));

        LinearLayout exerciseLayout = findViewById(R.id.scroll_layout_2);
        exerciseLayout.addView(newFrame);
    }

    /*
     * This function shows what kind of exercises the user can select.
     */
    private void showExercises(){
        TextView text = findViewById(R.id.exercise_group_text);
        String getText = (String) text.getText();
        text.setText(getText + muscleGroup + " Exercise Group");

        // TODO Might change collections name for exercises.
        DocumentReference docs = firebaseFirestore.collection("Exercises").document(muscleGroup);
        docs.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                List<Exercise> exercises = (List<Exercise>) documentSnapshot.get("Exercises");
                for (int i = 0; i < exercises.size(); i++){
                    displayExercise(exercises.get(i));
                }
            }
        });


    }

    /*
     * Add the event handler for the back button.
     */
    private void backButton(){
        Button back = findViewById(R.id.back_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Update routine in the list of workout routines
                DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Workout_Routines");
                doc.update("Workout Routines", FieldValue.arrayRemove(routine));
                routine.addExerciseList(day, list);
                doc.update("Workout Routines", FieldValue.arrayUnion(routine));

                // Go back to the workout creation page.
                Intent switchIntent = new Intent(getApplicationContext(), Workout_Creation.class);
                startActivity(switchIntent);
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