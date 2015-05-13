package com.example.adriangracia.studybuddy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.adriangracia.studybuddy.SingleFragmentActivity;
import com.example.adriangracia.studybuddy.fragment.MainActivityFragment;

/**
 * Created by Robby on 5/12/2015.
 */
public class MainActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        return new MainActivityFragment();
    }
}
