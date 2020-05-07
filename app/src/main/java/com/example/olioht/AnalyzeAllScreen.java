package com.example.olioht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzeAllScreen extends AppCompatActivity {

    private int avgRating, avgSocial, avgSleep, expDays, pplDays, exerciseDays, daysTotal;
    private String firstDay;
    private DayClass day;
    private TextView avgSocialText, avgRatingTExt, avgSleepText, pplText, expText, exerciseText;
    private TextView firstUsageDay, daysLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_all_screen);

        avgSocialText = findViewById(R.id.avgSocial);
        avgRatingTExt = findViewById(R.id.avgRating);
        avgSleepText = findViewById(R.id.avgSleep);
        pplText = findViewById(R.id.ppl);
        expText = findViewById(R.id.exp);
        exerciseText = findViewById(R.id.exercised);
        firstUsageDay = findViewById(R.id.firstLogged);
        daysLogged = findViewById(R.id.totalLogged);

        Gson gson = new Gson();

        SharedPreferences sh = this.getSharedPreferences("dayData", Context.MODE_PRIVATE);
        Map<String, ?> dayData = sh.getAll();
        Set dateKeys = dayData.keySet();
        List<String> dateList = new ArrayList<>();
        dateList.addAll(dateKeys);
        firstDay = Collections.min(dateList);

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

        avgSocialText.setText((avgSocial / daysTotal) + " h");
        avgSleepText.setText((avgSleep / daysTotal) + " h");
        avgRatingTExt.setText((avgRating / daysTotal) + " h");
        pplText.setText(pplDays + " days");
        expText.setText(expDays + " days");
        exerciseText.setText(exerciseDays + " days");
        firstUsageDay.setText(firstDay);
        daysLogged.setText(daysTotal + " days");
    }

    public void goBack(View v) {
        finish();
    }
}
