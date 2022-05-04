package com.comp475.lhutrolleyapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.util.Schedule;
import com.comp475.lhutrolleyapp.util.Stop;
import com.comp475.lhutrolleyapp.util.StopDatabase;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_1 = "pickupArgument", EXTRA_MESSAGE_2 = "destinationArgument", EXTRA_MESSAGE_3 = "etaArgument";

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
            final String pickup = parsePickup(stop);
            final String destination = parseDestination(stop);
            final String eta = parseEta(stop);

            Intent intent = new Intent(getApplicationContext(), SetNotificationActivity.class);
            intent.putExtra(EXTRA_MESSAGE_1, pickup);
            intent.putExtra(EXTRA_MESSAGE_2, destination);
            intent.putExtra(EXTRA_MESSAGE_3, eta);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.filter) {
            // THIS DOES NOT FUNCTION PROPERLY YET! USE CAUTION.
            startActivity(new Intent(this, FilterScheduleActivity.class));
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void setupStopList() {
        // This is just for testing purposes, the final product will get all of this information
        // from the database.
        ArrayList<Stop> list = new ArrayList<>();

        list.add(new Stop("Campus Drive Circle", "East Campus", "7:41 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "7:45 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "7:49 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "7:53 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "7:57 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:01 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:05 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:09 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:13 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:17 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:21 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:25 AM"));
        list.add(new Stop("Campus Drive Circle", "East Campus", "8:29 AM"));
        list.add(new Stop("East Campus", "Campus Drive Circle", "8:33 AM"));

        stopList = new StopDatabase(list);
    }

    private String parsePickup(String stop) {
        return stop.substring(0, stop.indexOf('-') - 1);
    }

    private String parseDestination(String stop) {
        return stop.substring(stop.indexOf('-') + 2, stop.indexOf('-', stop.indexOf('-') + 2));
    }

    private String parseEta(String stop) {
        return stop.substring(stop.indexOf('-', stop.indexOf('-') + 2) + 2);
    }
}