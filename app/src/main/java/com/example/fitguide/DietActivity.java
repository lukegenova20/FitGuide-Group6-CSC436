package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        Button designDiet = findViewById(R.id.new_diet);
        Button loadDiet = findViewById(R.id.load_diet);
        designDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewDietActivity.class);
                startActivity(intent);
            }
        });

        loadDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Diet_Creation.class);
                startActivity(intent);
            }
        });

        backButton();


    }

    /*
     * Add the event handler for the back button.
     */
    private void backButton(){
        Button back = findViewById(R.id.back_button_meal);

        // Go back to the home page.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(v.getContext(), MainActivity2.class);
                startActivity(switchIntent);
                finish();
            }
        });

    }
}
