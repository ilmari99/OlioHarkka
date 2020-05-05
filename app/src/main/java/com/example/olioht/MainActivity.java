package com.example.olioht;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.ULocale;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    /*
    In this interface we select the day to be edited or created. If the user chooses a day that already has information, the information is read from a text file containing previous day objects.
    After choosing the date and pressing "Confirm" button the user will be redirected to the DayScreen -interface.
     */

    // Declaring variables for different UI components and values
    private static String date, today;
    private String dayString, monthString;
    private CalendarView calendarview;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    /*
    Yritin korjata ongelmaa, mutta nyt en ymmärrä miksi getDate palauttaa aina nykyisen päivän
    riippumatta siitä, että selected == true
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up calendar
        calendarview = findViewById(R.id.calendarView);
        today = sdf.format(new Date(calendarview.getDate()));
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                System.out.println("Selected date");

                if (month < 10) {
                    monthString = "0" + Integer.toString(month + 1);
                } else {
                    monthString = Integer.toString(month + 1);
                }
                if (dayOfMonth < 10) {
                    dayString = "0" + Integer.toString(dayOfMonth);
                } else {
                    dayString = Integer.toString(dayOfMonth);
                }
                date = dayString + "." + monthString + "." + year;
                System.out.println("Selected date " + date);
            }
        });
    }

    // Start the DayScreen activity
    public void selectDay(View v) {
        DayScreen.resetDay(null);
        Intent dayScreenIntent = new Intent(this, DayScreen.class);
        startActivity(dayScreenIntent);
    }

    // getDate-method for other activities needing date
    public static String getDate() {
        if (date == null) {
            return String.valueOf(today);
        }
        else {
            return String.valueOf(date);
        }
    }
}