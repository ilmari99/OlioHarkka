package com.example.olioht;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ActivityScreen  extends DayScreen {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);
        String date = getDate();
        Object activity;
        double actTime,actRating; //TODO ActivityScreenissä näytetään arvoja jo ennen valitun luokan luontia. Arvoja pystyy myös muuttamaan ja kun luokka on valittu, annetut tiedot kopioidaan
        //Timelle text input
        //ratingille dropdown(?)
        //Luokan voi luoda vasta valinnan jälkeen, koska ActivityClass ei voi periyttää ehdollisesti!

        String valinta = getActivityFromMenu();
        System.out.println(valinta);
        switch (valinta) {
            case ("Studying"):
                //Avaa fragmentin ActivityClassin kysymyksillä
                //Eli Drinking -luokan constructori tekee fragmentin
                activity = null;
                Studying activity = new Studying();
                //Sitten kysytään käyttäjältä arvoja, ja tallennetaan arvot vasta kun käyttäjä painaa tallenna
                //Näin vältytään monen Listenerin luonnilta
                System.out.println(valinta); //Printit vain testausta varten
                break;
            case ("Exercise"):
                activity = null;
                Exercise activity = new Exercise();
                System.out.println(valinta);
                day.exercise = true;
                break;
            case ("Drinking"):
                activity = null;
                Drinking activity = new Drinking();
                System.out.println(valinta);
                break;
            case ("Friends"):
                activity = null;
                Friends activity = new Friends();
                System.out.println(valinta);
                break;
            case ("Relationship"):
                activity = null;
                Relationship activity = new Relationship();
                System.out.println(valinta);
                break;
            default:
                System.out.println("Tyhjä");
                break;
        }
    }

    public void saveChanges(ActivityClass act) {
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

