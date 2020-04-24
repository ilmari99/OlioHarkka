package com.example.olioht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyFragment extends Fragment {
    private Spinner subjectSpinner;
    private CheckBox studiedAlone;
    private String subject;
    private Boolean alone;
    private Activity activity;
    private View view;

    public StudyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_study, container, false);
        System.out.println("onCreateView succesful");
        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        System.out.println("onAttach succesful"+activity);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subjectSpinner = view.findViewById(R.id.studySubjectDropdown);
        studiedAlone = view.findViewById(R.id.studiedAloneBox);

        //Annetaan muutoksesta -subject arvo
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject = String.valueOf(subjectSpinner.getSelectedItem());
                System.out.println(subject);
                ((Studying) getActivity()).setSubject(subject); //This line gives the exeption
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Annetaan muutoksesta alone -arvo
        studiedAlone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                alone = studiedAlone.isChecked();
                ((Studying) activity).setAlone(alone);
            }
        });
    }
}


