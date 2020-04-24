package com.example.olioht;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */

public class FriendsFragment extends Fragment {

    // Declaring variables for UI components and values
    private String friendsText;
    private Button saveActivityButton;
    private int rating, time, friendsNumber;
    private SeekBar friendSlider;
    private TextView friendNumberChanging;
    private EditText friendsTextInput;
    private Bundle dataBundle;
    private ActivityClass friends;
    private DayClass day;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        friendSlider = v.findViewById(R.id.friendsSeekBar);
        friendsTextInput = v.findViewById(R.id.notesTextInput);
        saveActivityButton = v.findViewById(R.id.saveActivityButton);
        friendNumberChanging = v.findViewById(R.id.friendsTextView);

        friendSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    friendNumberChanging.setText(progress + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }));

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dataBundle = ((ActivityScreen) getActivity()).sendDataToFragment();
            friendsText = getFriendsText();
            friendsNumber = getFriendsNumber();
            rating = dataBundle.getInt("rating");
            time = dataBundle.getInt("time");

            friends = new Friends(rating, time, friendsNumber, friendsText);

            day = DayScreen.getDayObject();
            day.doneActivities.add(friends);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public int getFriendsNumber() {
        friendsNumber = friendSlider.getProgress();
        return friendsNumber;
    }

    public String getFriendsText() {
        friendsText = friendsTextInput.getText().toString();
        return friendsText;
    }
}
