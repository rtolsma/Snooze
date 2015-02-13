package com.stanfordude.ryan.snooze.Alarm.Foreground;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.stanfordude.ryan.snooze.Alarm.Background.BeepReceiver;

import java.util.Calendar;

/**
 * Created by ryan on 1/8/15.
 *
 * The snoozeLength will be determined in minutes
 *
 * The ringer/alert will be set by the ui and stored in the main_activity
 *
 * This is just a class used to display all of the settings for a certain alarm
 * and parse the data into a string
 */
public class AlarmSetting implements Comparable<AlarmSetting> {

    public int hours;


    public int minutes;
    public int snoozeLength;
    public boolean setOn;

    PendingIntent pendingIntent;
    Intent intent;
    AlarmManager am;
    Context ctx;
    /*
    Hours will be in the same format of 0-23 as the timepicker is
     */
    public AlarmSetting(int hours, int minutes, int snoozeLength, boolean setOn, Context ctx, AlarmManager am) {
        this.hours = hours;
        this.minutes = minutes;
        this.snoozeLength = snoozeLength;
        this.setOn = setOn;
        this.ctx=ctx;
        this.am=am;


        intent = new Intent(ctx, BeepReceiver.class);
        intent.setAction(this.toString());
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

    }
    public AlarmSetting(int hours, int minutes, int snoozeLength, boolean setOn, Context ctx) {
        this.hours = hours;
        this.minutes = minutes;
        this.snoozeLength = snoozeLength;
        this.setOn = setOn;
        this.ctx=ctx;
        this.am=(AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);


        intent = new Intent(ctx, BeepReceiver.class);
        intent.setAction(this.toString());
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

    }




    //Not sure how this pendingIntent will work out, may not be unique which could
    //cause alarm cancellation errors.
    public void setAlarm() {
      //When editing values of the AlarmSetting, the previous alarm must be canceled, and a new one
        //must be made
        boolean temp;
        if (pendingIntent != null && (temp = isAlarmSet())) cancelAlarm();

     /*   if(intent==null) {
            intent = new Intent(ctx, BeepReceiver.class);
            intent.setAction(this.toString());
        }
        if(pendingIntent==null) pendingIntent = PendingIntent.getBroadcast(ctx,0 , intent, 0);
*/

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        long offset = 24 * 60 * 60 * 1000;
        long time = System.currentTimeMillis();
        long difference = (hours - calendar.get(Calendar.HOUR_OF_DAY) * 60 * 1000) + (minutes - calendar.get(Calendar.MINUTE) * 1000);
        if (am == null) am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), offset, pendingIntent);

        Toast.makeText(ctx, "Alarm is set", Toast.LENGTH_LONG).show();
    }

    public void cancelAlarm() {
        if(am==null || pendingIntent==null) return;
        am.cancel(pendingIntent);

    }

    public boolean isAlarmSet() {
        if (intent == null) return false;
        boolean enabled = (PendingIntent.getBroadcast(ctx, 0, intent, PendingIntent.FLAG_NO_CREATE) != null);

        return enabled;

    }




    /*
    Basic methods
     */


    public int compareTo(AlarmSetting a) {
        if (a.hours * 24 + a.minutes > this.hours * 24 + this.minutes) return -1;
        if (a.hours * 24 + a.minutes > this.hours * 24 + this.minutes) return 0;
        else return 1;
    }

    public String getState() {
        if (hours > 11)
            return "P.M";
        else
            return "A.M";
    }

    public String getTime() {
        if (minutes >= 10) {
          if(hours==0) return hours+12+":"+minutes;

            return hours > 12 ? hours - 12 + ":" + minutes : hours + ":" + minutes;

        }
        else {
            if(hours==0)  return hours+12+":0" + minutes;
            return hours > 12 ? hours - 12 + ":0" + minutes : hours + ":0" + minutes;
        }

    }

    @Override
    public String toString() {

        if (minutes >= 10)
        return hours > 12 ? hours - 12 + ":" + minutes + " P.M" : hours + ":" + minutes + "A.M";
        else
            return hours > 12 ? hours - 12 + ":0" + minutes + " P.M" : hours + ":0" + minutes + "A.M";

    }

    /*
    Getter and setters
     */

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSnoozeLength() {
        return snoozeLength;
    }

    public void setSnoozeLength(int snoozeLength) {
        this.snoozeLength = snoozeLength;
    }


    public boolean isSetOn() {
        return setOn;
    }

    public void setSetOn(boolean setOn) {

        this.setOn = setOn;

    }

}
