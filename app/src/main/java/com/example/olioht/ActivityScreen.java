package com.example.olioht;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ActivityScreen  extends DayScreen {
    /*
    In this interface the user can look at a done activity or edit an existing one.

    If the activity is empty (the user came by selecting "Add") the user can add values to attributes that are common for all Activities (time, rating).
    After selecting activity type, a corresponding fragment will pop-up. The fragment should be an extension and time and rating should still be editable.

    If the user is editing an activity (the user chose an activity from doneActivities -array in the DayClass) then the corresponding fragment will be shown instantly on this interface.
    The attributes will be editable similar to DayClass. Except, if the user tries to edit the activity type from the dropdown, there will pop-up a Warning -fragment, similar to DayClass.

    Also similar to DayClass, the data will only be saved by pressing the "Save" -button.


     */

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);
        String date = getDate();
        ActivityClass activity = new ActivityClass();
        double actTime,actRating; //TODO ActivityScreenissä näytetään arvoja jo ennen valitun luokan luontia. Arvoja pystyy myös muuttamaan ja kun luokka on valittu, annetut tiedot kopioidaan
        //TODO Timelle text input
        //TODO ratingille dropdown(?)

        String valinta = getActivityFromMenu();
        System.out.println(valinta);
        switch (valinta) {
            case ("Studying"):
                //Avaa fragmentin ActivityClassin kysymyksillä
                //Eli Drinking -luokan constructori luo fragmentin
                activity = new Studying();
                //Sitten kysytään käyttäjältä arvoja, ja tallennetaan arvot vasta kun käyttäjä painaa tallenna
                //Näin vältytään monen Listenerin luonnilta
                System.out.println(valinta); //Printit vain testausta varten
                break;
            case ("Exercise"):
                activity = new Exercise();
                System.out.println(valinta);
                day.exercise = true;
                break;
            case ("Drinking"):
                activity = new Drinking();
                System.out.println(valinta);
                break;
            case ("Friends"):
                activity = new Friends();
                System.out.println(valinta);
                break;
            case ("Relationship"):
                activity = null;
                activity = new Relationship();
                System.out.println(valinta);
                break;
            default:
                System.out.println("Tyhjä");
                break;
        }
    }

    public void saveChanges(ActivityClass act) {//Tallennetaan Arrayhy
        int i = 0;
        while (i<10){
            if (day.doneActitivities[i] == null){
                day.doneActitivities[i] = act;
                break;
            }
            else{
                i++;
            }
        }
    }



    protected String getActivityFromMenu(){
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

