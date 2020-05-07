package com.example.olioht;

import androidx.annotation.RequiresApi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AnalyzeChosenScreen extends MainActivity {

    // Declaring variables for different UI components and values
    private String date;
    private TextView dateText, dayRating, sleep, socialTime, activityText, exp, ppl, exerc;
    private String[] analyzedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_chosen_screen);

        date = MainActivity.getDate();

        // Finding text boxes
        dateText = findViewById(R.id.dateText);
        dayRating = findViewById(R.id.dayRating);
        sleep = findViewById(R.id.sleep);
        socialTime = findViewById(R.id.socialTime);
        activityText = findViewById(R.id.activityText);
        exp = findViewById(R.id.exp);
        ppl = findViewById(R.id.ppl);
        exerc = findViewById(R.id.exerc);

        // Getting data for selected day. If no data is available,
        analyzedData = dataProcessor.analyzeChosen(this, date);
        if (analyzedData == null || analyzedData.length == 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No data available!");
            alertDialogBuilder.setMessage("No data available for selected day. Please select other day.");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
        }
        else {
            dateText.setText(analyzedData[0]);
            dayRating.setText(analyzedData[1]);
            sleep.setText(analyzedData[2]);
            socialTime.setText(analyzedData[3]);
            exp.setText(analyzedData[4]);
            ppl.setText(analyzedData[5]);
            exerc.setText(analyzedData[6]);
            activityText.setText("Done activities: " + analyzedData[7]);
        }
    }

    public void goBack(View v) {
        finish();
    }

    private void endActivity() {
        finish();
    }
}