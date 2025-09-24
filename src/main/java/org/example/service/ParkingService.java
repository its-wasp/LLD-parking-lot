package org.example.service;

import org.example.model.*;
import org.example.model.vehicle.Vehicle;
import org.example.strategy.IPricingStrategy;
import org.example.strategy.ISpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingService {
    private final ISpotAllocationStrategy spotAllocationStrategy;
    private final IPricingStrategy pricingStrategy;

    public ParkingService(ISpotAllocationStrategy spotAllocationStrategy, IPricingStrategy pricingStrategy) {
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket issueTicket(ParkingLot lot, Vehicle vehicle, EntryGate entryGate) {

        Spot spot = spotAllocationStrategy.findSpot(lot, vehicle, entryGate);
        if (spot == null) {
            System.err.println("ERROR: No available spot found for vehicle: " + vehicle.getLicensePlate());
            return null;
        }
        spot.occupy();
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, spot);
        lot.addTicket(ticket);
        System.out.println("SUCCESS: Ticket issued. Vehicle: " + vehicle.getLicensePlate() + ", Spot: " + spot.getId());
        return ticket;
    }

    public double processExit(ParkingLot lot, String ticketId, ExitGate exitGate) {

        Ticket ticket = lot.getTicket(ticketId);

        if (ticket == null) {
            System.err.println("ERROR: Invalid ticket ID: " + ticketId);
            return 0.0;
        }


        ticket.setExitTime(LocalDateTime.now());

        double fee = pricingStrategy.calculatePrice(ticket);
        System.out.println("SUCCESS: Calculated fee for ticket " + ticketId + " is $" + fee);

        ticket.getAllocatedSpot().free();

        lot.removeTicket(ticketId);

        System.out.println("INFO: Spot " + ticket.getAllocatedSpot().getId() + " is now free.");
        return fee;
    }
}
