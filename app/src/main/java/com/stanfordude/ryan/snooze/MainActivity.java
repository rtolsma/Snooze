package com.stanfordude.ryan.snooze;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stanfordude.ryan.snooze.Alarm.AlarmFragment;
import com.stanfordude.ryan.snooze.Alarm.CreateAlarmSetting;

import java.net.URI;


public class MainActivity extends ActionBarActivity implements AlarmFragment.OnFragmentInteractionListener, CreateAlarmSetting.OnFragmentInteractionListener {


    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    AlarmFragment alarmFragment;



    public void onFragmentInteraction(Uri uri) {}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

        }
          /*  SharedPreferences pref=getSharedPreferences(AlarmFragment.FILE, Context.MODE_PRIVATE);
        switch(pref.getString(AlarmFragment.LASTUSED, ""))  {
            case AlarmFragment.TAG: displayAlarmFragment(null);

            default: break;

        }  */


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
     /*   SharedPreferences pref=getSharedPreferences(AlarmFragment.FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pref.edit();
        Fragment temp=fragmentManager.findFragmentById(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getId());
        edit.putString(AlarmFragment.LASTUSED, temp.getTag());
        edit.commit();  */
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
        alarmFragment = new AlarmFragment();
        fragmentTransaction.replace(R.id.fragment_container, alarmFragment, AlarmFragment.TAG);
        fragmentTransaction.addToBackStack(AlarmFragment.TAG);
        fragmentTransaction.commit();


    }


}
