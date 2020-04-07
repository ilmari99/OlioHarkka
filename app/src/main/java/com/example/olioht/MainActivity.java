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

    private static String date;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "." + (month + 1) + "." + year;
            }
        });
    }

    public void selectDay(View v) {
        Intent dayScreenIntent = new Intent(this, DayScreen.class);
        startActivity(dayScreenIntent);
    }

    public static String getDate() {
        return date;
    }

}