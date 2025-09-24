package org.example.model;

import org.example.service.ParkingService;

public class ExitGate {
    private final String id;
    private final ParkingService parkingService;

    public ExitGate(String id, ParkingService parkingService) {
        this.id = id;
        this.parkingService = parkingService;
    }

    public double checkout(ParkingLot lot, String ticketId) {
        System.out.println("INFO: Processing checkout for ticket ID " + ticketId + " at exit gate " + this.id);
        return parkingService.processExit(lot, ticketId, this);
    }

    public String getId() {
        return id;
    }
}
