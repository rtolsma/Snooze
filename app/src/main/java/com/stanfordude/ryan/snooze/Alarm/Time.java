package com.stanfordude.ryan.snooze.Alarm;

/**
 * Created by ryan on 1/8/15.
 *
 * The snoozeLength will be determined in minutes
 *
 * The ringer/alert will be set by the ui and stored in the main_activity
 *
 *
 * This is just a class used to display all of the settings for a certain alarm
 *
 *
 */
public class Time {

        public int hours;


    public int minutes;
    public int snoozeLength;
    public int snoozeTimes;
              public boolean  setOn;
            public Time(int hours, int minutes, int snoozeLength, int snoozeTimes, boolean setOn) {
                this.hours=hours;
                this.minutes=minutes;
                this.snoozeLength=snoozeLength;
                this.snoozeTimes=snoozeTimes;
                this.setOn=setOn;
            }
    @Override
        public String toString() {

        return  hours+":"+minutes;

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

    public int getSnoozeTimes() {
        return snoozeTimes;
    }

    public void setSnoozeTimes(int snoozeTimes) {
        this.snoozeTimes = snoozeTimes;
    }

    public boolean isSetOn() {
        return setOn;
    }

    public void setSetOn(boolean setOn) {
        this.setOn = setOn;
    }

}
