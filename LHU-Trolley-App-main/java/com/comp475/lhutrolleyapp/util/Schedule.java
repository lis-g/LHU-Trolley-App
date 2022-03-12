package com.comp475.lhutrolleyapp.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class Schedule extends ArrayAdapter<String> {

    public Schedule(Context context, int resource, StopDatabase stopList) {
        super(context, resource, stopList.getStopsAsStrings());
    }
}
