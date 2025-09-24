package org.example.model;

import org.example.model.enums.SpotSize;
import org.example.model.enums.SpotStatus;

public class Spot {
    private final String id;
    private final int number;
    private final SpotSize spotSize;
    private SpotStatus status;

    public Spot(String id, int number, SpotSize spotSize) {
        this.id = id;
        this.number = number;
        this.spotSize = spotSize;
        this.status = SpotStatus.AVAILABLE; // default
    }

    public boolean isAvailable() {
        return this.status == SpotStatus.AVAILABLE;
    }

    public void occupy() {
        this.status = SpotStatus.OCCUPIED;
    }

    public void free() {
        this.status = SpotStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public SpotStatus getStatus() {
        return status;
    }
}
