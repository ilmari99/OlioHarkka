package com.example.olioht;

public class DayClass extends DayScreen{
    /*
    In this class we only have the attributes of one day. Changing and setting of values happens in DayScreen.
    In the doneActivities -array we have ActivityClass -objects.
    Saa nähdä pystyykö tollasta olio -arrayta toisen olion sisällä tallentaa tekstitiedostoon.
     */

    public static DayClass singletonDay;
    String date = MainActivity.getDate();
    int sleepTime, socialTime, dayRating;
    Boolean newExperience, newPeople, exercise;                             //Tieto exercisesta haetaan Aktivitysta
    ActivityClass[] doneActivities = new ActivityClass[10];                 //Array jossa on ActivityClass Olioita
    //Ei pysty siis lisäämään yli kymmentä aktiviteettia päivässä




    // For keeping DayClass as singleton
    public DayClass() {
        if (singletonDay == null) {
            sleepTime = -1;
            socialTime = -1;
            dayRating = -1;
            /*newExperience = false;
            newPeople = false;
            exercise = false;*/
        }
    }

    public static DayClass getInstance() {
        if (singletonDay == null) {
            singletonDay = new DayClass();
        }
        return singletonDay;
    }

}
