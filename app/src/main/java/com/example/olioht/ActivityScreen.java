package com.example.olioht;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ActivityScreen  extends DayScreen {

    /*
    In this interface user can add different activities to selected date. Activities are saved as objects into an array.
    After selecting activity type, a corresponding fragment will pop-up. The fragment should be an extension and time and rating should still be editable.

    If the user is editing an activity then the information for that day is shown when activity is selected.
    The attributes will be editable similar to DayClass. Except, if the user tries to edit the activity type from the dropdown, there will pop-up a Warning -fragment, similar to DayClass.

    Also similar to DayClass, the data will only be saved by pressing the "Save" -button.
     */

    // Declaring variables for UI components and values
    private ActivityClass activity = null;
    private Spinner activitySpinner;
    private Fragment drinkingFrag, exerciseFrag, friendsFrag, relationFrag, studyFrag;
    private String choice;
    private TextView infoTextBox, activityRatingText, activityTimeText;
    private SeekBar activityRatingSlider, activityTimeSlider;
    private int activityRating, activityTime;
    private Bundle dataBundle;
    private String date = MainActivity.getDate();


    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);

        dataBundle = new Bundle();

        // Finding UI components
        activitySpinner = findViewById(R.id.activityDropdown);

        activityRatingSlider = findViewById(R.id.activityRatingSeekBar);
        activityTimeSlider = findViewById(R.id.activityTimeSeekBar);

        infoTextBox = findViewById(R.id.activityScreenInfoMessage);
        activityRatingText = findViewById(R.id.activityRatingChanging);
        activityTimeText = findViewById(R.id.activityTimeChanging);

        TextView selectedDate = findViewById(R.id.selectedDate);
        selectedDate.setText(date);

        // Getting the activity and corresponding questions for user
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choice = String.valueOf(activitySpinner.getSelectedItem());

                // Setting up usage of fragments
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction drinkingFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction exerciseFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction friendsFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction relationFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction studyFragmentTransaction = fragmentManager.beginTransaction();

                switch (choice) {
                    case ("Studying"):
                        studyFrag = new StudyFragment();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        studyFragmentTransaction.replace(R.id.activityFrame, studyFrag);
                        studyFragmentTransaction.commit();
                        break;
                    case ("Exercise"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        exerciseFrag = new ExerciseFragment();
                        exerciseFragmentTransaction.replace(R.id.activityFrame, exerciseFrag);
                        exerciseFragmentTransaction.commit();
                        break;
                    case ("Drinking"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        drinkingFrag = new DrinkingFragment();
                        drinkingFragmentTransaction.replace(R.id.activityFrame, drinkingFrag);
                        drinkingFragmentTransaction.commit();
                        break;
                    case ("Friends"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        friendsFrag = new FriendsFragment();
                        friendsFragmentTransaction.replace(R.id.activityFrame, friendsFrag);
                        friendsFragmentTransaction.commit();
                        break;
                    case ("Relationship"):
                        infoTextBox.setVisibility(View.INVISIBLE);
                        relationFrag = new RelationshipFragment();
                        relationFragmentTransaction.replace(R.id.activityFrame, relationFrag);
                        relationFragmentTransaction.commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Listeners for changing texts
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
    }

    public void goBack (View v){
        /*
        //TODO lisätään tähän varoitus -fragmentti, että jos poistut tallentamatta menetät muutokset
        Intent goBackIntent = new Intent(this, DayScreen.class);
        startActivity(goBackIntent);

         */
        finish();
    }

    public void saveChanges(View v) {
        // TODO Implement the whole method
    }

    //Sends Activity rating and time to the chosen fragment
    public Bundle sendDataToFragment() {
        activityRating = activityRatingSlider.getProgress();
        activityTime = activityTimeSlider.getProgress();
        dataBundle.putInt("rating", activityRating);
        dataBundle.putInt("time", activityTime);
        return dataBundle;
    }

}
