package com.comp475.lhutrolleyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.comp475.lhutrolleyapp.R;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * TODO: There needs to be a check here to see if any notifications are set.
         *          If there are, setContentView(R.layout.activity_notifications).
         *          Else, setContentView(R.layout.activity_no_notifications).
         */
        setContentView(R.layout.activity_no_notifications);
    }
}