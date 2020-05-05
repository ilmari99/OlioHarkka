package com.example.olioht;


import java.util.HashMap;
import java.util.Hashtable;

public class ActivityClass {

    /* Class containing information for one particular activity, which will be added to chosen day's
    doneActivities-array upon saving.
     */

    protected int activityRating, activityTime;
    
}
    class Drinking extends ActivityClass {
        private final String activityName = "Drinking";
        private int doses;
        private Boolean passedOut;
        private String notes;
        private transient HashMap<String,String> activityHashMap= new HashMap<>();

        public Drinking(int Rating, int Time, int tempDoses, Boolean passedOutBool,String notez) {
            super.activityRating = Rating;
            super.activityTime = Time;
            this.doses = tempDoses;
            this.passedOut = passedOutBool;
            this.notes = notez;
            createActivityHash();
        }

        /*
        Creating a HashMap for every class
        I think it is easier to handle and print if necessary
         */

        private void createActivityHash(){
            activityHashMap.put("Activity",activityName);
            activityHashMap.put("Rating",String.valueOf(super.activityRating));
            activityHashMap.put("Time",String.valueOf(super.activityTime));
            activityHashMap.put("Alcohol doses",String.valueOf(doses));
            activityHashMap.put("Passed out",String.valueOf(passedOut));
            activityHashMap.put("Notes",notes);
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;
        }
    }

    class Studying extends ActivityClass {
        private final String activityName = "Studying";
        private String subject,notes;
        private Boolean withFriends;
        private HashMap<String,String> activityHashMap = new HashMap<String,String>();

        public Studying(int activityRating, int activityTime, String subject, Boolean withFriends,String notez) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.subject = subject;
            this.withFriends = withFriends;
            this.notes = notez;
            createActivityHash();
        }

        private void createActivityHash(){
            activityHashMap.put("Activity",activityName);
            activityHashMap.put("Rating",String.valueOf(super.activityRating));
            activityHashMap.put("Time",String.valueOf(super.activityTime));
            activityHashMap.put("Subject",subject);
            activityHashMap.put("With friends",String.valueOf(withFriends));
            activityHashMap.put("Notes",notes);
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;
        }
    }

    class Exercise extends ActivityClass {
        private String sportsType, notes;
        private final String activityName = "Exercise";
        private transient HashMap<String,String> activityHashMap= new HashMap<>();

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
            activityHashMap.put("Notes",notes);
        }

        public HashMap getActivityHashMap(){
            return activityHashMap;

        }

    }

    class Friends extends ActivityClass {
        private int friendsNumber;
        private String notes;
        private final String activityName = "Friends";
        private transient HashMap<String, String> activityHashMap = new HashMap<>();

        public Friends(int activityRating, int activityTime, int friendsNumber, String notez) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.friendsNumber = friendsNumber;
            this.notes = notez;
            createActivityHash();
        }

        private void createActivityHash() {
            activityHashMap.put("Activity", activityName);
            activityHashMap.put("Rating", String.valueOf(super.activityRating));
            activityHashMap.put("Time", String.valueOf(super.activityTime));
            activityHashMap.put("Number of friends", String.valueOf(friendsNumber));
            activityHashMap.put("Notes", notes);
        }

        public HashMap getActivityHashMap() {
            return activityHashMap;

        }
    }

    class Relationship extends ActivityClass {
        private String relShipActivity, notes;
        private final String activityName = "Relationship";
        private transient HashMap<String, String> activityHashMap = new HashMap<>();
        private Boolean had_sex;

        public Relationship(int activityRating, int activityTime, String relShipActivity, String relShipNotes) {
            super.activityRating = activityRating;
            super.activityTime = activityTime;
            this.relShipActivity = relShipActivity;
            this.notes = relShipNotes;
            createActivityHash();
        }

        private void createActivityHash() {
            activityHashMap.put("Activity", activityName);
            activityHashMap.put("Rating", String.valueOf(super.activityRating));
            activityHashMap.put("Time", String.valueOf(super.activityTime));
            activityHashMap.put("Relationship activity", relShipActivity);
            activityHashMap.put("Had sex", String.valueOf(had_sex));
            activityHashMap.put("Notes",notes);
        }

        public HashMap getActivityHashMap() {
            return activityHashMap;

        }
    }
