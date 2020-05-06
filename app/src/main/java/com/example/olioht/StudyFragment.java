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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * A simple {@link Fragment} subclass.
 */

public class StudyFragment extends Fragment {

    // Declaring variables for UI components and values
    private String subject,notes;
    private int rating, time;
    private Boolean withFriends;
    private Bundle dataBundle;
    private EditText notesBox;
    private Spinner subjectSpinner;
    private CheckBox friendsCheckBox;
    private Button saveActivityButton,deleteButton;
    private ActivityClass studying;
    private DayClass day = DayScreen.getDayObject();;
    private Boolean saved = false;

    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);
        subjectSpinner = v.findViewById(R.id.subjectInput);
        friendsCheckBox = v.findViewById(R.id.withFriendsCheck);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);
        deleteButton = v.findViewById(R.id.deleteActivityButton);
        notesBox = v.findViewById(R.id.notesTextInputStudying);
        SeekBar actRating = v.findViewById(R.id.activityRatingSeekBar);
        SeekBar actTime = v.findViewById(R.id.activityTimeSeekBar);

        if (day.doneActivities.contains(studying)) {
            int index = day.doneActivities.indexOf(studying);
            ActivityClass oldStudying = day.doneActivities.get(index);
            actRating.setProgress(oldStudying.activityRating);
            actTime.setProgress(oldStudying.activityTime);
        }

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                saved = true;
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                subject = getSubject();
                withFriends = getFriendsBool();
                notes = getFriendsNotes();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                studying = new Studying(rating, time, subject, withFriends, notes);

                day.doneActivities.add(studying);
                day.createDayHash();
                day.printAllDayData(); //These are not necessary but demonstrate how saved data works
                final Toast toast = Toast.makeText(getContext(),"Activity saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                getActivity().finish();
                System.out.println("Called finish method");
            }
        });
            // Inflate the layout for this fragment
        return v;
    }

    public boolean getFriendsBool() { return friendsCheckBox.isChecked(); }

    public String getSubject() {
        return subjectSpinner.getSelectedItem().toString();
    }

    public DayClass getDayObject() {
        return day;
    }

    public String getFriendsNotes(){return String.valueOf(notesBox.getText());}

    public Boolean getSaved(){return saved;}
}


