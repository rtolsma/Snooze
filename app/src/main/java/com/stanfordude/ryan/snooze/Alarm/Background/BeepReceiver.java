package com.stanfordude.ryan.snooze.Alarm.Background;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by ryan on 2/6/15.
 * This class will receive the intent
 */
public class BeepReceiver extends BroadcastReceiver {
    //Time Based Code Execution stuff
    public AlarmManager alarmManager;
    public ArrayList<AlarmManager.AlarmClockInfo> alarmClocks = new ArrayList<>();


    @Override
    public void onReceive(Context ctx, Intent intent) {
        setAlarms(ctx);
    }


    public void setAlarms(Context ctc) {

    }


}
