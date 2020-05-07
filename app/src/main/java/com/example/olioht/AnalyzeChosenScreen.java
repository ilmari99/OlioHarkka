package com.example.olioht;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AnalyzeChosenScreen extends AppCompatActivity {

    private String date = MainActivity.getDate();
    private DayClass day;
    private TextView dateText, dayRating, sleep, socialTime, activityText, exp, ppl, exerc;
    private ArrayList<String> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_chosen_screen);
        activities = new ArrayList<>();

        dateText = findViewById(R.id.dateText);
        dayRating = findViewById(R.id.dayRating);
        sleep = findViewById(R.id.sleep);
        socialTime = findViewById(R.id.socialTime);
        activityText = findViewById(R.id.activityText);
        exp = findViewById(R.id.exp);
        ppl = findViewById(R.id.ppl);
        exerc = findViewById(R.id.exerc);

        Gson gson = new Gson();

        SharedPreferences sh = this.getSharedPreferences("dayData", Context.MODE_PRIVATE);
        String dayJSON = sh.getString(date, "");

        System.out.println("#####" + dayJSON + "#######");

        if (dayJSON == "") {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No data available!");
            alertDialogBuilder.setMessage("No data available for selected day. Please select other day.");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
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
                activities.add(activityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dateText.setText(date);
        dayRating.setText(String.valueOf(day.getDayRating()));
        sleep.setText(String.valueOf(day.getSleepTime()));
        socialTime.setText(String.valueOf(day.getSocialTime()));
        exp.setText(day.getNewExperience() ? "Yes" : "No");
        ppl.setText(day.getNewPeople() ? "Yes" : "No");
        exerc.setText(day.getExercise() ? "Yes" : "No");
        activityText.setText("Done activities: " + Arrays.toString(activities.toArray()));
    }
}