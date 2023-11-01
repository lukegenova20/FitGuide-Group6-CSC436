package com.example.fitguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        workoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Assuming "Workout 1" is at position 0
                    Intent intent = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}