package com.example.fitguide;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WorkoutDetailActivityTest {

    @Rule
    public ActivityScenarioRule<WorkoutDetailActivity> activityRule =
            new ActivityScenarioRule<>(WorkoutDetailActivity.class);

    @Test
    public void checkExerciseTitle() {
        onView(withId(R.id.exerciseTitle)).check(matches(withText("Bench Press")));
    }

    @Test
    public void checkDifficultyValue() {
        onView(withId(R.id.difficultyValue)).check(matches(withText("Medium")));
    }

    @Test
    public void checkEquipmentValue() {
        onView(withId(R.id.equipmentValue)).check(matches(withText("Barbell, Weights")));
    }

    @Test
    public void checkBodyCoverageValue() {
        onView(withId(R.id.bodyCoverageValue))
                .check(matches(withText("Chest Area: Pectoralis major and Pectoralis minor\nShoulder Area: Anterior deltoid\nArm Area: Triceps brachii")));
    }
}
