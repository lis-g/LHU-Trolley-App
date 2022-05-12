package com.comp475.lhutrolleyapp.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.prefs.FilterScheduleSettingsFragment;

public class FilterScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_schedule);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new FilterScheduleSettingsFragment()).commit();

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
