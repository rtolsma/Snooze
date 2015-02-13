package com.stanfordude.ryan.snooze.Alarm.Background;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by ryan on 2/6/15.
 */
public class BeepIntentService extends IntentService {

       public BeepIntentService(String s) {
           super(s);
       }

    public BeepIntentService() {
        super("BeepIntentService");
    }

    @Override
    public void onHandleIntent(Intent intent) {

  }


}
