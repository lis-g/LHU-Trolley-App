package com.comp475.lhutrolleyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.comp475.lhutrolleyapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "comp475";
    private boolean notifIsSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.main_menu_label);

        Button schedule = findViewById(R.id.scheduleButton);
        Button notifications = findViewById(R.id.notificationButton);
        Button map = findViewById(R.id.mapButton);
        Button exit = findViewById(R.id.exitButton);


        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Schedule button tapped!");
                startActivity(new Intent(view.getContext(), ScheduleActivity.class));
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Notifications button tapped!");
                startActivity(new Intent(view.getContext(), NotificationsActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Map button tapped!");
                Toast toast = Toast.makeText(getApplicationContext(), "Unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Exit button tapped!");
                finish();
            }
        });
    }

    // getter
    public boolean getNotifStatus() {
        return notifIsSet;
    }

    // setter
    public void setNotifStatus( boolean checkNotifStatus) {
        this.notifIsSet = checkNotifStatus;
    }
}