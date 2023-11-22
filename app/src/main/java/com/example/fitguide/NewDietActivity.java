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

public class NewDietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diet);

        TextView greekYogurt = findViewById(R.id.textview_greekyogurt);
        TextView eggs = findViewById(R.id.textview_eggs);
        TextView oatmeal = findViewById(R.id.textview_oatmeal);
        TextView chicken = findViewById(R.id.textview_chicken);
        TextView tuna = findViewById(R.id.textview_tuna);
        TextView quinoa = findViewById(R.id.textview_quinoa);
        TextView salmon = findViewById(R.id.textview_salmon);
        TextView turkey = findViewById(R.id.textview_turkey);
        TextView stirFry = findViewById(R.id.textview_stirFry);
        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                startActivity(intent);
            }
        });



        greekYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietGreekYogurt.class);
                startActivity(intent);
            }
        });

        eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietEggs.class);
                startActivity(intent);
            }
        });

        oatmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietOatmeal.class);
                startActivity(intent);
            }
        });

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietChicken.class);
                startActivity(intent);
            }
        });

        tuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietTuna.class);
                startActivity(intent);
            }
        });

        quinoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietQuinoa.class);
                startActivity(intent);
            }
        });

        salmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietSalmon.class);
                startActivity(intent);
            }
        });

        turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietTurkey.class);
                startActivity(intent);
            }
        });

        stirFry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietStiryFry.class);
                startActivity(intent);
            }
        });
    }
}
