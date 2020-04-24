package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScreen extends  DayScreen {

    /*
    In this interface the user can look at a done activity or edit an existing one.

    If the activity is empty (the user came by selecting "Add") the user can add values to attributes that are common for all Activities (time, rating).
    After selecting activity type, a corresponding fragment will pop-up. The fragment should be an extension and time and rating should still be editable.

    If the user is editing an activity (the user chose an activity from doneActivities -array in the DayClass) then the corresponding fragment will be shown instantly on this interface.
    The attributes will be editable similar to DayClass. Except, if the user tries to edit the activity type from the dropdown, there will pop-up a Warning -fragment, similar to DayClass.

    Also similar to DayClass, the data will only be saved by pressing the "Save" -button.
     */

    public ActivityClass activity = null;
    private Spinner activitySpinner, actRatingSpinner;
    Fragment drinkingFrag, exerciseFrag, friendsFrag, relationFrag, studyFrag;
    private String valinta;
    protected TextView infoTextBox;
    public FrameLayout activityFrame;
    private int activityCounter = 0;
    String date = MainActivity.getDate();
    private String hours, activityRating;//TODO ActivityScreenissä näytetään arvoja jo ennen valitun luokan luontia. Arvoja pystyy myös muuttamaan ja kun luokka on valittu, annetut tiedot kopioidaan
    TextView currentDate;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscreen);

        activitySpinner = findViewById(R.id.activityDropdown);
        actRatingSpinner = findViewById(R.id.activityRatingDropdown);
        infoTextBox = findViewById(R.id.activityScreenInfoMessage);
        currentDate = findViewById(R.id.currentDateTextBox);
        currentDate.setText(MainActivity.getDate()); //Todo miks tämä ei toimi

        // Grounds for fragments



        drinkingFrag = new DrinkingFragment();
        exerciseFrag = new ExerciseFragment();
        friendsFrag = new FriendsFragment();
        relationFrag = new RelationshipFragment();
        studyFrag = new StudyFragment();

        //Luokan voi luoda vasta valinnan jälkeen, koska ActivityClass ei voi periyttää ehdollisesti!

        //Valittu activity dropdownista
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
                    //todo tänne jonnekin joku ehto, että jos on täyttänyt esim. Friends tietoja ja sen jälkeen vaihtaa aktiviteettia Studying --> tiedot katoaa. Eli joku varoitus
                    case ("Studying"):
                        activity = new Studying();
                        infoTextBox.setVisibility(View.INVISIBLE);
                        studyFragmentTransaction.commit();
                        studyFragmentTransaction.replace(R.id.activityFrame, studyFrag);
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
                        infoTextBox.setVisibility(View.INVISIBLE);
                        friendsFrag = new FriendsFragment();
                        friendsFragmentTransaction.replace(R.id.activityFrame, friendsFrag);
                        friendsFragmentTransaction.commit();
                        activity = new Friends();
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

    String getActivityRating() {
        return String.valueOf(actRatingSpinner.getSelectedItem());
    }

    void saveToActivity() {
        //todo ehto: jos getActivityRating ei ole integer (eli se on tyhjä), niin tulee fragmentti tai ilmoitus joka pyytää käyttäjää antamaan ratingin.
        activity.rating = getActivityRating();
        activity.hours = hours;
    }

    public void goBack(View v) {
        //TODO lisätään tähän varoitus -fragmentti, että jos poistut tallentamatta menetät muutokset
        Intent goBackIntent = new Intent(this, DayScreen.class);
        startActivity(goBackIntent);
    }

    public void saveChanges(View v) {//Tallennetaan Arrayhyn
        saveToActivity();
        int i = 0;
        while (i < 10) {
            if (day.doneActivities[i] == null) {
                day.doneActivities[i] = activity;
                break;
            } else {
                i++;
            }
        }
        printAttributes();
    }

    void printAttributes() { //todo jostain syystä tässä aliohjelmassa kun kutsuu "getAttributes()" -> IllegalStateException
        int i = 0;
        while (day.doneActivities[i] != null) {
            ActivityClass act = day.doneActivities[i];
            int n = 0;

            if(act instanceof Drinking) {
                String[] attributes = ((Drinking) act).getAttributes();
                int numberofattributes = attributes.length;
                while(n<numberofattributes) {
                    System.out.println(attributes[n]);
                    n++;
                }
            }

            else if(act instanceof Studying) {
                String[] attributes = ((Studying) act).getAttributes();
                int numberofattributes = attributes.length;
                while (n < numberofattributes) {
                    System.out.println(attributes[n]);
                    n++;
                }
            }

            else if(act instanceof Exercise) {
                String[] attributes = ((Exercise) act).getAttributes();
                int numberofattributes = attributes.length;
                while (n < numberofattributes) {
                    System.out.println(attributes[n]);
                    n++;
                }
            }

            else if(act instanceof Friends) {
                String[] attributes = ((Friends) act).getAttributes();
                int numberofattributes = attributes.length;
                while (n < numberofattributes) {
                    System.out.println(attributes[n]);
                    n++;

                }
            }

            else if(act instanceof Relationship) {
                String[] attributes = ((Relationship) act).getAttributes();
                int numberofattributes = attributes.length;
                while (n < numberofattributes) {
                    System.out.println(attributes[n]);
                    n++;
                }
            }

                i++;
        }
        }
    }





