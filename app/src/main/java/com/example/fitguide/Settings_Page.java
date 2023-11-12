package com.example.fitguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Settings_Page extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    LinearLayout mainLayout;

    boolean saveChanges;

    int setHour;
    int setMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        firebaseAuth  =FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();
        mainLayout = findViewById(R.id.notifications_time);
        saveChanges = false;

        addCheckBoxEvent();

        saveChanges();

        addHeaderListeners();
    }

    /*
     * This function displays the text for the time picker programmatically.
     */
    private void createText(){
        TextView text = new TextView(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int margin = Math.round( (float) 20 * density);
        params.setMargins(margin, margin, margin, margin);
        text.setLayoutParams(params);

        text.setText(R.string.Notification_Time);
        text.setTextSize(40);
        text.setTextColor(getResources().getColor(R.color.black, getTheme()));

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        text.setTypeface(font);
        mainLayout.addView(text);
    }

    /*
     * This function displays the edit text and button to get a time programmatically.
     */
    private void createTimeChange(){
        EditText text = new EditText(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Converts dp units to pixels.
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int margin = Math.round( (float) 20 * density);
        params.setMargins(margin, margin, margin, margin);
        text.setLayoutParams(params);

        text.setEms(10);

        text.setHint("Enter Time");
        text.setGravity(Gravity.CENTER_HORIZONTAL);

        mainLayout.addView(text);

        Button button = new Button(getApplicationContext());
        button.setLayoutParams(params);

        button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        button.setText("Get Date");
        button.setTextSize(20);
        button.setBackgroundTintMode(null);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        button.setTypeface(font);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Time Picker Dialog
                TimePickerDialog picker = new TimePickerDialog(Settings_Page.this,
                        R.style.DatePickerTheme,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                setHour = sHour;
                                setMinute = sMinute;
                                text.setText(sHour + ":" + sMinute);
                            }
                        }, 12, 12, true);
                picker.show();
            }
        });

        mainLayout.addView(button);
    }

    /*
     * Add event listener to checkbox when the user wants to turn on notifications.
     */
    private void addCheckBoxEvent(){
        CheckBox checkBox = findViewById(R.id.checkBox_settings);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveChanges = isChecked;
                if (saveChanges) {
                    createText();
                    createTimeChange();
                } else {
                    mainLayout.removeAllViews();;
                }
            }
        });
    }

    /*
     * Adds event listener to button to save the changes the user made.
     */
    private void saveChanges(){
        Button saveButton = findViewById(R.id.save_changes);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveChanges){
                    DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Personal_info");
                    doc.update("Notifications On", true);
                    doc.update("Hour Set", setHour);
                    doc.update("Minute Set", setMinute);

                    // TODO: Create notification system
                }
                Intent switchIntent = new Intent(v.getContext(), MainActivity2.class);
                startActivity(switchIntent);

            }
        });
    }

    /*
     * This function adds event listeners to buttons in the header layout.
     */
    private void addHeaderListeners(){

        ImageButton drop = findViewById(R.id.Dropdown);
        ImageButton home = findViewById(R.id.Settings);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Load pop-up menu
                // Disable menu selection for workout routine creation.
                Toast.makeText(getApplicationContext(), "NEED TO WORK ON THIS", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(v.getContext(), MainActivity2.class);
                startActivity(switchIntent);
            }
        });
    }
}