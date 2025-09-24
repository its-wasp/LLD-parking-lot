package org.example;

import org.example.model.*;
import org.example.model.enums.SpotSize;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.Motorcycle;
import org.example.model.vehicle.Vehicle;
import org.example.service.ParkingService;
import org.example.strategy.IPricingStrategy;
import org.example.strategy.ISpotAllocationStrategy;
import org.example.strategy.NearestSpotAllocationStrategy;
import org.example.strategy.PerHourPricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ISpotAllocationStrategy allocationStrategy = new NearestSpotAllocationStrategy();
        IPricingStrategy pricingStrategy = new PerHourPricingStrategy();

        ParkingService parkingService = new ParkingService(allocationStrategy, pricingStrategy);

        List<Spot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new Spot("F1-S1", 1, SpotSize.SMALL));
        floor1Spots.add(new Spot("F1-S2", 2, SpotSize.MEDIUM));
        floor1Spots.add(new Spot("F1-S3", 3, SpotSize.MEDIUM));
        floor1Spots.add(new Spot("F1-S4", 4, SpotSize.LARGE));

        List<Floor> floors = new ArrayList<>();
        floors.add(new Floor(1, floor1Spots));

        EntryGate entryGate1 = new EntryGate("E1", parkingService);
        ExitGate exitGate1 = new ExitGate("X1", parkingService);

        ParkingLot mallParkingLot = new ParkingLot("PL-01", floors, List.of(entryGate1), List.of(exitGate1));

        // simulating Parking
        System.out.println("--- SIMULATION START ---");

        // A car enters
        Vehicle car = new Car("KA-01-HH-1234");
        Ticket carTicket = entryGate1.createTicket(mallParkingLot, car);

        // A motorcycle enters
        Vehicle motorcycle = new Motorcycle("KA-02-JK-5678");
        Ticket motorcycleTicket = entryGate1.createTicket(mallParkingLot, motorcycle);

        System.out.println("\n--- Current Lot Status ---");
        mallParkingLot.getFloors().get(0).getSpots().forEach(spot ->
                System.out.println("Spot " + spot.getId() + " (" + spot.getSpotSize() + "): " + spot.getStatus()));

        System.out.println("\n--- Vehicles parked for some time... ---\n");
        Thread.sleep(2000); // sleep for 2 secs.

        double carFee = exitGate1.checkout(mallParkingLot, carTicket.getTicketId());
        System.out.println("Car with license " + car.getLicensePlate() + " paid a fee of $" + carFee);

        System.out.println("\n--- Current Lot Status After Car Exit ---");
        mallParkingLot.getFloors().get(0).getSpots().forEach(spot ->
                System.out.println("Spot " + spot.getId() + " (" + spot.getSpotSize() + "): " + spot.getStatus()));

        System.out.println("\n--- SIMULATION END ---");
    }
}
