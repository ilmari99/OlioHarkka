package com.example.olioht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    /*
    In this interface we select the day to be edited or created. If the user chooses a day that already has information, the information is read from a text file containing previous day objects.
    After choosing the date and pressing "Confirm" button the user will be redirected to the DayScreen -interface.
     */

    // Declaring variables for different UI components and values
    private static String date;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up calendar
        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            // TODO Set date to current date if it's not changed in calendar

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                date = dayOfMonth + "." + (month + 1) + "." + year;
            }
        });

    }


    // Start the DayScreen activity
    public void selectDay(View v) {
        Intent dayScreenIntent = new Intent(this, DayScreen.class);
        startActivity(dayScreenIntent);
    }


    // getDate-method for other activities needing date
    public static String getDate() {
        return date;
    }
}