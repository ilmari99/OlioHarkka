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
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */

public class DrinkingFragment extends Fragment {

    // Declaring variables for UI components and values
    private int doses, rating, time;
    private Boolean passedOut;
    private SeekBar doseSlider;
    private TextView dosesText;
    private CheckBox passedOutCheckBox;
    private Button saveActivityButton;
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
        passedOutCheckBox = v.findViewById(R.id.passedOutCheck);

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
                passedOut = getPassedOutBool();
                rating = dataBundle.getInt("rating");
                time = dataBundle.getInt("time");

                drinking = new Drinking(rating, time, doses, passedOut);

                day = DayScreen.getDayObject();
                day.doneActivities.add(drinking);
                day.createDayHash();
                day.printAllDayData();
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
}
