package com.example.notificationlistener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_request_access).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent[] intent = Arrays.asList(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)).toArray(new Intent[0]);
                startActivities(intent);
            }
        });
    }
}