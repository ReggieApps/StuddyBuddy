package com.example.adriangracia.studybuddy.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.fragment.AttendInformationFragment;
import com.example.adriangracia.studybuddy.SingleFragmentActivity;


public class AttendInformation extends SingleFragmentActivity {

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
