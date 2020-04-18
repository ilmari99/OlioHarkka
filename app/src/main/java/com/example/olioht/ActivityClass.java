package com.example.olioht;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
/*ActivityClassista tulee fragmentti ActivityScreeniin
ActivityScreenin siis pitäisi olla interface, mutta "lisäkysymykset" tulevat fragmenttina ActivityScreenin päälle
Entä, jos aukaisee fragmentin, niin pystyykö silloin samaan aikaan muokata interfacessa olevia tietoja?
 */

public class ActivityClass extends ActivityScreen {

    protected double rating, time;
    String date;
    //Timelle text input
    //ratingille dropdown(?)

    Fragment activityFragment;
    FragmentManager activityFragmentManager = getSupportFragmentManager();
    FragmentTransaction activityFragmentTransaction = activityFragmentManager.beginTransaction();

 /*Activity luokat: Kun valitaan Activitetti ActivityScreenissä olevasta activityDropdownlistasta
    Lisätään ActivityScreeniin valitun aktiviteetin luokan kysymykset esim. valitaan aktiviteetti Studying --> Kysytään dropdown listalla opiskeltu aine, opiskeltiinko yksin, jne.
    TODO Luo jokaiselle AktivityClass luokan sisäluokalle fragmentti, joka näytetään, kun käyttäjä valitsee aktiviteetin
    Kun fragmentti avataan, täytyy activiteetin tietojen silti näkyä ja olla muokattavissa.
     */

 /*

  */
}
    class Drinking extends ActivityClass {
        String activityname = "Drinking";
        int doses;

        public Drinking() {

        }
    }

    class Studying extends ActivityClass {
        String activityactivityname = "Studying";
        Boolean alone;
        // String subject = getStudySubject();

        public Studying() {

        }
    }

    class Exercise extends ActivityClass {
        String activityname = "Exercise";

        public Exercise() {

        }

    }

    class Friends extends ActivityClass {
        String activityname = "Friends";
        int numberofpeople;
        /*String activitywithFriends = getActivitywithfriends();

        private String getActivitywithfriends() {
            Spinner ActivitywithfriendsSpinner;
            ActivitywithfriendsSpinner = (Spinner) findViewById(R.id.activitywithfriendsDropdown);
            ActivitywithfriendsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            return String.valueOf(ActivitywithfriendsSpinner.getSelectedItem());
        }*/

        Friends() {

        }

    }

    class Relationship extends ActivityClass{
        String activityname = "Relationship";
        Boolean had_sex;
        int her_age;
        /*String RelationshipActivity = getRelationshipActivity();

        private String getRelationshipActivity() {
            Spinner relationshipSpinner;
            relationshipSpinner = (Spinner) findViewById(R.id.relationshipActivityDropdown);
            relationshipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return String.valueOf(relationshipSpinner.getSelectedItem());
        }*/
        Relationship(){


        }

    }
