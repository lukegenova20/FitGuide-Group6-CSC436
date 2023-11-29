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
        else if ("Bench Press".equals(workout)) {
            setContentView(R.layout.activity_bench_press);
        }
        else if ("Push-Ups".equals(workout)) {
            setContentView(R.layout.activity_push_ups);
        }
        else if ("Lateral Raises".equals(workout)) {
            setContentView(R.layout.activity_lateral_raises);
        }
        else if ("Overhead Shoulder Press".equals(workout)) {
            setContentView(R.layout.activity_overhead_shoulder_press);
        }
        else if ("Squats".equals(workout)) {
            setContentView(R.layout.activity_squats);
        }
        else if ("Lunges".equals(workout)) {
            setContentView(R.layout.activity_lunges);
        }
        else if ("Lying Leg Curls".equals(workout)) {
            setContentView(R.layout.activity_lying_leg_curls);
        }
        else if ("Leg Press".equals(workout)) {
            setContentView(R.layout.activity_leg_press);
        }
        else if ("Calf Raises".equals(workout)) {
            setContentView(R.layout.activity_calf_raises);
        }
        else if ("Barbell Hip Thrusts".equals(workout)) {
            setContentView(R.layout.activity_barbell_hip_thrust);
        }
        else if ("Hip Abduction Exercise".equals(workout)) {
            setContentView(R.layout.activity_hip_abduction_exercise);
        }
        else if ("Pull-Up".equals(workout)) {
            setContentView(R.layout.activity_pull_up);
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
