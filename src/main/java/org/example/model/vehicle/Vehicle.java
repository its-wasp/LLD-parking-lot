package org.example.model.vehicle;

import org.example.model.enums.SpotSize;

public abstract class Vehicle {
    private final String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract SpotSize getRequiredSpotSize();
}
