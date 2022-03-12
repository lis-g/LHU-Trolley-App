package com.comp475.lhutrolleyapp.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.util.Schedule;
import com.comp475.lhutrolleyapp.util.Stop;
import com.comp475.lhutrolleyapp.util.StopDatabase;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private StopDatabase stopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        final ListView listView = findViewById(R.id.schedule);
        setupStopList();
        final Schedule adapter = new Schedule(this, android.R.layout.simple_list_item_1, stopList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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
}