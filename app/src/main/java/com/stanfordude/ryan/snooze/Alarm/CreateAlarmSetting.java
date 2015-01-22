package com.stanfordude.ryan.snooze.Alarm;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Button;

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



public class CreateAlarmSetting extends DialogFragment implements View.OnClickListener, TimePicker.OnTimeChangedListener, NumberPicker.OnValueChangeListener {

    /*
    the isEdit variable is set to true when an existing alarm time is clicked, this will add the
    "delete" button to the dialog, to possibly delete the item from the listview
     */
    private boolean isEdit = false;
    public static final String ISEDIT_PARAM = "isEdit";
    public static final String ALARMSETTING_PARAM = "AlarmSetting";
    public AlarmSetting alarmSetting;

    private TimePicker timePicker;
    private NumberPicker numberPicker;
    private Button cancelButton, saveButton, createButton;

    private OnFragmentInteractionListener mListener;

    public CreateAlarmSetting() {

    }

    /*
    Below are a few of the Fragment lifecycle methods
     */

    public void setArguments(Bundle bundle) {
        isEdit = bundle.getBoolean(ISEDIT_PARAM);
        alarmSetting = (AlarmSetting) bundle.get(ALARMSETTING_PARAM);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Initialize views and their listeners
        timePicker = (TimePicker) getActivity().findViewById(R.id.alarm_fragment_dialog_timepicker);
        numberPicker = (NumberPicker) getActivity().findViewById(R.id.alarm_fragment_dialog_numberpicker);
        cancelButton = (Button) getActivity().findViewById(R.id.alarm_fragment_dialog_cancel_button);
        createButton = (Button) getActivity().findViewById(R.id.alarm_fragment_dialog_create_button);
        saveButton = (Button) getActivity().findViewById(R.id.alarm_fragment_dialog_save_button);
        timePicker.setOnTimeChangedListener(this);
        numberPicker.setOnValueChangedListener(this);
        cancelButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        //create display based on given parameters

        if (isEdit) {
            //Replace cancel button with delete button
            ViewGroup parent = (ViewGroup) cancelButton.getParent();
            int index = parent.indexOfChild(cancelButton);
            parent.removeView(cancelButton);
            parent.addView(parent.findViewById(R.id.alarm_fragment_dialog_delete_button), index);
            //Replace create button with save button
            parent = (ViewGroup) createButton.getParent();
            index = parent.indexOfChild(createButton);
            parent.removeView(createButton);
            parent.addView(parent.findViewById(R.id.alarm_fragment_dialog_save_button), index);
            //TODO: Set Display Settings to current alarm settings

            timePicker.setCurrentHour(alarmSetting.getHours());
            timePicker.setCurrentMinute(alarmSetting.getMinutes());


        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            /*
            TODO: check if this is to edit a current alarmSetting and add the save button and delete button
             */

        View v = inflater.inflate(R.layout.fragment_create_alarm_setting, container, false);

        return v;
    }

/*  Not needed since not using alert dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {





        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(getActivity().findViewById(R.id.fragment_create_alarm_setting));
        builder.setTitle("Create a new Alarm Setting");
        builder.setNegativeButton(R.id.alarm_fragment_dialog_cancel_button, this);
        builder.setPositiveButton(R.id.alarm_fragment_dialog_set_button, this);

        return builder.create();



    }
*/

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
    Below are listeners for the actions in the dialog
     */

    @Override
    public void onValueChange(NumberPicker picker, int val, int num) {
        //Do something

    }

    @Override
    public void onTimeChanged(TimePicker picker, int hours, int minutes) {
        if (alarmSetting != null) {
            alarmSetting.setHours(hours);
            alarmSetting.setMinutes(minutes);
        } else alarmSetting = new AlarmSetting(hours, minutes, false);


    }


    @Override
    public void onClick(View v) {
        //do stuff for each button
        switch (v.getId()) {


        }


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
