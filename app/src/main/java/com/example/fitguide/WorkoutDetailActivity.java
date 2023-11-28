package com.example.fitguide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String workout = getIntent().getStringExtra("workout");

        if ("Deadlift".equals(workout)) {
            setContentView(R.layout.activity_deadlift);
        } else if ("Plank".equals(workout)) {
            setContentView(R.layout.activity_plank);
        }
        else if ("Mountain Climbers".equals(workout)) {
            setContentView(R.layout.activity_mountain_climbers);
        }
        else if ("Crunches".equals(workout)) {
            setContentView(R.layout.activity_crunches);
        }
        else if ("Bicep Curls".equals(workout)) {
            setContentView(R.layout.activity_bicep_curls);
        }
        else if ("Triceps Curls".equals(workout)) {
            setContentView(R.layout.activity_tricep_curls);
        }
        else if ("Barbell Curl".equals(workout)) {
            setContentView(R.layout.activity_barbell_curl);
        }
        else if ("Dumbbell Curl".equals(workout)) {
            setContentView(R.layout.activity_dumbbell_curl);
        }
        else if ("Tricep Dips".equals(workout)) {
            setContentView(R.layout.activity_tricep_dips);
        }
        else if ("Tricep Pushdown".equals(workout)) {
            setContentView(R.layout.activity_tricep_pushdown);
        }
        else if ("Wrist Curls".equals(workout)) {
            setContentView(R.layout.activity_wrist_curl);
        }
        else if ("Reverse Curls".equals(workout)) {
            setContentView(R.layout.activity_reverse_curls);
        }
        else {
            setContentView(R.layout.activity_bench_press);
        }

        Button backButton = findViewById(R.id.back_button);
        if (backButton != null) { // Check if the button exists in the current layout
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // This will close the current activity and go back to the previous one
                }
            });
        }
    }
}
