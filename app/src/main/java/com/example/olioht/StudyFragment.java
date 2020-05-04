package com.example.olioht;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 */

public class StudyFragment extends Fragment {

    // Declaring variables for UI components and values
    private String subject;
    private int rating, time;
    private Boolean withFriends;
    private Bundle dataBundle;
    private Spinner subjectSpinner;
    private CheckBox friendsCheckBox;
    private Button saveActivityButton;
    private ActivityClass studying;
    private DayClass day;

    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);
        subjectSpinner = v.findViewById(R.id.subjectInput);
        friendsCheckBox = v.findViewById(R.id.withFriendsCheck);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                subject = getSubject();
                withFriends = getFriendsBool();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                studying = new Studying(rating, time, subject, withFriends);

                day = DayScreen.getDayObject();
                day.doneActivities.add(studying);
                day.createDayHash();
                day.printAllDayData(); //These are not necessary but demonstrate how saved data works
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public boolean getFriendsBool() { return friendsCheckBox.isChecked(); }

    public String getSubject() {
        return subjectSpinner.getSelectedItem().toString();
    }
}


