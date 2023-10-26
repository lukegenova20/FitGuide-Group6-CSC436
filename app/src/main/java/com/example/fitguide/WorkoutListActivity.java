package com.example.fitguide;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

        // Create a list of dummy workouts
        String[] workouts = new String[]{
                "Workout 1",
                "Workout 2",
                "Workout 3",
                // ... Add as many as you need
        };

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);
        workoutListView.setAdapter(adapter);
    }
}
