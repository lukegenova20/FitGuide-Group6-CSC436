package com.example.fitguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Workout_Selection extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selection);

        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();

        addMainButtonListeners();

        addHeaderListeners();

    }


    /*
     * This function adds event listeners to buttons in the header layout.
     */
    private void addHeaderListeners(){

        ImageButton drop = findViewById(R.id.Dropdown);
        ImageButton settings = findViewById(R.id.Settings);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load pop-up menu
                // Disable menu selection for workout routine creation.
                Toast.makeText(getApplicationContext(), "NEED TO WORK ON THIS", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load Settings page
                Intent switchIntent = new Intent(v.getContext(), DummyPage.class);
                startActivity(switchIntent);
            }
        });
    }

    /*
     * This function adds even listeners to main buttons in the page.
     */
    private void addMainButtonListeners(){
        Button new_workout = findViewById(R.id.new_workout);
        Button load_workout = findViewById(R.id.load_workout);

        AlertDialog.Builder builder = new AlertDialog.Builder(Workout_Selection.this);
        builder.setIcon(R.drawable.baseline_error_24);
        builder.setTitle(" ");
        builder.setCancelable(true);

        new_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        load_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the user didn't create any routines, notify the user.
                DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getUid()).document("Workout_Routines");
                doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        List<WorkoutRoutine> routineList = (List<WorkoutRoutine>) documentSnapshot.get("Workout Routines");
                        if (routineList.size() == 0){
                            builder.setMessage("You haven't made any workout routines yet!");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else {
                            Intent intent = new Intent(Workout_Selection.this, Saved_Workout_Selection.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

    }

    /*
     * This function displays a popup menu for the workout routine style selection.
     */
    private void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Let workout creator know what kind of workout style the user wants for their routine.
                if (item.getItemId() == R.id.routine_style_1){
                    Intent switchIntent = new Intent(Workout_Selection.this, Workout_Creation.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    startActivity(switchIntent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.routine_style_2){
                    Intent switchIntent = new Intent(Workout_Selection.this, Workout_Creation.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    startActivity(switchIntent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.routine_style_3){
                    Intent switchIntent = new Intent(Workout_Selection.this, Workout_Creation.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    startActivity(switchIntent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.routine_style_4){
                    Intent switchIntent = new Intent(Workout_Selection.this, Workout_Creation.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    startActivity(switchIntent);
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        popup.inflate(R.menu.popup_menu_1);
        popup.show();
    }

}