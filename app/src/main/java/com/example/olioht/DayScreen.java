package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Collections;

public class DayScreen extends MainActivity{

    String[] ratingChoices = {"1","2","3","4","5","6","7","8","9","10"};
    private int dayRating, socialTime;
    private Spinner dayRatingSpinner;
    private EditText socialTimeBox;
    TextView selectedDate;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayscreen);

        socialTimeBox = findViewById(R.id.socialTimeTextBox);
        dayRatingSpinner = findViewById(R.id.dayRatingDropdown);
        selectedDate = findViewById(R.id.selectedDate);
        selectedDate.setText(getString(R.string.selectedDate) + " " + MainActivity.getDate());

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ratingChoices);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayRatingSpinner.setAdapter(ratingAdapter);

        dayRatingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
    }

    public int getDayRating(){
        return dayRating;
    }

    public int getSocialTime() {
        return socialTime;
    }

    public void saveData(View v) {

    }
}
