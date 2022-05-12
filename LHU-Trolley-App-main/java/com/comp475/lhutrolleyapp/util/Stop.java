package com.comp475.lhutrolleyapp.util;

public class Stop {
    // I'm not sure if eta should be a String.
    // The final implementation might use the Time class or something similar.
    private final String pickup, destination, eta;
    private final int trolleyNum;

    public Stop(String pickup, String destination, String eta, int trolleyNum) {
        this.pickup = pickup;
        this.destination = destination;
        this.eta = eta;
        this.trolleyNum = trolleyNum;
    }

    public String getPickup() {
        return pickup;
    }

    public String getDestination() {
        return destination;
    }

    public String getEta() {
        return eta;
    }

    @Override
    public String toString() {
        return pickup + " - " + destination + " - " + eta + " - #" + trolleyNum;
    }
}
