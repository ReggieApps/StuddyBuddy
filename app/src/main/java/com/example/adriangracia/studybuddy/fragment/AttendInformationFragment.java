package com.example.adriangracia.studybuddy.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.activities.AttendInformation;
import com.example.adriangracia.studybuddy.dialogs.PeopleGoingDialogFragment;
import com.example.adriangracia.studybuddy.dialogs.TimePickerDialogFragment;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class AttendInformationFragment extends Fragment {

    private Toolbar toolbar;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.attend_information, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            String[] value = extras.getStringArray("information");


            TextView timeAppend = (TextView)v.findViewById(R.id.editTime);
            TextView placeAppend = (TextView)v.findViewById(R.id.editPlace);
            TextView titleAppend = (TextView)v.findViewById(R.id.title);
            TextView durationAppend = (TextView)v.findViewById(R.id.editDuration);
            TextView descriptionAppend = (TextView)v.findViewById(R.id.editDescription);
            TextView subjectAppend = (TextView) v.findViewById(R.id.editSubject);
            TextView goingAppend = (TextView) v.findViewById(R.id.edit_people_going);
            timeAppend.append("  " + value[0]);
            placeAppend.append("  " + value[1]);
            titleAppend.append("  " + value[2]);
            durationAppend.append("  " + value[3]);
            descriptionAppend.append("  " + value[4]);
            subjectAppend.append(" " + value[5]);
            goingAppend.append(value[7]);


        }

        Button finalizeAttend = (Button) v.findViewById(R.id.attend_button);
        finalizeAttend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Attend button pressed.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        TextView peopleGoingClick = (TextView) v.findViewById(R.id.edit_people_going);
        peopleGoingClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeopleGoingDialogFragment going = new PeopleGoingDialogFragment();
                going.setTargetFragment(AttendInformationFragment.this,0);
                going.show(getActivity().getSupportFragmentManager(), "AttendInformationFragment");
            }
        });


        peopleGoingClick.setPaintFlags(peopleGoingClick.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        return v;
    }


}
