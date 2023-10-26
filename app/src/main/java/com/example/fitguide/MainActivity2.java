package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView designDiet = findViewById(R.id.design_diet);
        ImageView selectWorkout = findViewById(R.id.select_workout);
        ImageView workoutEncyclopedia = findViewById(R.id.workout_encyclopedia);
        workoutEncyclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkoutListActivity.class);
                startActivity(intent);
            }
        });


        designDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, DietActivity.class);
                startActivity(intent);
            }
        });

        selectWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, WorkoutRoutineActivity.class);
                startActivity(intent);
            }
        });
    }
}
