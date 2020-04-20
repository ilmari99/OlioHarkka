package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScreen extends DayScreen {

    /*
    In this interface user can add different activities to selected date. Activities are saved as objects into an array.
    After selecting activity type, a corresponding fragment will pop-up. The fragment should be an extension and time and rating should still be editable.

    If the user is editing an activity then the information for that day is shown when activity is selected.
    The attributes will be editable similar to DayClass. Except, if the user tries to edit the activity type from the dropdown, there will pop-up a Warning -fragment, similar to DayClass.

    Also similar to DayClass, the data will only be saved by pressing the "Save" -button.
     */

    // Declaring variables for different UI components and values
    private ActivityClass activity = null;
    private Spinner activitySpinner;
    private Fragment ratingFrag, drinkingFrag, exerciseFrag, friendsFrag, relationFrag, studyFrag;
    private String choice, subject;
    private TextView infoTextBox; // activityRatingText, activityTimeText;
    // private SeekBar activityRatingSlider, activityTimeSlider;
    private Boolean withFriends;
    private FrameLayout activityFrame, ratingFrame;
    private int activityRating, activityTime;
    private String date = MainActivity.getDate();
    private DayClass day = DayScreen.getDayObject();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);

        // Finding UI components
        activitySpinner = findViewById(R.id.activityDropdown);

        /*activityRatingSlider = findViewById(R.id.activityRatingSeekBar);
        activityTimeSlider = findViewById(R.id.activityTimeSeekBar);*/

        infoTextBox = findViewById(R.id.activityScreenInfoMessage);
       /* activityRatingText = findViewById(R.id.activityRatingChanging);
        activityTimeText = findViewById(R.id.activityTimeChanging);*/

        TextView selectedDate = findViewById(R.id.selectedDate);
        selectedDate.setText("Date: " + date);

        // Getting the activity and corresponding questions for user
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choice = String.valueOf(activitySpinner.getSelectedItem());
               /* activityFrame = findViewById(R.id.activityFrame);
                ratingFrame = findViewById(R.id.ratingFrame);*/

                // Setting up usage of fragments
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction drinkingFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction exerciseFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction friendsFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction relationFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction studyFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction ratingFragmentTransaction = fragmentManager.beginTransaction();

                ratingFrag = new generalInfo();
                ratingFragmentTransaction.replace(R.id.ratingFrame, ratingFrag);
                ratingFragmentTransaction.commit();

                // TODO When creating new activity object, give it right parameters (eg. activity = new Studying(SUBJECT, TIME))
                switch (choice) {
                    case ("Studying"):
                        studyFrag = new StudyFragment();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        studyFragmentTransaction.replace(R.id.activityFrame, studyFrag);
                        studyFragmentTransaction.commit();

                        // TODO Find a way to call methods from fragment
                        /*String subject = StudyFragment.getSubject;
                        int rating = StudyFragment.getRating;
                        int time = StudyFragment.getTime;
                        Boolean friends = studyFrag.getF*/

                        //activity = new Studying(subject, rating, time, friends);
                        break;
                    case ("Exercise"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        exerciseFrag = new ExerciseFragment();
                        exerciseFragmentTransaction.replace(R.id.activityFrame, exerciseFrag);
                        exerciseFragmentTransaction.commit();

                        activity = new Exercise();
                        break;
                    case ("Drinking"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        drinkingFrag = new DrinkingFragment();
                        drinkingFragmentTransaction.replace(R.id.activityFrame, drinkingFrag);
                        drinkingFragmentTransaction.commit();

                        activity = new Drinking();
                        break;
                    case ("Friends"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        friendsFrag = new FriendsFragment();
                        friendsFragmentTransaction.replace(R.id.activityFrame, friendsFrag);
                        friendsFragmentTransaction.commit();

                        activity = new Friends();
                        break;
                    case ("Relationship"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        relationFrag = new RelationshipFragment();
                        relationFragmentTransaction.replace(R.id.activityFrame, relationFrag);
                        relationFragmentTransaction.commit();

                        activity = new Relationship();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Intent getDataIntent = getIntent();
        withFriends = getDataIntent.getBooleanExtra("withFriends", false);
        subject = getDataIntent.getStringExtra("subject");
    }
        /*// Listeners for changing texts
        activityRatingSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 6) {
                    activityRatingText.setText(progress + "/5");
                }
                else {
                    activityRatingText.setText(progress + "");
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));

        activityTimeSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                activityTimeText.setText(progress + " hours");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));
    }*/

    public void goBack (View v){
        // TODO Add warning if trying to leave without saving
        Intent goBackIntent = new Intent(this, DayScreen.class);
        startActivity(goBackIntent);
    }

    public void saveChanges(View v) {

    }
}
