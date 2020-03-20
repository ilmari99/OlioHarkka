package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DayScreen extends MainActivity{

    TextView selectedDate;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayscreen);

        selectedDate = findViewById(R.id.selectedDate);
        selectedDate.setText(getString(R.string.selectedDate) + " " + MainActivity.getDate());
    }

    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
    }

}
