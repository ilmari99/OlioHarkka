package com.example.olioht;

public class DayClass extends DayScreen{

    /*
    In this class we only have the attributes of one day. Changing and setting of values happens in DayScreen.
    In the doneActivities -array we have ActivityClass -objects.
    Wondering if it's possible to save an object containing object-filled array to text file?
     */

    private String date;
    private int sleepTime, socialTime, dayRating;
    private Boolean newExperience, newPeople, exercise;
    ActivityClass[] doneActivities;

    public DayClass(String date, int sleepTime, int socialTime, int dayRating, Boolean experience, Boolean people, Boolean exercise) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.socialTime = socialTime;
        this.dayRating = dayRating;
        this.newExperience = experience;
        this.newPeople = people;
        this.exercise = exercise;
        doneActivities = new ActivityClass[9];     // Maximum of 10 activities a day
    }
}
