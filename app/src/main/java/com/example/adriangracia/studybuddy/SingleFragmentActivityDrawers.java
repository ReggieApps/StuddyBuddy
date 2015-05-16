package com.example.adriangracia.studybuddy;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.Profile;

/**
 * Created by jonathanmitchell on 4/24/15.
 */
public abstract class SingleFragmentActivityDrawers extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView profPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity_with_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Fragment aFrag = getSupportFragmentManager().findFragmentById(R.id.frame_layout_drawer_content_frame);

        if (aFrag == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_drawer_content_frame, getFragment()).commit();
        }

        profPic = (ImageView) findViewById(R.id.prof_pic);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public ImageView getProfPic(){
        return profPic;
    }


    public abstract Fragment getFragment();
}
