package com.example.adriangracia.studybuddy.activities;

import android.support.v4.app.Fragment;
import android.view.Menu;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.fragment.CreateEventFragment;
import com.example.adriangracia.studybuddy.SingleFragmentActivity;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class CreateEvent extends SingleFragmentActivity {

    @Override
    public Fragment getFragment() {
        return new CreateEventFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
