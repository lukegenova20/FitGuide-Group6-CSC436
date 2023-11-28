package com.example.fitguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutListActivity extends AppCompatActivity {

    ListView workoutListView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutListView = findViewById(R.id.workout_list);
        Button backButton = findViewById(R.id.back_button2);

        // Create a list of dummy workouts
        String[] workouts = new String[]{
                "Bench Press",
                "Deadlift",
                "Plank",
                "Mountain Climbers",
                "Crunches",
                "-->  Arms  <---",
                "Bicep Curls",
                "Triceps Curls",
                "-->  Biceps  <---",
                "Barbell Curl",
                "Dumbbell Curl",
                "-->  Triceps  <---",
                "Tricep Dips",
                "Tricep Pushdown",
                "-->  Forearms  <---",
                "Wrist Curls",
                "Reverse Curls",

                // ... Add as many as you need
        };
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);
        workoutListView.setAdapter(adapter);

        workoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                // Pass the selected workout name to WorkoutDetailActivity
                intent.putExtra("workout", workouts[position]);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
