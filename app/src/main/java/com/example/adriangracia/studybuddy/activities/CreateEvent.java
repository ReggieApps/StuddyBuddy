package com.example.adriangracia.studybuddy.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.adriangracia.studybuddy.SingleFragmentActivityDrawers;
import com.example.adriangracia.studybuddy.fragment.CreateEventFragment;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class CreateEvent extends SingleFragmentActivityDrawers {

    @Override
    public Fragment getFragment() {
        return new CreateEventFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
