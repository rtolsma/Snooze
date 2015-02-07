package com.stanfordude.ryan.snooze;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.stanfordude.ryan.snooze.Alarm.Foreground.AlarmFragment;
import com.stanfordude.ryan.snooze.Alarm.Foreground.CreateAlarmSetting;

import java.util.ArrayList;

/*
I was a little lazy in terms of placing certain methods and constants in the correct
or preferable places, so there are often constants that should be universal but are accessed through
specific classes



 */

public class MainActivity extends ActionBarActivity implements AlarmFragment.OnFragmentInteractionListener, CreateAlarmSetting.OnFragmentInteractionListener {


    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    AlarmFragment alarmFragment = new AlarmFragment();



    public void onFragmentInteraction(Uri uri) {}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View setup stuff
        SharedPreferences pref = getSharedPreferences(AlarmFragment.FILE, Context.MODE_PRIVATE);
        String temp;
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(AlarmFragment.LASTUSED);
            switch (result) {

                case AlarmFragment.TAG:
                    displayAlarmFragment(null);
                    break;

                default:
                    break;
            }
        } else if ((temp = pref.getString(AlarmFragment.LASTUSED, "")) != "") {
            switch (temp) {
                case AlarmFragment.TAG:
                    displayAlarmFragment(null);
                    break;
                default:
                    break;


            }

        } else {
            displayAlarmFragment(null);
        }






    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
   /*    //Gets tag of last fragment in the backStack
        if(savedInstanceState==null) savedInstanceState=new Bundle();

         savedInstanceState.putString(AlarmFragment.LASTUSED,
          fragmentManager.findFragmentById(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getId()).getTag());
       savedInstanceState.putString("Key", "Test");
        super.onSaveInstanceState(savedInstanceState); */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences pref = getSharedPreferences(AlarmFragment.FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pref.edit();
        Fragment temp=fragmentManager.findFragmentById(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getId());
        edit.putString(AlarmFragment.LASTUSED, temp.getTag());
        edit.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
                    /*
                    Helper methods
             */


    public AlarmFragment getAlarmFragment() {
        return alarmFragment;
    }

        /*
        Methods for displaying fragments
        TODO: In these activities have the FragmentTransaction pop the existing fragment being displayed before adding the new one
         */

    public void displayAlarmFragment(View v) {
        if (alarmFragment != null && alarmFragment.isVisible()) return;

        if (alarmFragment == null) alarmFragment = new AlarmFragment();
        fragmentTransaction.replace(R.id.fragment_container, alarmFragment, AlarmFragment.TAG);
        fragmentTransaction.addToBackStack(AlarmFragment.TAG);
        fragmentTransaction.commit();


    }


}
