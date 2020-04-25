package com.example.olioht;


import java.util.HashMap;
import java.util.Hashtable;

public class ActivityClass extends ActivityScreen {

    /* Class containing information for one particular activity, which will be added to chosen day's
    doneActivities-array upon saving.
     */

    protected int activityRating, activityTime;

}
    class Drinking extends ActivityClass {
        private final String activityName = "Drinking";
        private int doses;
        private Boolean passedOut;
        private HashMap<String,String> activityHashMap= new HashMap<>();

        public Drinking(int Rating, int Time, int tempDoses, Boolean passedOutBool) {
            super.activityRating = Rating;
            super.activityTime = Time;
            this.doses = tempDoses;
            this.passedOut = passedOutBool;
            createActivityHash();
        }

        private void createActivityHash(){
            activityHashMap.put("Activity",activityName);
            activityHashMap.put("Rating",String.valueOf(super.activityRating));
            activityHashMap.put("Time",String.valueOf(super.activityTime));
            activityHashMap.put("Alcohol doses",String.valueOf(doses));
            activityHashMap.put("Passed out",String.valueOf(passedOut));
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;
        }
    }

    class Studying extends ActivityClass {
        private final String activityName = "Studying";
        private String subject;
        private Boolean withFriends;
        private HashMap<String,String> activityHashMap = new HashMap<String,String>();

        public Studying(int activityRating, int activityTime, String subject, Boolean withFriends) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.subject = subject;
            this.withFriends = withFriends;
            createActivityHash();
        }

        private void createActivityHash(){
            activityHashMap.put("Activity",activityName);
            activityHashMap.put("Rating",String.valueOf(super.activityRating));
            activityHashMap.put("Time",String.valueOf(super.activityTime));
            activityHashMap.put("Subject",subject);
            activityHashMap.put("With friends",String.valueOf(withFriends));
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;
        }
    }

    class Exercise extends ActivityClass {
        private String sportsType, notes;
        private final String activityName = "Exercise";
        private HashMap<String,String> activityHashMap= new HashMap<>();

        public Exercise(int activityRating, int activityTime, String sportsType, String notes) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.sportsType = sportsType;
            this.notes = notes;
            createActivityHash();
        }

        private void createActivityHash(){
            activityHashMap.put("Activity",activityName);
            activityHashMap.put("Rating",String.valueOf(super.activityRating));
            activityHashMap.put("Time",String.valueOf(super.activityTime));
            activityHashMap.put("Sport type",String.valueOf(sportsType));
            activityHashMap.put("Additional notes",notes);
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;

        }

    }

    class Friends extends ActivityClass {
        private int friendsNumber;
        private String friendsText;
        private final String activityName = "Friends";
        private HashMap<String, String> activityHashMap = new HashMap<>();

        public Friends(int activityRating, int activityTime, int friendsNumber, String friendsText) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.friendsNumber = friendsNumber;
            this.friendsText = friendsText;
            createActivityHash();
        }

        private void createActivityHash() {
            activityHashMap.put("Activity", activityName);
            activityHashMap.put("Rating", String.valueOf(super.activityRating));
            activityHashMap.put("Time", String.valueOf(super.activityTime));
            activityHashMap.put("Number of friends", String.valueOf(friendsNumber));
            activityHashMap.put("Additional notes", friendsText);
        }

        public HashMap getActivityHashMap() {
            return activityHashMap;

        }
    }

    class Relationship extends ActivityClass {
        private String relShipActivity, relShipText;
        private final String activityName = "Relationship";
        private HashMap<String, String> activityHashMap = new HashMap<>();
        private Boolean had_sex;

        public Relationship(int activityRating, int activityTime, String relShipActivity, String relShipText) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.relShipActivity = relShipActivity;
            this.relShipText = relShipText;
            createActivityHash();
        }

        private void createActivityHash() {
            activityHashMap.put("Activity", activityName);
            activityHashMap.put("Rating", String.valueOf(super.activityRating));
            activityHashMap.put("Time", String.valueOf(super.activityTime));
            activityHashMap.put("Relationship activity", relShipActivity);
            activityHashMap.put("Had sex", String.valueOf(had_sex));
        }

        public HashMap getActivityHashMap() {
            return activityHashMap;

        }
    }
