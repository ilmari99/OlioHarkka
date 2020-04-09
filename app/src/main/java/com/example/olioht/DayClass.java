package com.example.olioht;

public class DayClass extends DayScreen{
    /*
    In this class we only have the attributes of one day. Changing and setting of values happens in DayScreen.
    In the doneActivities -array we have ActivityClass -objects.
    Saa nähdä pystyykö tollasta olio -arrayta toisen olion sisällä tallentaa tekstitiedostoon.
     */

    String date;
    int sleeptime,socialTime,dayRating;
    Boolean newExperience,newPeople,exercise;           //Tieto exercisesta haetaan Aktivitysta
    ActivityClass[] doneActitivities = new ActivityClass[10];                   //Array jossa on ActivityClass Olioita
    //Ei pysty siis lisäämään yli kymmentä aktiviteettia päivässä


    DayClass(){
        String date = MainActivity.getDate();
    }

}
