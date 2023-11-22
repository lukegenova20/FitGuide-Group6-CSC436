package com.example.fitguide.Workout_Creation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitguide.DietActivity;
import com.example.fitguide.MainActivity2;
import com.example.fitguide.MainActivityLogin;
import com.example.fitguide.R;
import com.example.fitguide.Settings.Settings_Page;
import com.example.fitguide.WorkoutListActivity;
import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.example.fitguide.Workout_Classes.WorkoutRoutineList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Saved_Workout_Selection extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    LinearLayout mainLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workout_selection);

        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();

        mainLayout = findViewById(R.id.loaded_workout_linear_layout);

        displayOptions();

        addHeaderListeners();
    }

    /*
     * Gets each workout routine saved in user data and displays them in the UI.
     */
    private void displayOptions(){
        DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Workout_Routines");

        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WorkoutRoutineList routineList = documentSnapshot.toObject(WorkoutRoutineList.class);
                for (int i = 0; i < routineList.getRoutineList().size(); i++){
                    createFrameLayout(routineList.getRoutine(i));
                }
            }
        });
    }

    /*
     * This creates a button programmatically
     */
    private Button createButton(WorkoutRoutine routine){

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 100 * density);
        Button newButton = new Button(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                newWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int marginTop = Math.round( (float) 110 * density);
        int marginStart = Math.round( (float) 115 * density);
        params.setMargins(marginStart, marginTop, 0, 0);

        newButton.setLayoutParams(params);
        newButton.setText("Change");
        newButton.setTextSize(20);
        newButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newButton.setBackgroundTintMode(null);
        newButton.setTextColor(getResources().getColor(R.color.black, getTheme()));

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        newButton.setTypeface(font);


        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Switch to the workout creation page with the routine data.
                Intent switchIntent = new Intent(getApplicationContext(), Workout_Creation.class);
                switchIntent.putExtra("loading", routine);
                startActivity(switchIntent);
            }
        });

        return newButton;
    }

    /*
     * This create the textview that displays the workout routine name.
     */
    private TextView createFirstText(WorkoutRoutine routine){
        TextView text = new TextView(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        text.setLayoutParams(params);
        String name = routine.getName();

        text.setText(name);
        text.setTextSize(30);
        text.setTextColor(getResources().getColor(R.color.black, getTheme()));

        text.setGravity(Gravity.CENTER_HORIZONTAL);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        text.setTypeface(font);

        return text;
    }

    /*
     * This creates the textview that displays the workout routine style
     * and if it has been selected.
     */
    private TextView createSecondText(WorkoutRoutine routine){
        TextView text = new TextView(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newHeight = Math.round( (float) 82 * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                newHeight
        );

        int margin = Math.round( (float) 47 * density);
        params.setMargins(0, margin, 0, 0);
        text.setLayoutParams(params);

        int padding = Math.round( (float) 10 * density);
        text.setPadding(padding, 0, 0,0);

        String selected = "No";
        if (routine.getSelected()){
            selected = "Yes";
        }
        text.setText("Style: " + routine.getStyle() + "\nSelected: " + selected);
        text.setTextSize(22);
        text.setTextColor(getResources().getColor(R.color.black, getTheme()));

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        text.setTypeface(font);

        return text;
    }

    /*
     * This creates a line programmatically
     */
    private View createLine(){
        View view = new View(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 325 * density);
        int newHeight = Math.round( (float) 4 * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                newWidth,
                newHeight
        );

        int margin = Math.round( (float) 40 * density);
        params.setMargins(0, margin, 0, 0);
        view.setLayoutParams(params);

        view.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.border_color, getTheme()));

        return view;
    }

    /*
     * Creates a frame layout for the specific workout routine.
     */
    private void createFrameLayout(WorkoutRoutine routine){
        FrameLayout newFrame = new FrameLayout(getApplicationContext());

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int newWidth = Math.round( (float) 330 * density);
        int newHeight = Math.round( (float) 170 * density);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                newWidth,
                newHeight
        );

        int margin = Math.round( (float) 40 * density);
        params.setMargins(0, margin, 0, 0);
        newFrame.setLayoutParams(params);

        newFrame.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        newFrame.setBackgroundTintMode(null);

        // Add Name of Routine
        newFrame.addView(createFirstText(routine));

        // Add Line Divider
        newFrame.addView(createLine());

        // Add Style of Routine and if it has been selected by the user.
        newFrame.addView(createSecondText(routine));

        // Add Button
        newFrame.addView(createButton(routine));

        mainLayout.addView(newFrame);
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
                    Intent switchIntent = new Intent(Saved_Workout_Selection.this, MainActivityLogin.class);
                    switchIntent.putExtra("workout_style", item.getTitle());
                    firebaseAuth.signOut();
                    startActivity(switchIntent);
                    finish();
                    return true;
                }else if(item.getItemId()==R.id.home_screen){
                    startActivity(new Intent(Saved_Workout_Selection.this, MainActivity2.class));
                    return true;

                }
                else if(item.getItemId()==R.id.Design_screen){
                    startActivity(new Intent(Saved_Workout_Selection.this, DietActivity.class));
                    return true;
                }
                else if(item.getItemId()==R.id.Workroutine_screen){
                    startActivity(new Intent(Saved_Workout_Selection.this,Workout_Selection.class));
                    return true;
                }
                else if(item.getItemId()==R.id.ency_screen){
                    startActivity(new Intent(Saved_Workout_Selection.this, WorkoutListActivity.class));
                    return true;
                }
                return false;
            }
        });
        popup.inflate(R.menu.homepage_dropdown);
        popup.show();
    }
}