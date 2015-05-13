package com.example.adriangracia.studybuddy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.SingleFragmentActivityDrawer;
import com.example.adriangracia.studybuddy.fragment.ListActivityFragment;


public class ListActivity extends SingleFragmentActivityDrawer {
    //happy holidays!!
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fragment getFragment() {
        return new ListActivityFragment();
    }

}
