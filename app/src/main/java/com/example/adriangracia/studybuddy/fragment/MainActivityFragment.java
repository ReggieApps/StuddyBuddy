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
import com.facebook.Profile;
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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_main_activity, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        if (AccessToken.getCurrentAccessToken() != null) {
            Intent i = new Intent(getActivity(), ListActivity.class);
            startActivity(i);
        }

        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("public_profile");
        // Other app specific specialization
        loginButton.setFragment(this);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getActivity(), "Logged in ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
                    }
                });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Intent i = new Intent(getActivity(), ListActivity.class);
        startActivity(i);
    }
}
