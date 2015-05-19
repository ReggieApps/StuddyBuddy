package com.example.adriangracia.studybuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jonathanmitchell on 4/24/15.
 */
public abstract class SingleFragmentActivityDrawers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity_with_drawer);

        Fragment aFrag = getSupportFragmentManager().findFragmentById(R.id.frame_layout_drawer_content_frame);

        if (aFrag == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_drawer_content_frame, getFragment()).commit();
        }

    }

    public abstract Fragment getFragment();
}
