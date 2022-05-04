package com.comp475.lhutrolleyapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.prefs.FilterScheduleSettingsFragment;

public class FilterScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_schedule);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new FilterScheduleSettingsFragment()).commit();
    }
}