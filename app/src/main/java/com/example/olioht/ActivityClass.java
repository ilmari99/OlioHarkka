package com.example.olioht;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Array;
/*ActivityClassista tulee fragmentti ActivityScreeniin
ActivityScreenin siis pitäisi olla interface, mutta "lisäkysymykset" tulevat fragmenttina ActivityScreenin päälle
Entä, jos aukaisee fragmentin, niin pystyykö silloin samaan aikaan muokata interfacessa olevia tietoja?
 */

public class ActivityClass extends ActivityScreen {

    String rating, hours;

    String[] classnames = {"Drinking", "Studying", "Exercise", "Friends", "Relationship"};

    FragmentManager fragmentManager = getSupportFragmentManager();


 /*Activity luokat: Kun valitaan Activitetti ActivityScreenissä olevasta activityDropdownlistasta
    Lisätään ActivityScreeniin valitun aktiviteetin luokan kysymykset esim. valitaan aktiviteetti Studying --> Kysytään dropdown listalla opiskeltu aine, opiskeltiinko yksin, jne.
    TODO Luo jokaiselle AktivityClass luokan sisäluokalle fragmentti, joka näytetään, kun käyttäjä valitsee aktiviteetin
    Kun fragmentti avataan, täytyy activiteetin tietojen silti näkyä ja olla muokattavissa.
     */
}

    class Drinking extends ActivityClass {
        String name = "Drinking";
        String doses;
        EditText dosesText;
        Boolean passedOut;
        CheckBox passedOutCheck;

        public Drinking() {
            Intent intent = this.getIntent();
            if (intent != null) {
                dosesText = findViewById(R.id.dosesInput);
                passedOutCheck = findViewById(R.id.passedOutCheck);


                //Annetaan muutoksesta doses -arvo
                dosesText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        doses = String.valueOf(dosesText.getText());
                    }
                });

                //Annetaan muutoksesta passedOut -arvo
                passedOutCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        passedOut = passedOutCheck.isChecked();
                    }
                });
            }

        }

        String getAmountOfDoses() {
            return doses;
        }

        Boolean getPassedOut() {
            return passedOut;
        }

        String[] getAttributes() {//todo miksi heittää näistä kaikista getAttributes funktioista nullikan
            String[] attributes = {day.date, name, rating, hours, doses, passedOut.toString()};
            return attributes;
        }


    }

    class Studying extends ActivityClass {
        String name = "Studying";
        Boolean alone;
        String subject;
        private Spinner subjectSpinner;
        private CheckBox studiedAlone;
        Activity act;

        public Studying() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = new Intent(this,Studying.class);
            startActivity(intent);

            studyFrag = new StudyFragment();
        }


        Boolean getStudiedAlone() {
            return alone;
        }

        public void setSubject(String tempSubject){
            subject = tempSubject;
            System.out.println("Subject set succesfully:"+subject);
        }

        public void setAlone(Boolean tempAlone)
        {
            alone = tempAlone;
            System.out.println("Alone set succesfully:"+alone);
        }

        public String[] getAttributes() {
            String[] attributes = new String[] {day.date, name, rating, hours, subject, alone.toString()};
            return attributes;
        }
    }


    class Exercise extends ActivityClass {
        String name = "Exercise";
        String type;
        Spinner exerciseType;

        public Exercise() {
            Intent intent = this.getIntent();
            if (intent != null) {
                exerciseType = findViewById(R.id.exerciseTypeDropdown);
                exerciseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type = String.valueOf(exerciseType.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

        }

        String[] getAttributes() {
            String[] attributes = {day.date, name, rating, hours, type};
            return attributes;
        }
    }

    class Friends extends ActivityClass {
        String name = "Friends";
        String numberofpeople;
        String activity;
        Spinner activityWithfriends;
        EditText amountofpeopleInput;

        Friends() {
            Intent intent = this.getIntent();
            if (intent != null) {
                activityWithfriends = findViewById(R.id.activityWithFriendsDropdown);
                amountofpeopleInput = findViewById(R.id.friendCountInput);
                activityWithfriends.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        activity = String.valueOf(activityWithfriends.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                amountofpeopleInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        numberofpeople = String.valueOf(amountofpeopleInput.getText());
                    }
                });
            }
        }

        String[] getAttributes() {
            String[] attributes = {day.date, name, rating, hours, numberofpeople, activity};
            return attributes;
        }
    }

    class Relationship extends ActivityClass {
        String name = "Relationship";
        Boolean had_sex;
        String activity;
        Spinner relationshipActivity;
        CheckBox hadsexBox;

        Relationship() {
            Intent intent = this.getIntent();
            if (intent != null) {
                relationshipActivity = findViewById(R.id.relationshipActivityDropdown);
                hadsexBox = findViewById(R.id.hadSexBox);
                relationshipActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        activity = String.valueOf(relationshipActivity.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                hadsexBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        had_sex = hadsexBox.isChecked();
                    }
                });
            }
        }

        String[] getAttributes() {
            String[] attributes = {day.date, name, rating, hours, activity, had_sex.toString()};
            return attributes;
        }
    }

