package com.example.adriangracia.studybuddy.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.adapters.DrawerListAdapter;
import com.example.adriangracia.studybuddy.objects.Information;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robby on 5/15/2015.
 */
public class NavigationDrawerFragment extends Fragment{

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private DrawerListAdapter drawerListAdapter;

    private ImageView profPic;
    private TextView profName;

    private View containerView;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        profPic = (ImageView) v.findViewById(R.id.prof_pic);
        profName = (TextView) v.findViewById(R.id.facebook_name);
        recyclerView = (RecyclerView) v.findViewById(R.id.drawer_list);

        drawerListAdapter = new DrawerListAdapter(getActivity(), getData());
        recyclerView.setAdapter(drawerListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try{
            Picasso.with(getActivity().getApplicationContext()).load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId() + "/picture?type=large").into(profPic);

            profName.append("Welcome " + Profile.getCurrentProfile().getFirstName() + " " + Profile.getCurrentProfile().getLastName() + "!");
        } catch (NullPointerException e){
            Log.e("Profile Error", "Null profile info on drawer");
        }
        return v;
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {android.R.drawable.ic_input_add, android.R.drawable.ic_input_add,android.R.drawable.ic_input_add, android.R.drawable.ic_delete};
        String[] titles = {"Button 1", "Button 2", "Button 3", "Logout", };

        for(int i = 0; i<titles.length && i<icons.length; i++){
            Information tempInfo = new Information();
            tempInfo.title = titles[i];
            tempInfo.iconId = icons[i];
            data.add(tempInfo);
        }

        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close){
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onDrawerOpened(View drawerView) {
                if(!mUserLearnedDrawer){
                    super.onDrawerOpened(drawerView);
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }
        };

        if(!mUserLearnedDrawer&&!mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, defaultValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}
