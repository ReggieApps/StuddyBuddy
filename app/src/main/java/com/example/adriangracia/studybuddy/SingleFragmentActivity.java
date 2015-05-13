package com.example.adriangracia.studybuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.app.ToolbarActionBar;

/**
 * Created by rgpaul on 4/20/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_container);

        Fragment aFrag = getSupportFragmentManager().findFragmentById(R.id.frame_layout_fragment_container);

        if(aFrag == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_fragment_container, getFragment()).commit();
        }
    }

    public abstract Fragment getFragment();
}
