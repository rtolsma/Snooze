package com.stanfordude.ryan.snooze.Alarm;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.stanfordude.ryan.snooze.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */


/*
    This Fragment is used to display the listview containing alarm settings
 */


public class AlarmFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<AlarmSetting> alarmSettingList=new ArrayList<AlarmSetting>();


    // the fragment initialization parameters for the Bundle
    private static final String LIST_VIEW = "AlarmFragmentListView";
    //the objects that recieve the values from the getArguments.getString("parameter")
    ;

    private OnFragmentInteractionListener mListener;


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_alarm_setting:
                //add a fragment that will enable the user to specify the timer settings, the i.e, CreateAlarmSetting class
              CreateAlarmSetting fragment=new CreateAlarmSetting();
                fragment.show(getFragmentManager().beginTransaction(), "tag-do something with");
                alarmSettingList.add(fragment.getAlarmSetting());
                break;


        }
    }


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
            listView = (ListView) savedInstanceState.get(LIST_VIEW);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //or getView().findViewById I'm not currently sure
        listView = (ListView) getActivity().findViewById(R.id.alarm_fragment_listview);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        Button addAlarmSetting = (Button) v.findViewById(R.id.add_alarm_setting);
        addAlarmSetting.setOnClickListener(this);


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
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
