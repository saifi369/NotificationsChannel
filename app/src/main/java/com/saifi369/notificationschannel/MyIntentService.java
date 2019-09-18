package com.saifi369.notificationschannel;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "onHandleIntent: called");
        startForeground(1002, getNotification());

        if (intent != null) {
            if (intent.hasExtra(MainActivity.MESSAGE_KEY)) {
                String data = intent.getStringExtra(MainActivity.MESSAGE_KEY);
                Log.d(TAG, "onHandleIntent: Data: " + data);

                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "onHandleIntent: Counter is: " + (i + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Notification getNotification() {

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_insert_drive_file)
                .setContentTitle("Service Notification")
                .setContentText("A Background Service is Running")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        return notification;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
        stopForeground(true);
    }
}
