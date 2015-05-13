package com.example.adriangracia.studybuddy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.adriangracia.studybuddy.SingleFragmentActivity;
import com.example.adriangracia.studybuddy.fragment.MainActivityFragment;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Robby on 5/12/2015.
 */
public class MainActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public Fragment getFragment() {
        return new MainActivityFragment();
    }
}
