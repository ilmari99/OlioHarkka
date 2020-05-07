package com.example.olioht;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox had_sex;
    private Boolean sex;

    public RelationshipFragment() {
        // Required empty public constructor
    }

    public RelationshipFragment(String act,Boolean tempSex, String notes){
        relShipActivity = act;
        sex = tempSex;
        relShipNotes = notes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_relationship, container, false);
        relShipDropdown = v.findViewById(R.id.relShipSpinner);
        relShipNotesBox = v.findViewById(R.id.notesTextInputRelationship);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);
        deleteButton = v.findViewById(R.id.deleteActivityButton);
        had_sex = v.findViewById(R.id.hadSexCheck);

        if(relShipNotes != null){
            setRelationshipData(relShipActivity,sex,relShipNotes);
        }

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                relShipActivity = getRelShipActivity();
                relShipNotes = getRelShipNotes();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                relationship = new Relationship(rating, time, relShipActivity, getSexBool(), relShipNotes);

                day = DayScreen.getDayObject();
                day.doneActivities.add(relationship);
                day.createDayHash();
                day.printAllDayData();
                final Toast toast = Toast.makeText(getContext(),"Activity saved", Toast.LENGTH_SHORT);
                toast.show();
                relShipActivity = null;
                relShipNotes = null;
                had_sex = null;
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

    public Boolean getSexBool(){return had_sex.isChecked();}

    public void setRelationshipData(String act,boolean sex,String notes){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.relationshipActivity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relShipDropdown.setAdapter(adapter);
        if (act != null) {
            int spinnerPosition = adapter.getPosition(act);
            relShipDropdown.setSelection(spinnerPosition);
        }

        had_sex.setSelected(sex);
        relShipNotesBox.setText(notes);
    }
}
