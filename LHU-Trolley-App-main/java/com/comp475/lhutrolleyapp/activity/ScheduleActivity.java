package com.comp475.lhutrolleyapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.util.Schedule;
import com.comp475.lhutrolleyapp.util.Stop;
import com.comp475.lhutrolleyapp.util.StopDatabase;
import com.comp475.lhutrolleyapp.util.StopSelectionErrorDialogFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_1 = "trolleyNumArgument", EXTRA_MESSAGE_2 = "pickupArgument", EXTRA_MESSAGE_3 = "destinationArgument", EXTRA_MESSAGE_4 = "etaArgument";

    private StopDatabase stopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        final ListView listView = findViewById(R.id.schedule);
        setupStopList();
        final Schedule adapter = new Schedule(this, android.R.layout.simple_list_item_1, stopList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            final String stop = (String) parent.getItemAtPosition(position);
            if (verifyStop(stop)) {
                final int trolleyNum = parseTrolleyNum(stop);
                final String pickup = parsePickup(stop);
                final String destination = parseDestination(stop);
                final String eta = parseEta(stop);

                Intent intent = new Intent(getApplicationContext(), SetNotificationActivity.class);
                intent.putExtra(EXTRA_MESSAGE_1, trolleyNum);
                intent.putExtra(EXTRA_MESSAGE_2, pickup);
                intent.putExtra(EXTRA_MESSAGE_3, destination);
                intent.putExtra(EXTRA_MESSAGE_4, eta);
                startActivity(intent);
            } else {
                StopSelectionErrorDialogFragment dialogFragment = new StopSelectionErrorDialogFragment();
                dialogFragment.show(dialogFragment.getChildFragmentManager(), StopSelectionErrorDialogFragment.TAG);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case(R.id.filter):
                startActivity(new Intent(this, FilterScheduleActivity.class));
                break;

            case(android.R.id.home):
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void setupStopList() {
        // This is just for testing purposes, the final product will get all of this information
        // from the database.
        ArrayList<Stop> list = new ArrayList<>();

        list.add(new Stop("Campus Drive Circle", "East Campus", "7:41 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "7:45 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "7:49 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "7:53 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "7:57 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:01 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:05 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:09 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:13 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:17 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:21 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:25 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:29 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:33 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:37 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:41 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:45 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:45 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:49 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:49 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:53 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:53 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:57 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:57 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:01 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:01 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:05 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:05 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:09 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:09 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:13 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:13 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:17 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:17 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:21 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:21 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:25 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:25 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:29 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:29 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:33 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:33 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:37 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:37 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:41 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:41 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:45 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:45 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:49 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:49 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:53 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:53 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "9:57 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "9:57 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:01 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:01 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:05 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:05 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:09 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:09 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:13 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:13 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:17 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:17 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:21 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:21 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:25 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:25 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:29 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:29 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:33 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:33 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:37 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:37 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:41 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:41 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:45 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:45 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:49 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:49 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:53 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:53 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "10:57 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "10:57 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:01 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:01 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:05 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:05 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:09 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:09 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:13 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:13 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:17 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:17 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:21 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:21 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:25 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:25 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:29 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:29 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:33 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:33 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:37 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:37 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:41 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:41 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:45 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:45 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:49 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:49 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:53 AM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:53 AM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "11:57 AM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "11:57 AM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "12:01 PM", 2));
        list.add(new Stop("East Campus", "Campus Drive Circle", "12:01 PM", 1));
        list.add(new Stop("East Campus", "Campus Drive Circle", "12:05 PM", 2));
        list.add(new Stop("Campus Drive Circle", "East Campus", "12:05 PM", 1));
        list.add(new Stop("Campus Drive Circle", "East Campus", "12:09 PM", 2));

        stopList = new StopDatabase(list);
    }

    private boolean verifyStop(String stop) {
        Pattern pattern = Pattern.compile("([a-zA-Z\\s])+(-)+([a-zA-Z\\s])+(-)+(\\s)+(0?[1-9]|1[0-2])+(:)+([0-5][0-9])+(\\s)+(A|P)+(M)+(\\s)+(-)+(\\s)+(#)+(\\d)");
        Matcher matcher = pattern.matcher(stop);
        return matcher.find();
    }

    private int parseTrolleyNum(String stop) {
        return Integer.parseInt(stop.substring(stop.length() - 1));
    }

    private String parsePickup(String stop) {
        return stop.substring(0, stop.indexOf('-') - 1);
    }

    private String parseDestination(String stop) {
        return stop.substring(stop.indexOf('-') + 2, stop.indexOf('-', stop.indexOf('-') + 2) - 1);
    }

    private String parseEta(String stop) {
        return stop.substring(stop.indexOf('-', stop.indexOf('-') + 2) + 2, stop.length() - 5);
    }
}
