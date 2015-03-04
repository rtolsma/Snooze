package com.stanfordude.ryan.snooze.Alarm.Background;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import com.stanfordude.ryan.snooze.Alarm.Foreground.AlarmSetting;

import java.util.Calendar;

/**
 * Created by ryan on 2/6/15.
 */
public class BeepIntentService extends IntentService {

    static MediaPlayer mMediaPlayer;
    Context ctx;

    public BeepIntentService(String s) {
        super(s);
    }

    public BeepIntentService() {
        super("BeepIntentService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        ctx = getBaseContext();
        Calendar cal = Calendar.getInstance();
        Bundle extra = intent.getExtras();

        int snoozeLength = extra.getInt("snoozeLength");
        int id = 0;

        if (extra.getBoolean("Startup")) {
            //is the initial intent to start ringing
            id = 0;

            //provide code to start the ringing
            playRingtone();

        } else if (!extra.getBoolean("STOP")) {
            //is the snooze button
            id = extra.getInt("ID");

            //code to stop ringing here
            stopRingtone();
            // set new alarm to go off in snoozeLength more minutes... somewhat recursive paradigm
            Intent newIntent = new Intent(ctx, BeepReceiver.class);
            newIntent.setAction("Snooze restart for notification: " + id);
            PendingIntent pi = PendingIntent.getBroadcast(ctx, 0, newIntent, 0);
            cal.add(Calendar.MINUTE, snoozeLength);
            ((AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);


        } else {
            //is the stop button
            id = extra.getInt("ID");
            //code to stop ringing here
            stopRingtone();
        }
        if (id != 0)
            ((NotificationManager) ctx.getSystemService(NOTIFICATION_SERVICE)).cancel(id);


    }

    public void playRingtone() {
        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alert == null) {
                // alert is null, using backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

                if (alert == null) {
                    // alert backup is null, using 2nd backup
                    alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }
            }
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(ctx, alert);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }

        } catch (Exception e) {
//I don't know what to do

        }


    }

    public void stopRingtone() {
        mMediaPlayer.pause();
        mMediaPlayer.stop();
    }
}
