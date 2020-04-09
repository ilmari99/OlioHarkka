package com.example.olioht;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Collections;

public class DayScreen extends MainActivity {
/*
In this interface we create a new DayClass or display editable attributes from a day that was already filled.
In this screen the user can fill or edit information about a particular day. If there is no data about the day, then the EditText -fields will be blank, but if there was data about the day,
the data will be displayed on the EditText fields and the user can edit the information easily.
 The data is ONLY saved to a file after the user presses the "Save" -button. When overwriting, there will pop-up a "Warning" -fragment.

 There will only be one instance of DayClass at a time. Others will be stored on the file.

 In this interface, there will be a list of DoneActivities, where the user can select added activities or select "Add".
 After choosing an activity or Add, the user can press "Go to activity" which will open a new Interface "ActivityScreen".

 */
//TODO lisää check-boxit boolean tyypin attribuuteille DayClassista.
    protected DayClass day = new DayClass(); //Luodaan uusi päivä TODO Muokkaa niin, että ei luoda uutta päivää jos päivämäärällä löytyy tietoja

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayscreen);

        EditText socialTimeText = findViewById(R.id.socialTimeTextBox);
        EditText sleepTimeText = findViewById(R.id.sleepTimeTextBox);
        TextView selectedDate = findViewById(R.id.selectedDate);
        selectedDate.setText(MainActivity.getDate());
        /*
        this.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(v,socialTimeText,sleepTimeText);
            }
        });
        */




        /*
        final Spinner dayRatingSpinner = findViewById(R.id.dayRatingDropdown);
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
*/
    }

        private String getRating () {
            Spinner ratingSpinner;
            ratingSpinner = (Spinner) findViewById(R.id.dayRatingDropdown);
            ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return String.valueOf(ratingSpinner.getSelectedItem());
        }


        public void goBack (View v){
            //TODO lisätään tähän varoitus, että jos poistut tallentamatta menetät muutokset
            day = null;
            Intent goBackIntent = new Intent(this, MainActivity.class);
            startActivity(goBackIntent);
        }


        //Jos nyt vasta tallennetaan kaikki day olion tiedot, niin ei tarvitse listenereja
        public void saveData (View v, EditText socialTimeText, EditText sleepTimeText){
            day.socialTime = Integer.parseInt(socialTimeText.getText().toString());
            day.sleeptime = Integer.parseInt(sleepTimeText.getText().toString());
            day.dayRating = Integer.parseInt(getRating());
        }
        //TODO En saanut nappeja toimimaan

        public void saveDayToFile () {
            //Tallennetaan päivälle kuuluvat tiedot tiedostoon
        }

        public void addOrEditActivity (View v) {    //  TODO  näiden getterit editActivity ja addActivity-metodeihin: ActivityClass act, EditText socialTimeText, EditText, sleepTimeText

            if (getActivityFromDayScreen().equals("Add")) {
                addActivity(v, socialTimeText, sleepTimeText);
            }
            else {
                editActivity(v, act, socialTimeText, sleepTimeText);
            }
        }


        //TODO Kun muokataan aktiviteettia pitää saada suoraan auki kaikki vanhan aktiviteetin tiedot
        //Tässä täytyy siis avata suoraan ActivityScreen ja aktiviteetille kuuluva  fragmentti
        public void editActivity (View v, ActivityClass act, EditText socialTimeText, EditText
        sleepTimeText){
            saveData(v, socialTimeText, sleepTimeText);
            Intent activityScreenIntent = new Intent(this, ActivityScreen.class);
            startActivity(activityScreenIntent);
        }

        public void addActivity (View v, EditText socialTimeText, EditText sleepTimeText){
            saveData(v, socialTimeText, sleepTimeText);
            Intent activityScreenIntent = new Intent(this, ActivityScreen.class);
            startActivity(activityScreenIntent);
        }


        protected String getActivityFromDayScreen () {
            Spinner activitySpinner;
            activitySpinner = (Spinner) findViewById(R.id.activityDropdown);
            activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return String.valueOf(activitySpinner.getSelectedItem());
        }



}
