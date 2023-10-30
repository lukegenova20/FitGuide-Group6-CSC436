package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {
    TextView username ;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();
        username = findViewById(R.id.welcome_text);
        firebaseFirestore.collection(firebaseAuth.getUid()).
                document("Personal_info").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        username.setText("Welcome "+documentSnapshot.get("First Name").toString() + "!");
                    }
                });
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
                Intent intent = new Intent(MainActivity2.this, Workout_Selection.class);
                startActivity(intent);
            }
        });
    }
}
