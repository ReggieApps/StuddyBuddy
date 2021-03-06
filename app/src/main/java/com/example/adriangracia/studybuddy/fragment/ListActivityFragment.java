package com.example.adriangracia.studybuddy.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.adriangracia.studybuddy.activities.AttendInformation;
import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.activities.CreateEvent;
import com.example.adriangracia.studybuddy.factories.JSONParser;
import com.example.adriangracia.studybuddy.objects.EventObject;
import com.example.adriangracia.studybuddy.objects.TimeObject;
import com.software.shell.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class ListActivityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    final public String information = "information";

    public Spinner specifySubject;
    public ActionButton createEventButton;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<EventObject> eventList = new ArrayList<>();

    JSONParser jsonParser = new JSONParser();

    ListView test;
    ArrayAdapter adapter;

    private static String url_get_event = "http://isumobileclub.ilstu.edu/get_events.php";

    private ProgressDialog pDialog;

    AsyncTask task;
    private Toolbar toolbar;

    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_activity, container, false);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        test = (ListView) v.findViewById(R.id.listView);

        toolbar = (Toolbar) v.findViewById(R.id.app_bar_spinner);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) getActivity().findViewById(R.id.drawer_layout), toolbar);

        task = new CreateNewProduct(this) {
            @Override
            public void onResponseReceived() {
            }
        }.execute();


        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent in = new Intent(getActivity(), AttendInformation.class);

                EventObject clickedEvent = eventList.get(position);

                String[] testInformation = {clickedEvent.getTo().toString(), clickedEvent.getLocation(), clickedEvent.getTitle(), clickedEvent.getEndTime().toString(), clickedEvent.getDescription(), clickedEvent.getSubject(), clickedEvent.getPeopleGoing(), clickedEvent.getNumPeopleGoing()};
                in.putExtra(information, testInformation);
                startActivity(in);
            }
        });

        createEventButton = (ActionButton) v.findViewById(R.id.Button2);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), CreateEvent.class);

                startActivity(in);
            }
        });

        createEventButton.setButtonColor(getActivity().getResources().getColor(R.color.accent_color));
        createEventButton.setButtonColorPressed(getActivity().getResources().getColor(R.color.accent_color));
        createEventButton.setRippleEffectEnabled(true);

        specifySubject = (Spinner) v.findViewById(R.id.spinner);

        //I think this is the cause of the Async progress not closing on startup sometimes.
        specifySubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    new CreateNewProduct(position) {
                        @Override
                        public void onResponseReceived() {
                            if (position == 1) {
                            } else {
                                String selectedSubj = getResources().getStringArray(R.array.class_array)[position];
                                for (int i = 0; i < eventList.size(); i++) {
                                    if (!eventList.get(i).getSubject().equals(selectedSubj)) {
                                        list.remove(list.indexOf(eventList.get(i).getTitle()));
                                        eventList.remove(i);
                                        i--;
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        return v;
    }

    @Override
    public void onRefresh() {
        new CreateNewProduct() {
            @Override
            public void onResponseReceived() {
                if (specifySubject.getSelectedItemPosition() == 1 || specifySubject.getSelectedItemPosition() == 0) {

                } else {
                    String selectedSubj = getResources().getStringArray(R.array.class_array)[specifySubject.getSelectedItemPosition()];
                    for (int i = 0; i < eventList.size(); i++) {
                        if (!eventList.get(i).getSubject().equals(selectedSubj)) {
                            list.remove(list.indexOf(eventList.get(i).getTitle()));
                            eventList.remove(i);
                            i--;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }

    abstract class CreateNewProduct extends AsyncTask<String, String, String> implements Handler.Callback {

        ListActivityFragment caller;
        int position;

        public CreateNewProduct() {
        }

        public CreateNewProduct(ListActivityFragment caller) {
            this.caller = caller;
        }

        public CreateNewProduct(int position) {
            this.position = position;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(!mSwipeRefreshLayout.isRefreshing()) {
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Getting Events...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
        }

        protected String doInBackground(String... args) {
            JSONArray jsonArr = jsonParser.getJSONFromUrl(url_get_event);
            for (int n = 0; n < jsonArr.length(); n++) {
                try {
                    JSONObject object = jsonArr.getJSONObject(n);
                    if (!list.contains(object.getString("title"))) {
                        String[] time = object.getString("time").split(":");
                        time[1] = time[1].substring(0, 2);

                        String[] endTime = object.getString("end_time").split(":");
                        endTime[1] = endTime[1].substring(0, 2);

                        String longPeopleStr = object.getString("attend_ids");
                        int length = 1;

                        for (int i = 0; i < longPeopleStr.length(); i++){
                            if(longPeopleStr.charAt(i) == ',')length++;
                        }

                        EventObject tempEven = new EventObject(object.getString("title"), object.getString("location"), object.getString("description"), object.getString("subject"), new TimeObject(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1])), new TimeObject(Integer.parseInt(time[0]), Integer.parseInt(time[1])), longPeopleStr, ""+length);
                        eventList.add(tempEven);
                        list.add(object.getString("title"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            if(mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }

            test.setAdapter(adapter);
            onResponseReceived();
            pDialog.dismiss();
        }

        public abstract void onResponseReceived();

        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }
}

