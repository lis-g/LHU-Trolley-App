package com.comp475.lhutrolleyapp.util;

import java.util.ArrayList;

public class StopDatabase {

    private ArrayList<Stop> stopList;

    public StopDatabase(ArrayList<Stop> stopList) {
        this.stopList = stopList;
    }

    public ArrayList<String> getStopsAsStrings() {
        ArrayList<String> list = new ArrayList<>();

        for (Stop s : stopList)
            list.add(s.toString());

        return list;
    }
}
