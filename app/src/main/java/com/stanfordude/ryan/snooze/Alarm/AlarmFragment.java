package com.stanfordude.ryan.snooze.Alarm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.stanfordude.ryan.snooze.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */


/*
    This Fragment is used to display the listview containing alarm settings
 */


public class AlarmFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    //SharedPreference Keys
    public final static String SNOOZELENGTH = "SNOOZELENGTH", MINUTES = "Minutes", HOURS = "HOURS",
            STATE = "STATE", LENGTH = "LENGTH", FILE = "Snooze", LASTUSED = "LASTFRAGMENT";
    public SharedPreferences sp;


    public final static String TAG = "AlarmFragment";
    private ListView listView;
    private AlarmSettingAdapter alarmAdapter;


    private ArrayList<AlarmSetting> alarmSettingList = new ArrayList<AlarmSetting>();


    // the fragment initialization parameters for the Bundle
    private static final String LIST_VIEW = "AlarmFragmentListView";
    //the objects that recieve the values from the getArguments.getString("parameter")


    private OnFragmentInteractionListener mListener;




    /*
    Below is the fragment lifecycle methods
     */


    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //doesn't work, needs to load from sharedPreferences
            listView = (ListView) savedInstanceState.get(LIST_VIEW);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.fragment_alarm, container, false);
        Button addAlarmSetting = (Button) v.findViewById(R.id.add_alarm_setting);
        addAlarmSetting.setOnClickListener(this);
        //or getView().findViewById I'm not currently sure
        if (listView == null)
            listView = (ListView) v.findViewById(R.id.alarm_fragment_listview);
        alarmAdapter = new AlarmSettingAdapter(getActivity(), alarmSettingList);
        listView.setAdapter(alarmAdapter);
        listView.setOnItemClickListener(this);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sp = getActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        getSharedPreferences();


        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (alarmSettingList == null) return;

        for (int i = 0; i < alarmSettingList.size(); i++) {
            putInSharedPreferences(HOURS + i, alarmSettingList.get(i).getHours());
            putInSharedPreferences(MINUTES + i, alarmSettingList.get(i).getMinutes());
            putInSharedPreferences(SNOOZELENGTH + i, alarmSettingList.get(i).getSnoozeLength());
            // putInSharedPreferences(STATE+i, alarmSettingList.get(i).isSetOn());
            putInSharedPreferences(STATE + i, alarmSettingList.get(i).isSetOn());
        }
        putInSharedPreferences(LENGTH, alarmSettingList.size());


    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



        /*
        Below are the methods for event handling
        -Messy; Don't like it
         */

    public void getSharedPreferences() {

        if (sp == null) sp = getActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        int length = sp.getInt(LENGTH, 0), min, hours, snooze;

        boolean state;
        AlarmSetting temp;
        if (length == 0) return; //File has not been created, or their are no settings to restore

        for (int i = 0; i < length; i++) {
            min = sp.getInt(MINUTES + i, 0);
            hours = sp.getInt(HOURS + i, 0);
            snooze = sp.getInt(SNOOZELENGTH + i, 0);
            state = sp.getBoolean(STATE + i, false);
            temp = new AlarmSetting(hours, min, snooze, state);
            alarmSettingList.add(temp);
        }

    }


    public void putInSharedPreferences(String key, String s) {
        if (sp == null)
            sp = getActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, s);
        editor.commit();

    }

    public void putInSharedPreferences(String key, boolean s) {
        if (sp == null)
            sp = getActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, s);
        editor.commit();

    }

    public void putInSharedPreferences(String key, int s) {

        if (sp == null)
            sp = getActivity().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, s);
        editor.commit();
    }


    public void addAlarmSetting(AlarmSetting a) {
        alarmSettingList.add(a);
        ((AlarmSettingAdapter) listView.getAdapter()).notifyDataSetChanged();
        Collections.sort(alarmSettingList);
    }

    public ArrayList<AlarmSetting> getAlarmSettingList() {
        return alarmSettingList;
    }


    public ListView getListView() {
        return listView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_alarm_setting:
                //add a fragment that will enable the user to specify the timer settings, the i.e, CreateAlarmSetting class
                CreateAlarmSetting fragment = new CreateAlarmSetting();
                fragment.show(getFragmentManager().beginTransaction(), TAG);
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        alarmSettingList.get(position).setSetOn(((ToggleButton) view.findViewById(R.id.alarm_list_item_toggleButton)).isChecked());
        CreateAlarmSetting c = new CreateAlarmSetting();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CreateAlarmSetting.ISEDIT_PARAM, true);
        bundle.putInt(CreateAlarmSetting.ALARMSETTING_PARAM, position);
        c.setArguments(bundle);
        c.show(getFragmentManager().beginTransaction(), TAG);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
