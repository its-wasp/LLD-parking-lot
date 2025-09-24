package org.example.strategy;

import org.example.model.EntryGate;
import org.example.model.ParkingLot;
import org.example.model.Spot;
import org.example.model.vehicle.Vehicle;

public interface ISpotAllocationStrategy {
    Spot findSpot(ParkingLot lot, Vehicle vehicle, EntryGate entryGate);
}
