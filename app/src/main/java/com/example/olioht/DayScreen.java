package com.example.olioht;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private Boolean dataExists = false, isActivityData = false;
    private String date;
    private SeekBar sleepTimeSlider, socialTimeSlider, rateDaySlider;
    private TextView dayRatingText, socialTimeText, sleepTimeText;
    private CheckBox exerciseBox, newExperienceBox, newPeopleBox;
    private static DayClass day;
    private int sleepTime, socialTime, dayRating, actNumber;
    private Gson gson = new Gson();
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

        if (day == null) {
            try {
                day = checkExistingData(this, date);
            } catch (JSONException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (day != null) {
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
        actNumber = 0;

        System.out.println("OnCreate suoritettu");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void goBack (View v) {
        day = ActivityScreen.getDayObject();
        if (day == null) {
            finish();
        }
        else if (day.doneActivities == null || day.doneActivities.isEmpty()) {
            finish();
        }
        else if (!day.doneActivities.isEmpty() && dataExists == true && noNewData(day)) {
            finish();
        }
        else {
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

        isActivityData = true;
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

       String dayJSON = gson.toJson(day);    // Data to save

        SharedPreferences sharedPreferences = getSharedPreferences("dayData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(date, dayJSON);
        editor.commit();
        final Toast toast = Toast.makeText(this,"Day saved to a file!", Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    // Check if data for selected day exists already
    public DayClass checkExistingData(Context context, String date) throws JSONException, ClassNotFoundException {
        SharedPreferences sh = context.getSharedPreferences("dayData", Context.MODE_PRIVATE);
        String dayJSON = sh.getString(date, "");
        System.out.println("#####" + dayJSON + "#######");


        if (dayJSON == "" || dayJSON == "null") {
            return null;
        }
        else {
            day = gson.fromJson(dayJSON, DayClass.class);
            dataExists = true;
            day.doneActivities.clear();
            JSONObject jsonObject = new JSONObject(dayJSON);
            JSONArray acts = jsonObject.getJSONArray("doneActivities");

            System.out.print(acts.length());
            System.out.println(acts);

            for (int i = 0; i < acts.length(); i++) {
                JSONObject actObj = acts.getJSONObject(i);
                String activityName = actObj.getString("activityName");
                System.out.println(actObj);
                Class cls = Class.forName("com.example.olioht." + activityName);
                day.doneActivities.add((ActivityClass) gson.fromJson(acts.getString(i), cls));
            }
            return day;
        }
    }

    // Boolean value getters for checkboxes
    protected boolean getExperienceBool(){ return newExperienceBox.isChecked(); }

    protected boolean getExerciseBool(){ return exerciseBox.isChecked(); }

    protected boolean getNewPeopleBool(){return newPeopleBox.isChecked(); }

    // Getter for other classes in need of day-object
    public static DayClass getDayObject() { return day; }

    // Resetting day-object when old data is not needed
    public static void resetDay(DayClass act){
        day = act;
    }

    public Boolean dataExistsBoolean() {
        return dataExists;
    }

    public boolean noNewData(DayClass day) {
        int newActNumber = day.doneActivities.size();
        if (actNumber == 0) {
            return true;
        }
        else if (actNumber < newActNumber) {
            return false;
        }
        return true;
    }
}


