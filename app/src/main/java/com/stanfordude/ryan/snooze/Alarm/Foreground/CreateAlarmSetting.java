package com.stanfordude.ryan.snooze.Alarm.Foreground;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.stanfordude.ryan.snooze.MainActivity;
import com.stanfordude.ryan.snooze.R;

import java.util.Calendar;

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

I'm not replacing existing views with views from other layouts, too much of a hassle, I'll simply add a button in runtime, and change
the text on the existing buttons

TODO:Convert current dialog into use with AlertDialog, it has a better design, and flexibility/usability increases

 */



public class CreateAlarmSetting extends DialogFragment implements View.OnClickListener, AlertDialog.OnClickListener, TimePicker.OnTimeChangedListener, NumberPicker.OnValueChangeListener {

    private MainActivity ma;
    private AlarmFragment af;


    //AlarmService stuff
    public AlarmManager alarmManager;
    /*
    the isEdit variable is set to true when an existing alarm time is clicked, this will add the
    "delete" button to the dialog, to possibly delete the item from the listview
     */
    private boolean isEdit = false;
    public static final String ISEDIT_PARAM = "isEdit";
    public static final String ALARMSETTING_PARAM = "AlarmSetting";
    public AlarmSetting alarmSetting;
    //Index in the arraylist
    private int settingIndex;
    private TimePicker timePicker;
    private NumberPicker numberPicker;
    //private Button cancelButton, createButton, deleteButton;
    //View of the dialog layout that will be inflated
    private View main;
    private AlertDialog alertDialog;
    private OnFragmentInteractionListener mListener;

    public CreateAlarmSetting() {

    }

        /*
        Below are a few of the Fragment lifecycle methods
         */

    public void setArguments(Bundle bundle) {
        if (bundle != null) {
            isEdit = bundle.getBoolean(ISEDIT_PARAM);
            settingIndex = bundle.getInt(ALARMSETTING_PARAM);
            if (af != null) {

                alarmSetting = af.getAlarmSettingList().get(settingIndex);
            }
        }
    }

    public AlarmSetting getAlarmSetting() {
        return alarmSetting;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            /*
            Not doing anything yet, because findViewById() doesn't work at this point in the lifecycle
             */

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);




        ma = (MainActivity) getActivity();
        af = ma.getAlarmFragment();
        alarmManager= (AlarmManager) ma.getSystemService(Context.ALARM_SERVICE);
        //Dialog initialization stuff below

        if (main == null) {
            main = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_alarm_setting, null);
        }
        timePicker = (TimePicker) main.findViewById(R.id.alarm_fragment_dialog_timepicker);
        numberPicker = (NumberPicker) main.findViewById(R.id.alarm_fragment_dialog_numberpicker);
        timePicker.setOnTimeChangedListener(this);
        numberPicker.setOnValueChangedListener(this);
        numberPicker.setMaxValue(45);
        numberPicker.setMinValue(1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        if (!isEdit) {
            timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.getInstance().get(Calendar.MINUTE));
            alarmSetting = new AlarmSetting(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 1, false, getActivity() , alarmManager );


            builder.setView(main);
        builder.setNegativeButton("Cancel", this);
        builder.setPositiveButton("Create", this);
        builder.setTitle("Create a new Alarm Setting");
            return (alertDialog = builder.show());
        } else {

            if (alarmSetting == null) {
                alarmSetting = af.getAlarmSettingList().get(settingIndex);

            }

            timePicker.setCurrentMinute(alarmSetting.getMinutes());
            timePicker.setCurrentHour(alarmSetting.getHours());
            numberPicker.setValue(alarmSetting.getSnoozeLength());
            builder.setView(main);
            builder.setNegativeButton("Delete", this);
            builder.setPositiveButton("Save", this);
            return (alertDialog = builder.show());

        }


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
    Below are listeners for the actions in the dialog
     */
    @Override
    public void onClick(DialogInterface dialogInterface, int id) {
        switch (id) {
            case DialogInterface.BUTTON_NEGATIVE:
                if (isEdit) {
                    af.getAlarmSettingList().remove(settingIndex);
                    ((BaseAdapter) af.getListView().getAdapter()).notifyDataSetChanged();

                    //Will cancel any alarm with a matching pendingIntent in this AlarmSetting
                    //because its being deleted
                    af.getAlarmSettingList().get(settingIndex).cancelAlarm();

                }
                break;
            case DialogInterface.BUTTON_NEUTRAL:


            case DialogInterface.BUTTON_POSITIVE:
                //get data from pickers, add alarm to listview
                alarmSetting.minutes = timePicker.getCurrentMinute();
                alarmSetting.hours = timePicker.getCurrentHour();
                alarmSetting.snoozeLength = numberPicker.getValue();
                if (isEdit)
                    af.getAlarmSettingList().remove(settingIndex);
                af.addAlarmSetting(alarmSetting);

                break;
        }


    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        //Do something
        //   alarmSetting.setSnoozeLength(newVal);
    }

    @Override
    public void onTimeChanged(TimePicker picker, int hours, int minutes) {
       /* if (alarmSetting != null) {
            alarmSetting.setHours(hours);
            alarmSetting.setMinutes(minutes);
        } else alarmSetting = new AlarmSetting(hours, minutes, false);

*/
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
