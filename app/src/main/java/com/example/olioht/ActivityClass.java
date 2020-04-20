package com.example.olioht;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

    Fragment activityFragment;
    FragmentManager activityFragmentManager = getSupportFragmentManager();
    FragmentTransaction activityFragmentTransaction = activityFragmentManager.beginTransaction();
    String[] classnames = {"Drinking", "Studying", "Exercise", "Friends", "Relationship"};
    FragmentManager fragmentManager = getSupportFragmentManager();


 /*Activity luokat: Kun valitaan Activitetti ActivityScreenissä olevasta activityDropdownlistasta
    Lisätään ActivityScreeniin valitun aktiviteetin luokan kysymykset esim. valitaan aktiviteetti Studying --> Kysytään dropdown listalla opiskeltu aine, opiskeltiinko yksin, jne.
    TODO Luo jokaiselle AktivityClass luokan sisäluokalle fragmentti, joka näytetään, kun käyttäjä valitsee aktiviteetin
    Kun fragmentti avataan, täytyy activiteetin tietojen silti näkyä ja olla muokattavissa.
     */

    static class Drinking extends ActivityClass {
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

    static class Studying extends ActivityClass {
        String name = "Studying";
        Boolean alone;
        String subject;
        Spinner subjectSpinner;
        CheckBox studiedAlone;

        public Studying() {
            Intent intent = this.getIntent();
            if(intent != null) {
                System.out.println("Studying constructor toimii");
                subjectSpinner = findViewById(R.id.studySubjectDropdown);
                studiedAlone = findViewById(R.id.studiedAloneBox);

                //Annetaan muutoksesta -subject arvo
                subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        subject = String.valueOf(subjectSpinner.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                //Annetaan muutoksesta alone -arvo
                studiedAlone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        alone = studiedAlone.isChecked();
                    }
                });
            }
        }


        Boolean getStudiedAlone() {
            return alone;
        }

        String[] getAttributes() {
            String[] attributes = {day.date, name, rating, hours, subject, alone.toString()};
            return attributes;
        }
    }

    static class Exercise extends ActivityClass {
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

    static class Friends extends ActivityClass {
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

    static class Relationship extends ActivityClass {
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

}