package com.comp475.lhutrolleyapp.util;

public class Stop {
    // I'm not sure if eta should be a String.
    // The final implementation might use the Time class or something similar.
    private String pickup, destination, eta;

    public Stop(String pickup, String destination, String eta) {
        this.pickup = pickup;
        this.destination = destination;
        this.eta = eta;
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
        return pickup + " - " + destination + " - " + eta;
    }
}
