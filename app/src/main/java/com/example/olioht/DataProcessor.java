package com.example.olioht;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.StrictMath.round;

public class DataProcessor {

    /* Handles file writing and reading as well as analyzing the data. Is public singleton. */

    public static DataProcessor dataProcessor;
    Gson gson = new Gson();

    public  DataProcessor() {
        if (dataProcessor == null) {
        }
    }


    // Method for checking if data for selected day already exists in SharedPreferences
    public DayClass checkExistingData(Context context, String date) {
        SharedPreferences sh = context.getSharedPreferences("dayData", context.MODE_PRIVATE);
        String dayJSON = sh.getString(date, "");

        // If no existing data is found, returns null. Else proceeds to construct a DayClass object
        if (dayJSON == "" || dayJSON == "null") {
            return null;
        }
        else {
            DayClass day = gson.fromJson(dayJSON, DayClass.class);
            day.doneActivities.clear();     // Clearing array to be sure it's empty before filling it with done activities
            try {
                // Getting done activities from dayJSON and making an array of them
                JSONObject jsonObject = new JSONObject(dayJSON);
                JSONArray acts = jsonObject.getJSONArray("doneActivities");

                // Adding activities from JSON to day.doneActivities array
                for (int i = 0; i < acts.length(); i++) {
                    JSONObject actObj = acts.getJSONObject(i);      // Getting one activity from the array
                    String activityName = actObj.getString("activityName");
                    Class cls = Class.forName("com.example.olioht." + activityName);    // Making a class name from an activity's name
                    day.doneActivities.add((ActivityClass) gson.fromJson(acts.getString(i), cls));
                }
            }
            catch (JSONException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return day;
        }
    }


    // Method for saving day object to SharedPreferences XML as and JSON-formatted string.
    public void saveDayToFile(Context context, String date, DayClass day) {
        String dayJSON = gson.toJson(day);    // Transforming day object to JSON formatted text

        // Storing data in SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("dayData", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(date, dayJSON);
        editor.commit();

        final Toast toast = Toast.makeText(context,"Day saved to a file!", Toast.LENGTH_LONG);
        toast.show();
    }


    // Method for analysing all day data. Returns string[] with analysed data inside.
    public String[] analyseAllData(Context context) {
        DayClass day;
        DecimalFormat f = new DecimalFormat("##.00");
        String[] analyzedData = new String[8];
        double avgRating = 0, avgSocial = 0, avgSleep = 0;
        int daysTotal = 0, expDays = 0, pplDays = 0, exerciseDays = 0;

        // Getting SharedPreferences XML
        SharedPreferences sh = context.getSharedPreferences("dayData", Context.MODE_PRIVATE);
        Map<String, ?> dayData = sh.getAll();   // Getting all rows of information from XML
        Set dateKeys = dayData.keySet();    // Key value of every row into a set
        List<String> dateList = new ArrayList<>();
        dateList.addAll(dateKeys);      // All key values into an ArrayList
        
        String firstDay = Collections.min(dateList);    // Date from first logged day

        // Going through every row of info, based on the key. Calculating averages and sums from the data.
        for (String key : dateList) {
            String dayJSON = sh.getString( key, "");
            day = gson.fromJson(dayJSON, DayClass.class);

            avgRating += day.getDayRating();
            avgSocial += day.getSocialTime();
            avgSleep += day.getSleepTime();
            expDays += day.getNewExperience() ? 1 : 0;
            pplDays += day.getNewPeople() ? 1 : 0;
            exerciseDays += day.getExercise() ? 1 : 0;
            daysTotal++;
        }

        // Adding analysed data to array
        analyzedData[0] = firstDay;
        analyzedData[1] = daysTotal + " days";
        analyzedData[2] = f.format(avgRating / daysTotal);
        analyzedData[3] = f.format(avgSocial / daysTotal) + " h";
        analyzedData[4] = f.format(avgSleep / daysTotal) + " h";
        analyzedData[5] = expDays + " days";
        analyzedData[6] = pplDays + " days";
        analyzedData[7] = exerciseDays + " days";

        return analyzedData;
    }


    // Method for analyzing user chosen day. Returns string[] with analysed data inside.
    public String[] analyzeChosen(Context context, String date) {
        DecimalFormat f = new DecimalFormat("##.00");
        StringBuilder builder = new StringBuilder();
        DayClass day;
        final String[] analyzedData = new String[8];

        // Getting SharedPreferences XML
        SharedPreferences sh = context.getSharedPreferences("dayData", Context.MODE_PRIVATE);
        String dayJSON = sh.getString(date, "");

        // If no data available for selected date, error message is shown and null list is returned
        if (dayJSON == "") {
            return null;
        }

        day = gson.fromJson(dayJSON, DayClass.class);

        day.doneActivities.clear();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(dayJSON);
            JSONArray acts = jsonObject.getJSONArray("doneActivities");
            for (int i = 0; i < acts.length(); i++) {
                JSONObject actObj = acts.getJSONObject(i);
                String activityName = actObj.getString("activityName");
                builder.append(activityName.toLowerCase() + ", ");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        // Adding data to string[]
        analyzedData[0] = date;
        analyzedData[1] = Integer.toString(day.getDayRating());
        analyzedData[2] = Integer.toString(day.getSleepTime());
        analyzedData[3] = Integer.toString(day.getSocialTime());
        analyzedData[4] = day.getNewExperience() ? "Yes" : "No";
        analyzedData[5] = day.getNewPeople() ? "Yes" : "No";
        analyzedData[6] = day.getExercise() ? "Yes" : "No";
        analyzedData[7] = builder.toString();

        return analyzedData;
    }


    // For making this class singleton
    public static DataProcessor getInstance() {
        if (dataProcessor == null) {
            dataProcessor = new DataProcessor();
        }
        return dataProcessor;
    }
}
