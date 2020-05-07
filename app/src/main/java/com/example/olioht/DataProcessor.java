package com.example.olioht;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataProcessor {

    /* Handles file writing and reading as well as analyzing the data. Is public singleton. */

    public static DataProcessor dataProcessor;
    Gson gson = new Gson();

    public  DataProcessor() {
        if (dataProcessor == null) {
        }
    }


    // Method for checking if data for selected day already exists. Data is stored in SharedPrefs
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


    public static DataProcessor getInstance() {
        if (dataProcessor == null) {
            dataProcessor = new DataProcessor();
        }
        return dataProcessor;
    }
}
