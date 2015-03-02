package com.stanfordude.ryan.snooze.Alarm.Background;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stanfordude.ryan.snooze.R;

import java.util.ArrayList;

/**
 * Created by ryan on 2/6/15.
 * This class will receive the intent
 */
public class BeepReceiver extends BroadcastReceiver {
    //Time Based Code Execution stuff
    public int snoozeLength;

    @Override
    public void onReceive(Context ctx, Intent intent) {
        //start the ringing
        Intent initial=new Intent(ctx, BeepIntentService.class);
        initial.putExtra("Startup", true);
        ctx.startService(initial);
    /*
    Testing whether flag_cancel_current will help right now
     */



        //creates uniqe id base off of time
        int id= Integer.parseInt( new String(""+System.currentTimeMillis()).substring(4)  );
        Intent snoozeIntent=new Intent(ctx, BeepIntentService.class);
        PendingIntent snoozePi= PendingIntent.getService(ctx, 0, snoozeIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Intent stopIntent=new Intent(ctx, BeepIntentService.class);
        PendingIntent stopPi=PendingIntent.getService(ctx, 0, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);
     //create 2 pending intents, one for each action




        Bundle snoozeAction= new Bundle();
        Bundle stopAction = new Bundle();
        snoozeLength=intent.getIntExtra("snooze", 1);
        snoozeAction.putInt("snoozeLength", snoozeLength);
        stopAction.putInt("snoozeLength", snoozeLength);
        snoozeAction.putBoolean("STOP", false);
        stopAction.putBoolean("STOP", true);
        snoozeAction.putInt("ID", id);
        stopAction.putInt("ID", id);
        snoozeIntent.putExtras(snoozeAction);
        stopIntent.putExtras(stopAction);
        Notification.Builder builder=new Notification.Builder(ctx);
        builder.setContentTitle("Alarm has Triggered");
        builder.addAction(R.drawable.clock, " Snooze", snoozePi);
        builder.addAction(R.drawable.blank, "Stop", stopPi);
        builder.setSmallIcon(R.drawable.clock);
        Notification notify= builder.build();
        NotificationManager notificationManager= (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notify);
        Toast.makeText(ctx, "Broadcast Received from alarm scheduled for " + intent.getAction(), Toast.LENGTH_LONG).show();
        Log.d("BeepIntentService", "Received Broadcast from alarm scheduled at "+intent.getAction());
    }



}
