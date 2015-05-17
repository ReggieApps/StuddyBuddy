package com.example.adriangracia.studybuddy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.activities.ListActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;



/**
 * Created by Robby on 5/12/2015.
 */
public class MainActivityFragment extends Fragment {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Toolbar toolbar;
    private ProfileTracker profileTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_activity, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        if(Profile.getCurrentProfile() != null){
            Intent i = new Intent(getActivity(), ListActivity.class);
            startActivity(i);
        }

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        // Other app specific specialization
        loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "Logged in ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), ListActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

}
