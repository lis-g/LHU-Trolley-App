package com.comp475.lhutrolleyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.comp475.lhutrolleyapp.R;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * check if any notifications are set then set the appropriate content view
         */
        MainActivity Status = new MainActivity();
        boolean check = Status.getNotifStatus();

        if( true == check )
            setContentView(R.layout.activity_notifications);
        else
            setContentView(R.layout.activity_no_notifications);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case(android.R.id.home):
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
