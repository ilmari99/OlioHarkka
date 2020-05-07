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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class FriendsFragment extends Fragment {

    // Declaring variables for UI components and values
    private String friendsText;
    private int rating, time, friendsNumber;
    private SeekBar friendSlider;
    private EditText friendsTextInput;
    private Bundle dataBundle;
    private ActivityClass friends;
    private DayClass day;
    private TextView friendstext;

    public FriendsFragment() {
        // Required empty public constructor
    }

    public FriendsFragment(int friends,String tempNotes){
        friendsNumber = friends;
        friendsText = tempNotes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        friendSlider = v.findViewById(R.id.friendsSeekBar);
        friendsTextInput = v.findViewById(R.id.notesTextInputFriends);
        Button saveActivityButton = v.findViewById(R.id.saveActivityButton);
        Button deleteButton = v.findViewById(R.id.deleteActivityButton);
        friendstext = v.findViewById(R.id.friendsTextView);

        if(friendsText != null){
            setFriendsData(friendsNumber,friendsText);
        }

        friendSlider.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                friendstext.setText(String.valueOf(getFriendsNumber()));
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
            friendsText = getFriendsText();
            friendsNumber = getFriendsNumber();
            rating = dataBundle.getInt("rating");
            time = dataBundle.getInt("time");

            friends = new Friends(rating, time, friendsNumber, friendsText);

            day = DayScreen.getDayObject();
            day.doneActivities.add(friends);
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
                day = DayScreen.getDayObject();
                day.doneActivities.remove(friends);
                getActivity().finish();
                System.out.println("Called finish method");
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

    public void setFriendsData(int numberoffriends,String notes){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.subjects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        friendSlider.setProgress(numberoffriends);

        friendstext.setText(String.valueOf(numberoffriends));
        friendsTextInput.setText(notes);
    }
}
