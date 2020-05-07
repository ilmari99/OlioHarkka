package com.example.olioht;


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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */

// TODO Make UI elements show old data if its found

public class DrinkingFragment extends Fragment {

    // Declaring variables for UI components and values
    private int doses, rating, time;
    private Boolean passedOut;
    private String notes;
    private EditText notesEditText;
    private SeekBar doseSlider;
    private TextView dosesText;
    private CheckBox passedOutCheckBox;
    private Button saveActivityButton,deleteButton;
    private Bundle dataBundle;
    private ActivityClass drinking;
    private DayClass day;


    public DrinkingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_drinking, container, false);

        doseSlider = v.findViewById(R.id.doseSeekBar);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);
        dosesText = v.findViewById(R.id.dosesTextChanging);
        deleteButton = v.findViewById(R.id.deleteActivityButton);
        passedOutCheckBox = v.findViewById(R.id.passedOutCheck);
        notesEditText = v.findViewById(R.id.notesTextInputDrinking);

        doseSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == seekBar.getMax()) {
                    dosesText.setText("Master drinker");
                }
                else {
                    dosesText.setText(String.valueOf(progress));
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
                doses = getDoses();
                notes = getDrinkingNotes();
                passedOut = getPassedOutBool();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                drinking = new Drinking(rating, time, doses, passedOut, notes);

                day = DayScreen.getDayObject();
                day.doneActivities.add(drinking);
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

    public int getDoses() {
        doses = doseSlider.getProgress();
        return doses;
    }

    public Boolean getPassedOutBool() { return passedOutCheckBox.isChecked(); }

    public String getDrinkingNotes(){return String.valueOf(notesEditText.getText());}
}
