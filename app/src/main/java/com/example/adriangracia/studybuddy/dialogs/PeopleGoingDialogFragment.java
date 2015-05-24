package com.example.adriangracia.studybuddy.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.example.adriangracia.studybuddy.R;

/**
 * Created by Robby on 5/23/2015.
 */
public class PeopleGoingDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_people_going,null);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setMessage("People going to group meeting")
                .setCancelable(false)
                .setNegativeButton("ok",this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


}
