package com.example.olioht;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;


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

    // Declaring variables for different UI components and values
    private Boolean experience, exercise, people;
    private Boolean oldDataExists = false;
    private String date;
    private SeekBar sleepTimeSlider, socialTimeSlider, rateDaySlider;
    private TextView dayRatingText, socialTimeText, sleepTimeText;
    private CheckBox exerciseBox, newExperienceBox, newPeopleBox;
    private static DayClass day;
    private int sleepTime, socialTime, dayRating, actNumber;
    ArrayList<ActivityClass> empty = new ArrayList<>();

    @SuppressLint("CutPasteId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayscreen);

        TextView selectedDate = findViewById(R.id.selectedDate);
        date = MainActivity.getDate();
        selectedDate.setText(date);

        // Changing text boxes for sliders
        dayRatingText = findViewById(R.id.rating);
        socialTimeText = findViewById(R.id.socialTime);
        sleepTimeText = findViewById(R.id.sleepTime);

        // The sliders
        socialTimeSlider = findViewById(R.id.socialSeekBar);
        sleepTimeSlider = findViewById(R.id.sleepSeekBar);
        rateDaySlider = findViewById(R.id.rateSeekBar);

        // Checkboxes for boolean values
        exerciseBox = findViewById(R.id.exerciseBox);
        newExperienceBox = findViewById(R.id.newexperienceBox);
        newPeopleBox = findViewById(R.id.newpeopleBox);

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

        // Getting existing data if possible
        if (day == null) {
            actNumber = 0;
            day = dataProcessor.checkExistingData(this, date);
            if (day != null) {

                oldDataExists = true;
                actNumber = day.doneActivities.size();

                //Setting up alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Old data found!");
                alertDialogBuilder.setMessage("You have already filled this day's information, showing it instead. You can also make changes to it.");
                alertDialogBuilder.setPositiveButton("OK", null);
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

                // Changing values to those found in SharedPreferences
                socialTimeSlider.setProgress(day.getSocialTime());
                sleepTimeSlider.setProgress(day.getSleepTime());
                rateDaySlider.setProgress(day.getDayRating());
                exerciseBox.setChecked(day.getExercise());
                newExperienceBox.setChecked(day.getNewExperience());
                newPeopleBox.setChecked(day.getNewPeople());
            }
        }

        System.out.println("OnCreate suoritettu");
    }

    //Go back to calendar and show warning message if needed.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void goBack (View v) {
        day = ActivityScreen.getDayObject();
        if (day == null) {
            finish();
        }
        else if (day.doneActivities == null || day.doneActivities.isEmpty()) {
            finish();
        }
        else if (oldDataExists && noNewData(day)) {
            finish();
        }
        else {  // Showing warning dialogue if about to leave without saving
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Save your data!");
            alertDialogBuilder.setMessage("You haven't saved your activity data. Are you sure you don't want save it? You can do it by pressing \"Save the day\" button below.");
            alertDialogBuilder.setPositiveButton("Yes, I want to lose my data", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialogBuilder.setNegativeButton("No, let's go save it", null);
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
        }
    }

    /* Creating DayClass object for chosen day with all the info asked in DayScreen, then continuing
    to fill information about different activities */
   public void saveDayData (View v) {
        socialTime = socialTimeSlider.getProgress();
        sleepTime = sleepTimeSlider.getProgress();
        dayRating = rateDaySlider.getProgress();
        experience = getExperienceBool();
        people = getNewPeopleBool();
        exercise = getExerciseBool();
        if(day == null) {
            day = new DayClass(date, sleepTime, socialTime, dayRating, experience, people, exercise, empty);
        }

        Intent activityScreenIntent = new Intent(this, ActivityScreen.class);
        startActivity(activityScreenIntent);

        System.out.println("Suoritettu saveDayData");
    }

    // Saving the Day, including all the activities, to a SharedPreferences XML file in JSON format
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveToFile(View v) {

        if (day == null) {
            socialTime = socialTimeSlider.getProgress();
            sleepTime = sleepTimeSlider.getProgress();
            dayRating = rateDaySlider.getProgress();
            experience = getExperienceBool();
            people = getNewPeopleBool();
            exercise = getExerciseBool();
            day = new DayClass(date, sleepTime, socialTime, dayRating, experience, people, exercise, empty);
        }
        else {
            day = ActivityScreen.getDayObject();
        }
        dataProcessor.saveDayToFile(this, date, day);
        finish();
    }

    // Boolean value getters for checkboxes
    protected boolean getExperienceBool(){ return newExperienceBox.isChecked(); }

    protected boolean getExerciseBool(){ return exerciseBox.isChecked(); }

    protected boolean getNewPeopleBool(){return newPeopleBox.isChecked(); }

    // Getter for other classes in need of day-object
    public static DayClass getDayObject() {
        System.out.println(day);
        return day; }

    // Resetting day-object when old data is not needed
    public static void resetDay(DayClass act){
        day = act;
    }

    //Check whether there are added activities
    public boolean noNewData(DayClass day) {
        int newActNumber = day.doneActivities.size();
        if (actNumber == 0) {
            return true;
        }
        else return actNumber >= newActNumber;
    }
}


