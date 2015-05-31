package com.example.adriangracia.studybuddy.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.adapters.DrawerListAdapter;
import com.example.adriangracia.studybuddy.objects.Information;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robby on 5/23/2015.
 */
public class PeopleGoingDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private DrawerListAdapter drawerListAdapter;
    private RecyclerView recyclerView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_people_going,null);

        recyclerView = (RecyclerView) v.findViewById(R.id.people_going_list);

        drawerListAdapter = new DrawerListAdapter(getActivity(), getData());
        recyclerView.setAdapter(drawerListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setMessage("People going to group meeting")
                .setCancelable(false)
                .setNegativeButton("ok",this)
                .create();
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {};
        String[] titles = {};

        for(int i = 0; i<titles.length && i<icons.length; i++){
            Information tempInfo = new Information();
            tempInfo.title = titles[i];
            tempInfo.iconId = icons[i];
            data.add(tempInfo);
        }

        return data;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


}
