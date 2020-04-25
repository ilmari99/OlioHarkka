package com.example.olioht;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DayScreen extends MainActivity {

/*
In this interface we create a new DayClass or display editable attributes from a day that was already filled.
In this screen the user can fill or edit information about a particular day. If there is no data about the day, then the EditText -fields will be blank, but if there was data about the day,
the data will be displayed on the EditText fields and the user can edit the information easily.
 The data is ONLY saved to a file after the user presses the "Save" -button. When overwriting, there will pop-up a "Warning" -fragment.

 There will only be one instance of DayClass at a time. Others will be stored on the file.

 In this interface, there will be a list of DoneActivities, where the user can select added activities or select "Add".
 After choosing an activity or Add, the user can press "Go to activity" which will open a new Interface "ActivityScreen".
*/

    // TODO No new DayCLass-object is created, if info for selected day already exists; information is read from file instead

    // Declaring variables for different UI components and values
    private int sleepTime, socialTime, dayRating;
    private Boolean experience, exercise, people;
    private String date;
    private SeekBar sleepTimeSlider, socialTimeSlider, rateDaySlider;
    private TextView dayRatingText, socialTimeText, sleepTimeText;
    private CheckBox exerciseBox, newExperienceBox, newPeopleBox;
    private static DayClass day;



    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayscreen);

        TextView selectedDate = findViewById(R.id.selectedDate);
        date = MainActivity.getDate();
        selectedDate.setText(date);

        // Changing text boxes for sliders
        dayRatingText = findViewById(R.id.dayRating);
        socialTimeText = findViewById(R.id.socialTime);
        sleepTimeText = findViewById(R.id.sleepTime);

        // The sliders
        socialTimeSlider = findViewById(R.id.socialSeekBar);
        sleepTimeSlider = findViewById(R.id.sleepSeekBar);
        rateDaySlider = findViewById(R.id.rateSeekBar);

        // Checkboxes for boolean values
        exerciseBox = findViewById(R.id.exerciseBox);
        newExperienceBox = findViewById(R.id.newexperienceBox);
        newPeopleBox = findViewById(R.id.newexperienceBox);

        // Listeners for changing texts
        socialTimeSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                socialTimeText.setText(progress + " hours");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));

        sleepTimeSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sleepTimeText.setText(progress + " hours");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));

        rateDaySlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dayRatingText.setText(progress + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));
    }


    public void goBack (View v) {
        //TODO Warning-screen if going back without saving
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
    }


    // Creating DayClass object for chosen day with all the info asked in DayScreen
   public void saveDayData (View v) {
        socialTime = socialTimeSlider.getProgress();
        sleepTime = sleepTimeSlider.getProgress();
        dayRating = rateDaySlider.getProgress();
        experience = getExperienceBool();
        people = getNewPeopleBool();
        exercise = getExerciseBool();

        day = new DayClass(date, sleepTime, socialTime, dayRating, experience, people, exercise);

        Intent activityScreenIntent = new Intent(this, ActivityScreen.class);
        startActivity(activityScreenIntent);
    }

    // Boolean value getters for checkboxes
    protected boolean getExperienceBool(){ return newExperienceBox.isChecked(); }

    protected boolean getExerciseBool(){ return exerciseBox.isChecked(); }

    protected boolean getNewPeopleBool(){return newPeopleBox.isChecked(); }

    public static DayClass getDayObject() { return day; }
}
