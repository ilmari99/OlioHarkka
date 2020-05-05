package com.example.olioht;

import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class DayClass{

    /*
    In this class we only have the attributes of one day. Changing and setting of values happens in DayScreen.
    In the doneActivities -array we have ActivityClass -objects.
    Wondering if it's possible to save an object containing object-filled array to text file?
     */

    private String date;
    private int sleepTime, socialTime, dayRating;
    private Boolean newExperience, newPeople, exercise;
    ArrayList<ActivityClass> doneActivities;
    transient HashMap<String,String> dayAttributes = new HashMap<>();

    public DayClass(String date, int sleepTime, int socialTime, int dayRating, Boolean experience, Boolean people, Boolean exercise,ArrayList<ActivityClass> activs) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.socialTime = socialTime;
        this.dayRating = dayRating;
        this.newExperience = experience;
        this.newPeople = people;
        this.exercise = exercise;
        doneActivities = activs;
    }



    //Created a hashtable for day attributes
    @RequiresApi(api = Build.VERSION_CODES.N) //TÄMÄ vitun outo tuli kun käytin SimpleDateFormattia
    public void createDayHash(){
        dayAttributes.put("Date",MainActivity.getDate());
        dayAttributes.put("Rating",String.valueOf(dayRating));
        dayAttributes.put("Sleep time",String.valueOf(sleepTime));
        dayAttributes.put("Social time",String.valueOf(socialTime));
        dayAttributes.put("New experience",String.valueOf(newExperience));
        dayAttributes.put("New people",String.valueOf(newPeople));
        dayAttributes.put("Exercise",String.valueOf(exercise));
    }
    /*This prints the attributes of an activity
    Not necessarily necessary in the final product, but helps in developing.

     */

        public void printAllDayData(){
        System.out.println(dayAttributes);
        HashMap tempActHash = null;
        int i = 0;
        while(i<doneActivities.size()){
            Object act = doneActivities.get(i);
            if(act == null){break;}
            if(act instanceof Studying){
                tempActHash = ((Studying) act).getActivityHashMap();
            }
            if(act instanceof Relationship){
                tempActHash = ((Relationship) act).getActivityHashMap();
            }
            if(act instanceof Friends){
                tempActHash = ((Friends) act).getActivityHashMap();
            }
            if(act instanceof Exercise){
                tempActHash = ((Exercise) act).getActivityHashMap();
            }
            if(act instanceof Drinking){
                tempActHash = ((Drinking) act).getActivityHashMap();
            }
            System.out.println(i);
            System.out.println(tempActHash);
            i++;
        }
        }


}
