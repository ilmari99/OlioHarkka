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

}
    class Drinking extends ActivityClass {
        private int doses;
        private Boolean passedOut;

        public Drinking(int activityRating, int activityTime, int doses, Boolean passedOut) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.doses = doses;
            this.passedOut = passedOut;
        }
    }

    class Studying extends ActivityClass {
        private String subject;
        private Boolean withFriends;

        public Studying(int activityRating, int activityTime, String subject, Boolean withFriends) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.subject = subject;
            this.withFriends = withFriends;
        }
    }

    class Exercise extends ActivityClass {
        private String sportsType, notes;

        public Exercise(int activityRating, int activityTime, String sportsType, String notes) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.sportsType = sportsType;
            this.notes = notes;
        }

    }

    class Friends extends ActivityClass {
        private int friendsNumber;
        private String friendsText;

        public Friends(int activityRating, int activityTime, int friendsNumber, String friendsText) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.friendsNumber = friendsNumber;
            this.friendsText = friendsText;
        }
    }

    class Relationship extends ActivityClass {
        private

        public Relationship(int activityRating, int activityTime) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.subject = subject;
            this.withFriends = withFriends;
        }

    }
