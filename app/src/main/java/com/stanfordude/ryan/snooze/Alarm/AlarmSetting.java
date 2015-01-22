package com.stanfordude.ryan.snooze.Alarm;

/**
 * Created by ryan on 1/8/15.
 * <p/>
 * The snoozeLength will be determined in minutes
 * <p/>
 * The ringer/alert will be set by the ui and stored in the main_activity
 * <p/>
 * <p/>
 * This is just a class used to display all of the settings for a certain alarm
 */
public class AlarmSetting {

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

    @Override
    public String toString() {


        return hours > 12 ? hours - 12 + ":" + minutes + " P.M" : hours + ":" + minutes + "A.M";

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
