package com.stanfordude.ryan.snooze.Alarm;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.stanfordude.ryan.snooze.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAlarmSetting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */

/* This fragment is put into view when the "add" button is pressed in the AlarmFragment class
it allows the user to create an alarm setting, and returns that setting in some form to the AlarmFragment
class to put in the listview.

Also, when an existing list_item in the AlarmFragment is pressed, this fragment will be displayed with the current list_item setting
and will permit editing of an alarm setting.

For convenience sake, I'll use android's existing Time Picker
 */



public class CreateAlarmSetting extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    private OnFragmentInteractionListener mListener;

    public CreateAlarmSetting() {
        // Required empty public constructor
    }

    /*
    Below are a few of the Fragment lifecycle methods
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_alarm_setting, container, false);
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

    /*
    Below is the required method for implementing the OnSetTimeListener Interface
     */

    @Override
    public void onTimeSet(TimePicker tp, int hour, int min) {


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
