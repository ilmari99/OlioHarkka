package com.example.olioht;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */

public class StudyFragment extends Fragment {

    private String subject;
    private Boolean withFriends;
    private int rating, time;
    private EditText subjectText;
    private CheckBox friendsCheckBox;
    private Button saveActivityButton;
    private Bundle dataBundle;
    private ActivityClass studying;
    private DayClass day;

    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);
        subjectText = v.findViewById(R.id.subjectInput);
        friendsCheckBox = v.findViewById(R.id.withFriendsCheck);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                subject = getSubject();
                withFriends = getFriendsBool();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                studying = new Studying(subject, rating, time, withFriends);

                day = DayScreen.getDayObject();
                day.doneActivities.add(studying);

                System.out.println("#### "+subject+" ## "+withFriends+" ## "+rating+" ## "+time+" ###");
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public boolean getFriendsBool() {
        if (friendsCheckBox.isChecked() == false) {
            return false;
        }
        else {
            return true;
        }
    }

    public String getSubject() {
        return subjectText.getText().toString();
    }
}


