package com.saifi369.notificationschannel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        manager.notify(1,notification);

    }

    public void btnTwoClicked(View view) {

        String title=mTitleText.getText().toString();
        String message=mMessageText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        manager.notify(2,notification);

    }

    private void initViews() {
        mTitleText=findViewById(R.id.text_tile);
        mMessageText=findViewById(R.id.text_message);
    }
}
