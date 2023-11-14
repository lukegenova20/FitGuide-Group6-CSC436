package com.example.fitguide;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String workout = getIntent().getStringExtra("workout");

        if ("Deadlift".equals(workout)) {
            setContentView(R.layout.activity_deadlift);
        } else {
            setContentView(R.layout.activity_bench_press);
        }
    }

}
