package com.example.fitguide.Settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

/*
 * This broadcast receiver resets a previous existing alarm if the device boots
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();
            FirebaseFirestore firebaseFirestore  = FirebaseFirestore.getInstance();

            // Reset the alarm if an alarm was in place.
            DocumentReference doc = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Personal_info");
            doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    boolean notifications = documentSnapshot.getBoolean("Notifications On");
                    if (notifications){
                        int setHour = (Integer) documentSnapshot.get("Hour Set");
                        int setMinute = (Integer) documentSnapshot.get("Minute Set");
                        setAlarmSystem(context, setHour, setMinute);
                    }
                }
            });

        }
    }

    /*
     * This function creates a alarm system that creates a notification when
     * the it reaches the time the user sets
     */
    private void setAlarmSystem(Context context, int setHour, int setMinute){

        // Connect Broadcast Receiver to a pending intent object.
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent,PendingIntent.FLAG_IMMUTABLE);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set time to create alarm.
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        calender.set(Calendar.HOUR_OF_DAY, setHour);
        calender.set(Calendar.MINUTE, setMinute);
        calender.set(Calendar.SECOND, 0);

        // Set Alarm to go off at the set time everyday.
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

    }
}
