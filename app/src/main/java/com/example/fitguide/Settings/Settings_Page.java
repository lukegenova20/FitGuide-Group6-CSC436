package com.example.fitguide.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitguide.MainActivity2;
import com.example.fitguide.MainActivityLogin;
import com.example.fitguide.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;


public class Settings_Page extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    LinearLayout mainLayout;

    // Did the user change their settings?
    boolean saveChanges;

    // Used to load recent activity.
    boolean savedNotifications;

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
        savedNotifications = false;

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
        text.setTextSize(30);
        text.setTextColor(getResources().getColor(R.color.black, getTheme()));

        Typeface font = Typeface.createFromAsset(getAssets(), "font/jockey_one.ttf");
        text.setTypeface(font);
        mainLayout.addView(text);
    }

    /*
     * This function displays the edit text and button to get a time programmatically.
     */
    private void createTimeChange(boolean madeChanges){
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

        text.setClickable(false);
        text.setFocusable(false);
        text.setEnabled(false);


        // Show recent changes in edit text view
        if (madeChanges){
            text.setText(setHour + ":" + setMinute);
        }

        mainLayout.addView(text);

        Button button = new Button(getApplicationContext());
        button.setLayoutParams(params);

        button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        button.setText("Set Time");
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
                                if (sMinute < 10){
                                    text.setText(sHour + ":0" + sMinute);
                                } else {
                                    text.setText(sHour + ":" + sMinute);
                                }
                            }
                        }, 12, 0, true);
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

        // Set changes user already made.
        DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Personal_info");
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                boolean notifications = documentSnapshot.getBoolean("Notifications On");
                if (notifications){
                    saveChanges = true;
                    savedNotifications = true;
                    setHour = (Integer) documentSnapshot.get("Hour Set");
                    setMinute = (Integer) documentSnapshot.get("Minute Set");
                    checkBox.setChecked(true);
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveChanges = isChecked;
                if (saveChanges) {
                    createText();
                    createTimeChange(savedNotifications);
                } else {
                    mainLayout.removeAllViews();
                }
            }
        });
    }

    /*
     * This function creates a alarm system that creates a notification when
     * the it reaches the time the user sets
     */
    private void setAlarmSystem(){

        // Connect Broadcast Receiver to a pending intent object.
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent,PendingIntent.FLAG_IMMUTABLE);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (saveChanges){
            // Set time to create alarm.
            Calendar calender = Calendar.getInstance();
            calender.setTimeInMillis(System.currentTimeMillis());
            calender.set(Calendar.HOUR_OF_DAY, setHour);
            calender.set(Calendar.MINUTE, setMinute);
            calender.set(Calendar.SECOND, 0);

            // Set Alarm to go off at the set time everyday.
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {

            // Cancels the alarm
            manager.cancel(pendingIntent);
        }

    }

    /*
     * Adds event listener to button to save the changes the user made.
     */
    private void saveChanges(){
        Button saveButton = findViewById(R.id.save_changes);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Personal_info");
                if (saveChanges){
                    doc.update("Notifications On", true);
                    doc.update("Hour Set", setHour);
                    doc.update("Minute Set", setMinute);
                } else {
                    doc.update("Notifications On", false);
                    doc.update("Hour Set", 0);
                    doc.update("Minute Set", 0);
                }
                setAlarmSystem();
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
        ImageButton home = findViewById(R.id.Home);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
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

    private void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Let workout creator know what kind of workout style the user wants for their routine.
                if (item.getItemId() == R.id.sign_out){
                    Intent switchIntent = new Intent(Settings_Page.this, MainActivityLogin.class);
                    firebaseAuth.signOut();
                    startActivity(switchIntent);
                    finish();
                    return true;
                }
                return false;
            }
        });
        popup.inflate(R.menu.user_acc_drop_down);
        popup.show();
    }
}