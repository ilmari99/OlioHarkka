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
    private static String date;
    private CalendarView calendarview;
    private Boolean selected = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    /*
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    date = sdf.format(new Date(calendarview.getDate()));
     *///todo Kalenterissa oli ongelma, että jos ei valittu päivää (Mentiin oletuksella eli nykyisellä päivällä) niin päivämäärä oli null. En tajua miks kalenteri antaa nyt ainoastaan nykyisen päivämäärän :D
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting up calendar
        calendarview = findViewById(R.id.calendarView);
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            // TODO Set date to current date if it's not changed in calendar

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selected = true;
                date = dayOfMonth + "." + month + "." + year;
                System.out.println("Selected");
            }
        });
        if(!selected){ //Pitäisi tässä asettaa date arvo vain, jos selected == false
            date = sdf.format(new Date(calendarview.getDate()));
            System.out.println("Not selected");
        }
    }
    // Start the DayScreen activity
    public void selectDay(View v) {
        Intent dayScreenIntent = new Intent(this, DayScreen.class);
        startActivity(dayScreenIntent);
    }


    // getDate-method for other activities needing date
    public static String getDate() {
        return String.valueOf(date);
    }
}