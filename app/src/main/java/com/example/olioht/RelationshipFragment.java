package com.example.olioht;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */

public class RelationshipFragment extends Fragment {

    // Declaring variables for UI components and values
    private String relShipActivity, relShipNotes;
    private int rating, time;
    private Spinner relShipDropdown;
    private EditText relShipNotesBox;
    private Button saveActivityButton,deleteButton;
    private Bundle dataBundle;
    private ActivityClass relationship;
    private DayClass day;

    public RelationshipFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_relationship, container, false);
        relShipDropdown = v.findViewById(R.id.relShipSpinner);
        relShipNotesBox = v.findViewById(R.id.notesTextInputRelationship);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);
        deleteButton = v.findViewById(R.id.deleteActivityButton);

        // TODO Make UI elements show old data if its found

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                relShipActivity = getRelShipActivity();
                relShipNotes = getRelShipNotes();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                relationship = new Relationship(rating, time, relShipActivity, relShipNotes);

                day = DayScreen.getDayObject();
                day.doneActivities.add(relationship);
                day.createDayHash();
                day.printAllDayData();
                final Toast toast = Toast.makeText(getContext(),"Activity saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                getActivity().finish();
                final Toast toast = Toast.makeText(getContext(),"Activity deleted", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public String getRelShipActivity() {
        relShipActivity = relShipDropdown.getSelectedItem().toString();
        return relShipActivity;
    }

    public String getRelShipNotes() {
        relShipNotes = relShipNotesBox.getText().toString();
        return relShipNotes;
    }
}
