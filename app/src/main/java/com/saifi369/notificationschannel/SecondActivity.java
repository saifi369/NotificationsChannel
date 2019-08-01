package com.saifi369.notificationschannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mOutputText = findViewById(R.id.tv_output);

        if (getIntent().hasExtra(MainActivity.MESSAGE_KEY)) {
            String message = getIntent().getStringExtra(MainActivity.MESSAGE_KEY);
            mOutputText.setText(message);
            Toast.makeText(this, "Toast from Notification: " + message, Toast.LENGTH_SHORT).show();
        }

    }
}
