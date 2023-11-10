package com.example.fitguide;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.example.fitguide.Workout_Creation.Workout_Creation;
import com.example.fitguide.Workout_Creation.Workout_Selection;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class MainActivity2Test {
    @Rule
    public IntentsTestRule<MainActivity2> activityTestRule = new IntentsTestRule<>(MainActivity2.class, true, false); // Don't launch the activity immediately

    @Before
    public void setUp() throws InterruptedException {
        // Sign in with a test account before running the tests
        final CountDownLatch latch = new CountDownLatch(1);
        // DO NOT SHARE MY PASSWORD WITH ANYONE ELSE LOL
        FirebaseAuth.getInstance().signInWithEmailAndPassword("testing26@gmail.com", "testing26")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        latch.countDown();
                    } else {
                        throw new AssertionError("Could not sign in test user", task.getException());
                    }
                });

        latch.await(); // Wait for sign in to complete
        // Now we launch the activity
        activityTestRule.launchActivity(null);
    }

    @Test
    public void testWelcomeTextVisibility() {
        // Check if the welcome text is displayed
        onView(withId(R.id.welcome_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigateToWorkoutList() {
        // Click on the workout encyclopedia button
        onView(withId(R.id.workout_encyclopedia)).perform(click());
        // Check if the WorkoutListActivity was opened
        intended(hasComponent(WorkoutListActivity.class.getName()));
    }

    @Test
    public void testNavigateToDietActivity() {
        // Click on the design diet button
        onView(withId(R.id.design_diet)).perform(click());
        // Check if the DietActivity was opened
        intended(hasComponent(DietActivity.class.getName()));
    }

    @Test
    public void testNavigateToWorkoutSelection() {
        // Click on the select workout button
        onView(withId(R.id.select_workout)).perform(click());
        // Check if the Workout_Selection activity was opened
        intended(hasComponent(Workout_Selection.class.getName()));
    }

    @Test
    public void testDropdownClick() {
        // Click on the dropdown button
        onView(withId(R.id.Dropdown)).perform(click());
    }

    @Test
    public void testSettingsClick() {
        // Click on the settings button
        onView(withId(R.id.Settings)).perform(click());
        // Check if the DummyPage activity was opened
        intended(hasComponent(DummyPage.class.getName()));
    }
}
