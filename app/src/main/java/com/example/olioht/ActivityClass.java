package com.example.olioht;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class ActivityClass extends ActivityScreen {

    /* Class containing information for one particular activity, which will be added to chosen day's
    doneActivities-array upon saving.
     */

    protected int activityRating, activityTime;
    // protected String activityName;

}
    class Drinking extends ActivityClass {
        String activityName = "Drinking";
        int doses;

        public Drinking() {

        }
    }

    class Studying extends ActivityClass {
        private String subject;
        private Boolean withFriends;
        public Studying(String subject, int activityRating, int activityTime, Boolean withFriends) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.subject = subject;
            this.withFriends = withFriends;
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
