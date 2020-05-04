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

/**
 * A simple {@link Fragment} subclass.
 */

public class ExerciseFragment extends Fragment {

    // Declaring variables for UI components and values
    private String exercise, notes;
    private EditText notesBox;
    private Spinner exerciseType;
    private Button saveActivityButton;
    private int rating, time;
    private Bundle dataBundle;
    private ActivityClass exercising;
    private DayClass day;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        exerciseType = v.findViewById(R.id.sportsTypeInput);
        notesBox = v.findViewById(R.id.notesTextInputExercise);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
            dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
            exercise = getExercise();
            notes = getNotes();
            rating = dataBundle.getInt("rating");
            time = dataBundle.getInt("time");

            System.out.print(notes);

            exercising = new Exercise(rating, time, exercise, notes);

            day = DayScreen.getDayObject();
            day.doneActivities.add(exercising);
            day.createDayHash();
            day.printAllDayData();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public String getExercise() {
        exercise = exerciseType.getSelectedItem().toString();
        return exercise;
    }

    public String getNotes() {
        notes = notesBox.getText().toString();
        return notes;
    }
}
