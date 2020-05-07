package com.example.olioht;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

//#######################################THIS FRAGMENT CONTAINS THE COMMENTS FOR FRAGMENTS. THEY ARE ALL THE SAME########################

public class StudyFragment extends Fragment {

    // Declaring variables for UI components and values
    private String subject,notes;
    private int rating, time;
    private Boolean withFriends;
    private Bundle dataBundle;
    private EditText notesBox;
    private Spinner subjectSpinner;
    private CheckBox friendsCheckBox;
    private ActivityClass studying;
    private DayClass day = DayScreen.getDayObject();;
    private Boolean saved = false;

    public StudyFragment() {
        // Required empty public constructor
    }

    //This is called if we already have data about the day
    public StudyFragment(String subj,boolean wFriends,String tempNotes){
        subject = subj;
        withFriends = wFriends;
        notes = tempNotes;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);
        subjectSpinner = v.findViewById(R.id.subjectInput);
        friendsCheckBox = v.findViewById(R.id.withFriendsCheck);
        Button saveActivityButton = v.findViewById(R.id.saveActivityButton);
        Button deleteButton = v.findViewById(R.id.deleteActivityButton);
        notesBox = v.findViewById(R.id.notesTextInputStudying);

        if(subject != null){
            setStudyData(subject,withFriends,notes);
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
                day = DayScreen.getDayObject();
                day.doneActivities.remove(studying);
                getActivity().finish();

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

    //This sets the components to the values found in storage.
    public void setStudyData(String subj,boolean wFriends,String notes){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.subjects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        if (subj != null) {
            int spinnerPosition = adapter.getPosition(subj);
            subjectSpinner.setSelection(spinnerPosition);
        }

        friendsCheckBox.setSelected(wFriends);
        notesBox.setText(notes);
    }
}


