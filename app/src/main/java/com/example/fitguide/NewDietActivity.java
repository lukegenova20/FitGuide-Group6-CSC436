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

        // LOW CALORIE
        // Breakfast
        TextView greekYogurt = findViewById(R.id.textview_greekyogurt);
        TextView eggs = findViewById(R.id.textview_eggs);
        TextView oatmeal = findViewById(R.id.textview_oatmeal);
        // Lunch
        TextView chicken = findViewById(R.id.textview_chicken);
        TextView tuna = findViewById(R.id.textview_tuna);
        TextView quinoa = findViewById(R.id.textview_quinoa);
        // Dinner
        TextView salmon = findViewById(R.id.textview_salmon);
        TextView turkey = findViewById(R.id.textview_turkey);
        TextView stirFry = findViewById(R.id.textview_stirFry);
        // HIGH CALORIE
        // Breakfast
        TextView avocado = findViewById(R.id.textview_avocado_eggs);
        TextView fatYogurt = findViewById(R.id.textview_fat_yogurt);
        TextView smoothie = findViewById(R.id.textview_smoothie);
        // Lunch
        TextView beef = findViewById(R.id.textview_beef_stirfry);
        TextView burrito = findViewById(R.id.textview_burrito);
        TextView curry = findViewById(R.id.textview_chicken_curry);
        // Dinner
        TextView pork = findViewById(R.id.textview_pork);
        TextView spaghetti = findViewById(R.id.textview_spaghetti);
        TextView tilapia = findViewById(R.id.textview_tilapia);

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
                Intent intent = new Intent(getApplicationContext(), DietStirFry.class);
                startActivity(intent);
            }
        });

        avocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietAvocado.class);
                startActivity(intent);
            }
        });

        smoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietSmoothie.class);
                startActivity(intent);
            }
        });

        fatYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietFatGreek.class);
                startActivity(intent);
            }
        });

        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietBeef.class);
                startActivity(intent);
            }
        });

        burrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietBurrito.class);
                startActivity(intent);
            }
        });

        curry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietCurry.class);
                startActivity(intent);
            }
        });

        pork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietPork.class);
                startActivity(intent);
            }
        });

        spaghetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietSpaghetti.class);
                startActivity(intent);
            }
        });

        tilapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DietTilapia.class);
                startActivity(intent);
            }
        });
    }
}
