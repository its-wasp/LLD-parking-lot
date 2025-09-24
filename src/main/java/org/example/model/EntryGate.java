package org.example.model;

import org.example.model.vehicle.Vehicle;
import org.example.service.ParkingService;

public class EntryGate {
    private final String id;
    private final ParkingService parkingService;

    public EntryGate(String id, ParkingService parkingService) {
        this.id = id;
        this.parkingService = parkingService;
    }

    public Ticket createTicket(ParkingLot lot, Vehicle vehicle) {
        System.out.println("INFO: Vehicle " + vehicle.getLicensePlate() + " entering through gate " + this.id);
        return parkingService.issueTicket(lot, vehicle, this);
    }

    public String getId() {
        return id;
    }
}
