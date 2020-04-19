package com.example.olioht;

public class DayClass extends DayScreen{
    /*
    In this class we only have the attributes of one day. Changing and setting of values happens in DayScreen.
    In the doneActivities -array we have ActivityClass -objects.
    Saa nähdä pystyykö tollasta olio -arrayta toisen olion sisällä tallentaa tekstitiedostoon.
     */

    private String date;
    private int sleepTime, socialTime, dayRating;
    private Boolean newExperience, newPeople, exercise;                             //Tieto exercisesta haetaan Aktivitysta
    ActivityClass[] doneActivities;                                                 //Array jossa on ActivityClass Olioita
    //Ei pysty siis lisäämään yli kymmentä aktiviteettia päivässä

    public DayClass(String date, int sleepTime, int socialTime, int dayRating, Boolean experience, Boolean people, Boolean exercise) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.socialTime = socialTime;
        this.dayRating = dayRating;
        this.newExperience = experience;
        this.newPeople = people;
        this.exercise = exercise;
        doneActivities = new ActivityClass[10];
    }
}
