package com.comp475.lhutrolleyapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.comp475.lhutrolleyapp.R;

public class SetNotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);

        String pickup = getIntent().getStringExtra(ScheduleActivity.EXTRA_MESSAGE_1);
        String destination = getIntent().getStringExtra(ScheduleActivity.EXTRA_MESSAGE_2);
        String eta = getIntent().getStringExtra(ScheduleActivity.EXTRA_MESSAGE_3);

        TextView stopInfo = findViewById(R.id.stopInfo);
        stopInfo.setText(getString(R.string.set_notification_stop_info, destination, pickup, eta));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O )
        {
            NotificationChannel channel = new NotificationChannel("lhutrolleyID", "lhutrolleyname", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(view -> {
            Spinner timeSelector = findViewById(R.id.timeSelector);
            String timeSelected = timeSelector.getSelectedItem().toString();

            // This code outputs the currently selected time as a Toast and is only meant to be temporary
 //           Toast toast = Toast.makeText(getApplicationContext(), timeSelected, Toast.LENGTH_SHORT);
 //           toast.show();

            // Set a notification. The timeSelected variable contains the selected time as a String.
            NotificationCompat.Builder builder = new NotificationCompat.Builder(SetNotificationActivity.this, "LHUTrolleyChannelID")
                    .setContentText("Your trolley will arrive in " + timeSelected)
                    .setSmallIcon(R.drawable.ic_baseline_directions_bus_24)
                    .setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(SetNotificationActivity.this);
            managerCompat.notify(1,builder.build());

            // change notif status to true
            MainActivity Status = new MainActivity();
            Status.setNotifStatus(true);

        });
    }
}