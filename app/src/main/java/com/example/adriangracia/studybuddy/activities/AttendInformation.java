package com.example.adriangracia.studybuddy.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.adriangracia.studybuddy.SingleFragmentActivityDrawers;
import com.example.adriangracia.studybuddy.fragment.AttendInformationFragment;


public class AttendInformation extends SingleFragmentActivityDrawers {

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

    @Override
    public Fragment getFragment() {
        return new AttendInformationFragment();
    }
}
