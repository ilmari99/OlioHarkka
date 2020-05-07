package com.example.olioht;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ExerciseFragment extends Fragment {

    // Declaring variables for UI components and values
    private String exercise, notes;
    private EditText notesBox;
    private Spinner exerciseType;
    private int rating, time;
    private Bundle dataBundle;
    private ActivityClass exercising;
    private DayClass day;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    public ExerciseFragment(String type, String tempNotes){
        exercise = type;
        notes = tempNotes;
}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        exerciseType = v.findViewById(R.id.sportsTypeInput);
        notesBox = v.findViewById(R.id.notesTextInputExercise);
        Button saveActivityButton = v.findViewById(R.id.saveActivityButton);
        Button deleteButton = v.findViewById(R.id.deleteActivityButton);

        if(exercise != null){
            setExerciseData(exercise,notes);
        }

        // TODO Make UI elements show old data if its found

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
            dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
            exercise = getExercise();
            notes = getNotes();
            rating = dataBundle.getInt("rating");
            time = dataBundle.getInt("time");

            exercising = new Exercise(rating, time, exercise, notes);

            day = DayScreen.getDayObject();
            day.doneActivities.add(exercising);
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
                System.out.println("Called finish method");
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

    public void setExerciseData(String type, String notes){
        Context context = getContext();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.exerciseTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseType.setAdapter(adapter);
        if (type != null) {
            int spinnerPosition = adapter.getPosition(type);
            exerciseType.setSelection(spinnerPosition);
        }

        notesBox.setText(notes);
    }

}
