package com.example.fitguide.Workout_Creation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fitguide.DietActivity;
import com.example.fitguide.MainActivity2;
import com.example.fitguide.MainActivityLogin;
import com.example.fitguide.R;
import com.example.fitguide.Settings.Settings_Page;
import com.example.fitguide.WorkoutListActivity;
import com.example.fitguide.Workout_Classes.WorkoutRoutineList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

        backButton();

    }

    /*
     * Add the event handler for the back button.
     */
    private void backButton(){
        Button back = findViewById(R.id.back_button_selection);

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


    /*
     * This function adds event listeners to buttons in the header layout.
     */
    private void addHeaderListeners(){

        ImageButton drop = findViewById(R.id.Dropdown);
        ImageButton settings = findViewById(R.id.Settings);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDropdownPopup(v);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(v.getContext(), Settings_Page.class);
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
                DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Workout_Routines");
                doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        WorkoutRoutineList routineList = documentSnapshot.toObject(WorkoutRoutineList.class);
                        if (routineList.getRoutineList().size() == 0){
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
     * Show popup menu that gives the user the option to directly jump to a specific page, or
     * sign out of their account.
     */
    private void showDropdownPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Let workout creator know what kind of workout style the user wants for their routine.
                if (item.getItemId() == R.id.sign_out){
                    Intent switchIntent = new Intent(Workout_Selection.this, MainActivityLogin.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    firebaseAuth.signOut();
                    startActivity(switchIntent);
                    finish();
                    return true;
                }else if(item.getItemId()==R.id.home_screen){
                    startActivity(new Intent(Workout_Selection.this,MainActivity2.class));
                    return true;

                }
                else if(item.getItemId()==R.id.Design_screen){
                    startActivity(new Intent(Workout_Selection.this, DietActivity.class));
                    return true;
                }
                else if(item.getItemId()==R.id.Workroutine_screen){
                    startActivity(new Intent(Workout_Selection.this,Workout_Selection.class));
                    return true;
                }
                else if(item.getItemId()==R.id.ency_screen){
                    startActivity(new Intent(Workout_Selection.this, WorkoutListActivity.class));
                    return true;
                }
                return false;
            }
        });
        popup.inflate(R.menu.homepage_dropdown);
        popup.show();
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