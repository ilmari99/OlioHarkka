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
    //TODO Selvitetään miten saadaan parametrit dropdown listojen sijasta. Esim. sleep(h) ei voi olla >24h ja rating on välillä [0,10]

    private static String date;
    private CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendarView);



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            // TODO Määritellään pvm tämänhetkiseksi jos pvm:ää ei erikseen muuteta kalenterissa

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