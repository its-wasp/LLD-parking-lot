package org.example.strategy;

import org.example.model.EntryGate;
import org.example.model.Floor;
import org.example.model.ParkingLot;
import org.example.model.Spot;
import org.example.model.enums.SpotSize;
import org.example.model.vehicle.Vehicle;

/**
 a simple nearest spot allocation strategy implementation for now.
 */

public class NearestSpotAllocationStrategy implements ISpotAllocationStrategy {
    @Override
    public Spot findSpot(ParkingLot lot, Vehicle vehicle, EntryGate entryGate) {
        SpotSize requiredSize = vehicle.getRequiredSpotSize();
        for (Floor floor : lot.getFloors()) {
            for (Spot spot : floor.getSpots()) {
                // Find a spot that is available and large enough
                if (spot.isAvailable() && spot.getSpotSize().ordinal() >= requiredSize.ordinal()) {
                    return spot;
                }
            }
        }
        return null; // No spot found
    }
}
