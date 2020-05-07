package com.example.olioht;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class AnalyzeAllScreen extends MainActivity {

    // Declaring variables for different UI components and values
    private TextView avgSocialText, avgRatingTExt, avgSleepText, pplText, expText, exerciseText;
    private TextView firstUsageDay, daysLogged;
    private String[] analysedData;

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        // Getting analysed data from file
        analysedData = dataProcessor.analyseAllData(this);

        firstUsageDay.setText(analysedData[0]);
        daysLogged.setText(analysedData[1]);
        avgRatingTExt.setText(analysedData[2]);
        avgSocialText.setText(analysedData[3]);
        avgSleepText.setText(analysedData[4]);
        expText.setText(analysedData[5]);
        pplText.setText(analysedData[6]);
        exerciseText.setText(analysedData[7]);
    }

    public void goBack(View v) {
        finish();
    }
}
