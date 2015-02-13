package com.stanfordude.ryan.snooze.Alarm.Background;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ryan on 2/6/15.
 * This class will receive the intent
 */
public class BeepReceiver extends BroadcastReceiver {
    //Time Based Code Execution stuff


    @Override
    public void onReceive(Context ctx, Intent intent) {
        Toast.makeText(ctx, "Hello", Toast.LENGTH_LONG).show();
        Log.d("Tag", "Received stuff");
    }



}
