package com.example.olioht;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */

public class StudyFragment extends Fragment {

    private String subject;
    private EditText subjectText;
    private CheckBox friendsCheckBox;

    public StudyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);
        subjectText = v.findViewById(R.id.subjectInput);
        friendsCheckBox = v.findViewById(R.id.withFriendsCheck);

        subject = subjectText.getText().toString();

        // Inflate the layout for this fragment
        return v;
    }

    public boolean getFriendsBool() {
        if (friendsCheckBox.isChecked() == false) {
            return false;
        }
        else {
            return true;
        }
    }
}
