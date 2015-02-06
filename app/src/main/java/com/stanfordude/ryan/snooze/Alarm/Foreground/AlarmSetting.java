package com.stanfordude.ryan.snooze.Alarm.Foreground;

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

    /*
    Hours will be in the same format of 0-23 as the timepicker is
     */
    public AlarmSetting(int hours, int minutes, int snoozeLength, boolean setOn) {
        this.hours = hours;
        this.minutes = minutes;
        this.snoozeLength = snoozeLength;
        this.setOn = setOn;
    }

    public AlarmSetting(int hours, int minutes, boolean setOn) {
        this.hours = hours;
        this.minutes = minutes;
        this.setOn = setOn;

    }

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
        if (minutes >= 10)
            return hours > 12 ? hours - 12 + ":" + minutes : hours + ":" + minutes;
        else return hours > 12 ? hours - 12 + ":0" + minutes : hours + ":0" + minutes;

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
