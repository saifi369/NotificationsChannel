package com.saifi369.notificationschannel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    private EditText mTitleText,mMessageText;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void btnOneClicked(View view) {

        String title=mTitleText.getText().toString();
        String message=mMessageText.getText().toString();

        Intent contentIntent = new Intent(this, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                this, 0, contentIntent, 0);

        Intent actionIntent = new Intent(this, SecondActivity.class);

        actionIntent.putExtra(MESSAGE_KEY, message);

        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(contentPendingIntent)
                .addAction(R.mipmap.ic_launcher, "Show Toast", actionPendingIntent)
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();


        manager.notify(1,notification);

    }

    public void btnTwoClicked(View view) {

        String title=mTitleText.getText().toString();
        String message=mMessageText.getText().toString();

        Intent broadcastIntent = new Intent(this, NotificationBroadcastReceiver.class);
        broadcastIntent.putExtra(MESSAGE_KEY, message);

        PendingIntent broadcastPendingIntent = PendingIntent.getBroadcast(
                this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra(MESSAGE_KEY, message);

        PendingIntent servicePendingIntent = PendingIntent.getService(
                this, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.mipmap.ic_launcher, "Show Toast", broadcastPendingIntent)
                .addAction(R.mipmap.ic_launcher, "Start Service", servicePendingIntent)
                .build();

        manager.notify(2,notification);

    }

    private void initViews() {
        mTitleText=findViewById(R.id.text_tile);

        mMessageText=findViewById(R.id.text_message);
    }
}
