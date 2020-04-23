package com.example.olioht;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */

public class RelationshipFragment extends Fragment {

    // Declaring variables for UI components and values
    private Button saveActivityButton;
    private Bundle dataBundle;
    private ActivityClass friends;
    private DayClass day;

    public RelationshipFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_study, container, false);


        // Inflate the layout for this fragment
        return v;
    }
}
