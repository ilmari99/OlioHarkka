package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScreen  extends DayScreen {

    /*
    In this interface the user can look at a done activity or edit an existing one.

    If the activity is empty (the user came by selecting "Add") the user can add values to attributes that are common for all Activities (time, rating).
    After selecting activity type, a corresponding fragment will pop-up. The fragment should be an extension and time and rating should still be editable.

    If the user is editing an activity (the user chose an activity from doneActivities -array in the DayClass) then the corresponding fragment will be shown instantly on this interface.
    The attributes will be editable similar to DayClass. Except, if the user tries to edit the activity type from the dropdown, there will pop-up a Warning -fragment, similar to DayClass.

    Also similar to DayClass, the data will only be saved by pressing the "Save" -button.
     */

    private ActivityClass activity = null;
    private Spinner activitySpinner, actRatingSpinner;
    private Fragment drinkingFrag, exerciseFrag, friendsFrag, relationFrag, studyFrag;
    private String valinta;
    private TextView infoTextBox;
    private FrameLayout activityFrame;
    private int activityCounter = 0;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);

        activitySpinner = findViewById(R.id.activityDropdown);
        actRatingSpinner = findViewById(R.id.activityRatingDropdown);
        infoTextBox = findViewById(R.id.activityScreenInfoMessage);

        // Grounds for fragments

        FragmentManager fragmentManager = getSupportFragmentManager();


        drinkingFrag = new DrinkingFragment();
        exerciseFrag = new ExerciseFragment();
        friendsFrag = new FriendsFragment();
        relationFrag = new RelationshipFragment();
        //studyFrag = new StudyFragment();

        String date = MainActivity.getDate();
        double actTime, actRating;      //TODO ActivityScreenissä näytetään arvoja jo ennen valitun luokan luontia. Arvoja pystyy myös muuttamaan ja kun luokka on valittu, annetut tiedot kopioidaan

        //Timelle text input
        //ratingille dropdown(?)
        //Luokan voi luoda vasta valinnan jälkeen, koska ActivityClass ei voi periyttää ehdollisesti!

         activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                valinta = String.valueOf(activitySpinner.getSelectedItem());
                activityFrame = findViewById(R.id.activityFrame);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction drinkingFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction exerciseFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction friendsFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction relationFragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction studyFragmentTransaction = fragmentManager.beginTransaction();

                switch (valinta) {
                    case ("Studying"):
                        // Avaa fragmentin ActivityClassin kysymyksillä
                        // Eli Drinking -luokan constructori luo fragmentin
                        activity = new Studying();
                        //Sitten kysytään käyttäjältä arvoja, ja tallennetaan arvot vasta kun käyttäjä painaa tallenna
                        //Näin vältytään monen Listenerin luonnilta
                        studyFrag = new StudyFragment();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        studyFragmentTransaction.replace(R.id.activityFrame, studyFrag);
                        studyFragmentTransaction.commit();
                        break;
                    case ("Exercise"):
                        activity = new Exercise();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        exerciseFrag = new ExerciseFragment();
                        exerciseFragmentTransaction.replace(R.id.activityFrame, exerciseFrag);
                        exerciseFragmentTransaction.commit();
                        break;
                    case ("Drinking"):
                        activity = new Drinking();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        drinkingFrag = new DrinkingFragment();
                        drinkingFragmentTransaction.replace(R.id.activityFrame, drinkingFrag);
                        drinkingFragmentTransaction.commit();
                        break;
                    case ("Friends"):
                        activity = new Friends();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        friendsFrag = new FriendsFragment();
                        friendsFragmentTransaction.replace(R.id.activityFrame, friendsFrag);
                        friendsFragmentTransaction.commit();
                        break;
                    case ("Relationship"):
                        activity = new Relationship();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        relationFrag = new RelationshipFragment();
                        relationFragmentTransaction.replace(R.id.activityFrame, relationFrag);
                        relationFragmentTransaction.commit();
                        break;
                    default:
                        break;
                }
                System.out.println(valinta);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goBack (View v){
        //TODO lisätään tähän varoitus -fragmentti, että jos poistut tallentamatta menetät muutokset
        //day = null;
        Intent goBackIntent = new Intent(this, DayScreen.class);
        startActivity(goBackIntent);
    }





}

        // String valinta = getActivityFromMenu();
        //System.out.println(valinta);

        /*switch (valinta) {
            case ("Studying"):
                //Avaa fragmentin ActivityClassin kysymyksillä
                //Eli Drinking -luokan constructori luo fragmentin
                activity = new Studying();
                //Sitten kysytään käyttäjältä arvoja, ja tallennetaan arvot vasta kun käyttäjä painaa tallenna
                //Näin vältytään monen Listenerin luonnilta
                System.out.println(valinta); //Printit vain testausta varten
                activityFragment = new ActivityFragment();
                activityFragmentTransaction.replace(R.id.activityFrame, activityFragment);
                activityFragmentTransaction.commit();

                break;
            case ("Exercise"):
                activity = new Exercise();
                System.out.println(valinta);
                //day.exercise = true;
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
                activity = new Relationship();
                System.out.println(valinta);
                break;
            default:
                System.out.println("Tyhjä");
                break;
        }
    }

    /*public void saveChanges(ActivityClass act) {//Tallennetaan Arrayhy
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
    }*/



   /* protected String getActivityFromMenu() {
        Spinner activitySpinner;
        activitySpinner = findViewById(R.id.activityDropdown);
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return String.valueOf(activitySpinner.getSelectedItem());
    }*/


