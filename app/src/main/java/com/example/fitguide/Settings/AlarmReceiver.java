package com.example.fitguide.Settings;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fitguide.R;
import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.example.fitguide.Workout_Classes.WorkoutRoutineList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

/*
 * This broadcast receiver listens to when an alarm goes off. If it
 * does, then it creates a notification and notify the user with it.
 */
public class AlarmReceiver extends BroadcastReceiver {

    String content;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Creates notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "DailyNotification");
        builder.setContentTitle("FitGuide: Daily Notification");

        int dayOfTheWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        notificationContent(dayOfTheWeek);
        builder.setContentText(content);

        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        // Creates notification manager
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context. NOTIFICATION_SERVICE ) ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("DailyNotification", "DailyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel) ;
        }
        notificationManager.notify(1, builder.build());
    }

    /*
     * Dynamically create the notification text depending on what workout routine the user selected
     * and the current day of the week.
     */
    private void notificationContent(int dayOfTheWeek){
        FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore  = FirebaseFirestore.getInstance();

        DocumentReference personalInfo = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Personal_info");
        DocumentReference workoutRoutines = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document("Workout_Routines");

        personalInfo.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String firstName = (String) documentSnapshot.get("First Name");
                String lastName = (String) documentSnapshot.get("Last Name");

                content += "Hello " + firstName + " " + lastName + "!\n";
            }
        });

        workoutRoutines.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WorkoutRoutineList routineList = documentSnapshot.toObject(WorkoutRoutineList.class);
                WorkoutRoutine selected = routineList.getSelected();
                if (selected == null){
                    content += "You have not selected a workout routine to use for your progress!\n";
                    content += "Please select a workout you want to use!\n";
                } else {
                    String muscleGroup = selected.getMuscleGroup(dayOfTheWeek);
                    content += "Its time to work towards your Fitness Goal!\n";
                    content += "Today is a " + muscleGroup + " day!\n";
                    content += "Go onto FitGuide to see what you need to do!";
                }
            }
        });
    }
}
