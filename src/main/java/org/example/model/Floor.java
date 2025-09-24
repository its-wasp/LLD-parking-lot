package org.example.model;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Spot> spots;

    public Floor(int floorNumber, List<Spot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Spot> getSpots() {
        return spots;
    }
}
